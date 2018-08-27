package com.zhskg.bag.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.netty.model.BagData;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * Created by lwb on 2018/5/17.
 */
public class BatchHandle
{
    private static BulkProcessor bulkProcessor;
    private static String index="bagdata_db";
    private static String type ="bagdata_tb";
    static
    {
        TransportClient client = ElasticCilent.getClient();
        bulkProcessor = BulkProcessor.builder(client,new BulkProcessor.Listener(){

            public void beforeBulk(long executionId, BulkRequest request) {
                System.out.println("开始提交Actions的数量:"+request.numberOfActions());
            }

            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                System.out.println("处理过程中是否有错误:"+response.hasFailures());
            }

            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

            }//在5秒内，达到一千次提交一次请求，或者请求的数据量大小达到5M着提交一次，当都没达到，则在每5秒提交一次
        }).setBulkActions(2000) //We want to execute the bulk every 10 000 requests
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.MB)) //We want to flush the bulk every 5mb
                .setFlushInterval(TimeValue.timeValueSeconds(3)) //We want to flush the bulk every 5 seconds whatever the number of requests
                .setConcurrentRequests(5)
                .build();
    }

    public static int add(BagData object)
    {
        bulkProcessor.add( new IndexRequest(index, type, object.getId()).source(JSON.toJSONString(object),XContentType.JSON));
        return 1;
    }

    public static int sadd(BagData object)
    {
        TransportClient client = ElasticCilent.getClient();
        IndexResponse response = client.prepareIndex(index, type, object.getId()).setSource(JSON.toJSONString(object),XContentType.JSON).get();
        return 1;
    }
}
