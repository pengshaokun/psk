package com.zhskg.bag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhskg.bag.model.GroupResult;
import com.zhskg.bag.model.Stock;
import com.zhskg.bag.param.StockParam;
import com.zhskg.bag.service.StockService;
import com.zhskg.bag.util.ElasticCilent;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms.Bucket;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Service(interfaceName = "com.zhskg.bag.service.StockService", version = "1.0")
public class StockServiceImpl implements StockService
{
	private static final String index="bg_stocks";
	private static final String type = "bg_stock";

	/**
	 * 产品初始化
	 */
	public synchronized String add(Stock entry)
	{
		TransportClient client = ElasticCilent.getClient();
		QueryBuilder qBuilder=boolQuery().must(termQuery("clientId", entry.getClientId())).mustNot(termQuery("stockState", "2"));
		SearchResponse search = client.prepareSearch(index).setTypes(type).setPostFilter(qBuilder).execute().actionGet();
		Stock hastock = null;
		String stockState="0";
		for (SearchHit hit : search.getHits().getHits())
		{
			hastock = JSON.parseObject(hit.getSourceAsString(), Stock.class);
			if (Integer.parseInt(hastock.getStockState())>Integer.parseInt(stockState)) {
				stockState = hastock.getStockState();
			}
		}
		if (hastock !=null) {
			entry.setStockId(hastock.getStockId());
			entry.setModifyTime(System.currentTimeMillis());
			entry.setStockState(hastock.getStockState());
			UpdateRequest  update = new UpdateRequest();
			update.index(index).type(type).id(entry.getStockId()).doc(JSON.toJSONString(entry), XContentType.JSON);
			UpdateResponse resp=client.update(update).actionGet();
			return resp.getId();
		}else {
			entry.setCreateTime(System.currentTimeMillis());
			entry.setStockId(System.currentTimeMillis()+RandomStringUtils.randomNumeric(7));
			IndexResponse response = client.prepareIndex(index, type,entry.getStockId()).setSource(JSON.toJSONString(entry), XContentType.JSON).get();
			return response.getId();
		}
	}

	public long countByStockState(int stockState)
	{
		StatsAggregationBuilder builder = AggregationBuilders.stats("agg").field("stockId");
		QueryBuilder qBuilder=termQuery("stockState", stockState);
		TransportClient client = ElasticCilent.getClient();
		SearchResponse sr = client.prepareSearch(index).setTypes(type).setPostFilter(qBuilder).addAggregation(builder).get();
		Stats stats = sr.getAggregations().get("agg");
		return stats.getCount();
	}

	public int updateById(List<Stock> stocks)
	{
		TransportClient client = ElasticCilent.getClient();
		BulkRequestBuilder builder = client.prepareBulk().setRefreshPolicy("true");
		for(Stock stock : stocks)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stockState", stock.getStockState());
			map.put("modifyId", stock.getModifyId());
			map.put("modifyTime", System.currentTimeMillis());
			map.put("remark", stock.getRemark());
			builder.add(client.prepareUpdate(index, type, stock.getStockId()).setDoc(map));
		}
		BulkResponse response = builder.get();
		if (!response.hasFailures()) {
			return stocks.size();
		}
		return 0;
	}

	public List<Stock> getByKeys(List<String> keys) 
	{
		TransportClient client = ElasticCilent.getClient();
		MultiGetResponse response = client.prepareMultiGet().add(index, type, keys).get();
		MultiGetItemResponse[] items = response.getResponses();
		List<Stock> list = new ArrayList<Stock>();
		for(MultiGetItemResponse item : items)
		{
			GetResponse rs=item.getResponse();
			if (rs.isExists()) {
				list.add(JSON.parseObject(rs.getSourceAsString(),Stock.class));
			}
		}
		return list;
	}


	public List<GroupResult> queryStock() {
		TermsAggregationBuilder builder = AggregationBuilders.terms("group_pid").field("productId").size(20);
		SumAggregationBuilder subAgg = AggregationBuilders.sum("amountAgg").field("cruntAmount");
		builder.subAggregation(subAgg);
		QueryBuilder qBuilder=boolQuery().must(termQuery("stockState", "1")).must(rangeQuery("cruntAmount").gte(1));
		//QueryBuilder qBuilder=boolQuery().must(termQuery("stockState", "1"));
		TransportClient client = ElasticCilent.getClient();
		SearchResponse sr = client.prepareSearch(index).setTypes(type).setQuery(qBuilder).addAggregation(builder).get();
		LongTerms terms = sr.getAggregations().get("group_pid");  
		List<Bucket> buckets = terms.getBuckets();
		List<GroupResult> results = new ArrayList<GroupResult>();
		GroupResult result = null;
		for(Bucket bt : buckets)  
		{  
			Map<String, Aggregation> subaggmap = bt.getAggregations().asMap();
			double total_salary = ((InternalSum)subaggmap.get("amountAgg")).getValue();
			result = new GroupResult();
			result.setProductId(Integer.parseInt(bt.getKey().toString()));
			result.setCount((int)total_salary);
			results.add(result);
		}  
		return results;
	}

	public long countByCustom(StockParam param)
	{
		//StatsAggregationBuilder builder = AggregationBuilders.stats("agg_count").field("stockId");
		QueryBuilder qb = this.getBulder(param);
		TransportClient client = ElasticCilent.getClient();
		SearchResponse sr = client.prepareSearch(index).setTypes(type).setQuery(qb).setSize(0).get();//.addAggregation(builder)
		return sr.getHits().totalHits;
	}

	public List<Stock> customSelect(StockParam param) 
	{
		QueryBuilder qb = this.getBulder(param);
		TransportClient client = ElasticCilent.getClient();
		SearchResponse sr =null;
		if (!param.isPage()) {
			sr = client.prepareSearch(index).setTypes(type).setQuery(qb).execute().actionGet();
		}else {
			sr = client.prepareSearch(index).setTypes(type).setQuery(qb)
					.setFrom((param.getIndex()-1)*param.getPageSize()).setSize(param.getPageSize())
					.execute().actionGet();
		}
		List<Stock> list = new ArrayList<Stock>();
		for (SearchHit hit : sr.getHits().getHits())
		{
			list.add(JSON.parseObject(hit.getSourceAsString(), Stock.class));
		}
		return list;
	}

	public Stock getMaxClientId(long createId, int productId)
	{
		TransportClient client = ElasticCilent.getClient();
		QueryBuilder qb = boolQuery().must(termQuery("productId", productId)).must(termQuery("createId", createId));
		SearchResponse sr = client.prepareSearch(index).setTypes(type)
				.setPostFilter(qb).addSort("clientId.keyword",SortOrder.DESC).setSize(1)
				.execute().actionGet();
		Stock stock = null;
		for(SearchHit hit : sr.getHits()){
			stock = JSON.parseObject(hit.getSourceAsString(), Stock.class);
		}
		return stock;
	}

	private QueryBuilder getBulder(StockParam param)
	{
		BoolQueryBuilder qb = boolQuery();
		if (StringUtils.isNotEmpty(param.getClientId())) {
			qb.must(termQuery("clientId", param.getClientId()));
		}
		if (StringUtils.isNotEmpty(param.getOrderCode())) {
			qb.must(termQuery("orderCode", param.getOrderCode()));
		}
		if (StringUtils.isNotEmpty(param.getStockState())) {
			qb.must(termQuery("stockState", param.getStockState()));
		}
		if (StringUtils.isNotEmpty(param.getQualityState())) {
			qb.must(termQuery("qualityState", param.getQualityState()));
		}
		if (param.getStartTime()>0 && param.getEndTime()>0) {
			qb.must(rangeQuery("createTime").from(param.getStartTime()).to(param.getEndTime()));
		}
		if (StringUtils.isNotEmpty(param.getProductPro())) {
			qb.must(termQuery("productProperty", param.getProductPro()));
		}
		return qb;
	}


	public int updateProperty(List<Stock> stocks) 
	{
		TransportClient client = ElasticCilent.getClient();
		BulkRequestBuilder builder = client.prepareBulk().setRefreshPolicy("true");
		for(Stock stock : stocks)
		{
			builder.add(client.prepareUpdate(index, type, stock.getStockId()).setDoc(JSON.toJSONString(stock), XContentType.JSON));
		}
		BulkResponse response = builder.get();
		if (!response.hasFailures()) {
			return stocks.size();
		}
		return 0;
	}


	public int countNoPropty(String search) {
		TransportClient client = ElasticCilent.getClient();
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("productProperty",search));
		SearchResponse sr = client.prepareSearch(index).setTypes(type).setQuery(query).setSize(0).get();
		return (int)sr.getHits().totalHits;
	}
	
	public static void main(String[] args) 
	{
		StockParam param =new StockParam();
		//param.setClientId("99990000000001");
		List<Integer> pids = new ArrayList<Integer>();
		pids.add(13);
		param.setProductId(pids);
		StockService impl = new StockServiceImpl();
		//List<Stock> list = 
		Stock stock = impl.getMaxClientId(302l, 10);
		
	}
}
