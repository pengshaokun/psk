package com.zhsbag.test;

import com.alibaba.fastjson.JSON;
import com.zhskg.bag.model.BagData;
import com.zhskg.bag.param.BagDataParam;
import com.zhskg.bag.service.impl.BagDataServiceImpl;
import com.zhskg.bag.util.ElasticCilent;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lwb on 2018/6/20.
 */
public class BagDataTest
{
    public static void main(String[] args)
    {
        get();
      //  add();
    }


    public static void add()
    {
        BagData bagData = new BagData();
        bagData.setClientId("00410000000055");
        bagData.setCreateTime(System.currentTimeMillis());
        bagData.setIp("192.168.10.10");
        bagData.setAlarmFlag(1);
        bagData.setLat(new BigDecimal(102.56666666));
        bagData.setLon(new BigDecimal("24.12325111"));
        bagData.setPort(865);
        TransportClient client = ElasticCilent.getClient();
        IndexResponse response = client.prepareIndex("bagdata_db","bagdata_tb",bagData.getId()).setSource(JSON.toJSONString(bagData), XContentType.JSON).get();
        System.out.println(response.getId());
    }

    public static void get()
    {
        BagDataServiceImpl service=new BagDataServiceImpl();
        List<BagData> list =service.getLat("00410000000055",1,10);
        BagDataParam param = new BagDataParam();
        param.setClientId("00410000000055");

        System.out.println(service.count(param));
    }
}
