package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.model.BagData;
import com.zhskg.bag.param.BagDataParam;
import com.zhskg.bag.service.BagDataService;
import com.zhskg.bag.util.ElasticCilent;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by lwb on 2018/5/19.
 */
@Service(version = "1.0")
public class BagDataServiceImpl implements BagDataService
{
    public static final String index = "bagdata_db";
    public static final String type = "bagdata_tb";
    public List<BagData> getLat(String clientId, int page, int size)
    {
        List<BagData> list = new ArrayList<BagData>();
        TransportClient client = ElasticCilent.getClient();
        QueryBuilder qb = boolQuery().must(termQuery("clientId", clientId)).must(termQuery("tableId", 1));
        SearchResponse sr = client.prepareSearch(index).setTypes(type)
              //  .setPostFilter(qb).addSort("clientId.keyword", SortOrder.DESC).setSize(1)
                .setPostFilter(qb).addSort("createTime", SortOrder.DESC).setFrom((page - 1) * size).setSize(size)
                .execute().actionGet();
        BagData item = null;
        for(SearchHit hit : sr.getHits()){
            item = JSON.parseObject(hit.getSourceAsString(), BagData.class);
            list.add(item);
        }
        return list;
    }

    public List<BagData> getByDate(BagDataParam param) {
        List<BagData> list = new ArrayList<BagData>();
        TransportClient client = ElasticCilent.getClient();
        QueryBuilder qb = getBulder(param);
        SearchResponse sr = client.prepareSearch(index).setTypes(type)
                .setPostFilter(qb).addSort("createTime", SortOrder.DESC).setFrom(param.start).setSize(param.end)
                .execute().actionGet();
        BagData item = null;
        for(SearchHit hit : sr.getHits()){
            item = JSON.parseObject(hit.getSourceAsString(), BagData.class);
            list.add(item);
        }
        return list;
    }

    public int count(BagDataParam param) {
        TransportClient client = ElasticCilent.getClient();
        QueryBuilder qb = getBulder(param);
        SearchResponse sr = client.prepareSearch(index).setTypes(type).setQuery(qb).get();
        return (int)sr.getHits().getTotalHits();
    }

    private QueryBuilder getBulder(BagDataParam param)
    {
        BoolQueryBuilder qb = boolQuery();
        if (StringUtils.isNotEmpty(param.getClientId())) {
            qb.must(termQuery("clientId", param.getClientId()));
        }
        if (param.getAlarmFlag()!=null) {
            qb.must(termQuery("alarmFlag", param.getAlarmFlag()));
        }
        if (param.getFaultFlag() !=null) {
            qb.must(termQuery("faultFlag", param.getFaultFlag()));
        }
        if (param.getStartTime()>0 && param.getEndTime()>0) {
            qb.must(rangeQuery("createTime").from(param.getStartTime()).to(param.getEndTime()));
        }
        return qb;
    }
}
