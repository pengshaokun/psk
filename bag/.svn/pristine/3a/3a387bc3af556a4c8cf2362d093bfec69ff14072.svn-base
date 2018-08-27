package com.zhsbag.test;

import com.alibaba.fastjson.JSON;
import com.zhskg.bag.model.Stock;
import com.zhskg.bag.param.StockParam;
import com.zhskg.bag.service.impl.StockServiceImpl;

import java.util.List;

/**
 * Created by lwb on 2018/5/30.
 */
public class StockTest
{
    public static void main(String[] args)
    {
      //add();
       //get();
    }

    public static void add()
    {//15289439572616837710
        StockServiceImpl stockService = new StockServiceImpl();
        Stock item = new Stock();
        item.setProductName("智慧式箱包");
        item.setClientId("00410000000005");
        item.setProductFactory("智慧式");
        item.setProductId(1600);
        item.setStockState("1");
        item.setAmount(1);
        item.setBatchNumber("ios-1111222");
        item.setMac("FE:80:E0:F1:25");
        item.setProductCode("2000");
        item.setPwd("123456");
        item.setCreateTime(System.currentTimeMillis());
        item.setCreateId(1l);
        System.out.println(stockService.add(item));
    }

    public static void get()
    {
        StockServiceImpl stockService = new StockServiceImpl();
        StockParam param = new StockParam();
        param.setClientId("00410000000005");
        List<Stock> list = stockService.customSelect(param);
        for (Stock item : list)
        {
            System.out.println(JSON.toJSONString(item));
        }
    }
}
