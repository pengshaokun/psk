package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.data.ServerConfig;
import com.zhskg.bag.model.Stock;
import com.zhskg.bag.param.StockParam;
import com.zhskg.bag.service.StockService;
import com.zhskg.bag.service.ThresholdService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.RedisUtil;
import com.zhskg.bag.util.ReturnMapByBack;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/5/28.
 */
@Controller
@RequestMapping(value = "web/stock/")
public class WebStockController
{
    @Reference(version = "1.0")
    private StockService stockService;

    @Reference(version = "1.0")
    private ThresholdService thresholdService;

    @Autowired
    private LoginContext loginContext;

    private static int productId = 1600;
    private static String productCode="3201";

    /**
     * 初始化设备
     * @param model
     * @return
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Stock model)
    {
        LoginInfo info = loginContext.getInfoWeb();
        model.setProductId(productId);
        model.setStockState("1");
        model.setAmount(1);
        model.setProductCode(productCode);
        model.setCreateId(info.getAccountId());
        model.setCreateTime(System.currentTimeMillis());
        model.setProductFactory("智慧式");
        String result =stockService.add(model);
        if (StringUtils.isNotEmpty(result)){
            return ReturnMapByBack.result(1,"success");
        }
        return ReturnMapByBack.result(0,"fail");
    }

    /**
     * 获取最大设备编码
     * @return
     */
    @RequestMapping(value = "/getMaxClientId",method = RequestMethod.GET)
    @ResponseBody
    public Object getMaxClientId()
    {
        LoginInfo info = loginContext.getInfoWeb();
        Stock stock = stockService.getMaxClientId(info.getAccountId(),productId);
        if (stock !=null){
            String clientId = stock.getClientId();
            String prev = clientId.substring(0,4);
            long max = Long.parseLong(clientId.substring(4,clientId.length()))+1;
            String result = prev+ StringUtils.leftPad(Long.toString(max),10,"0");
            return ReturnMapByBack.result(1,"success",result);
        }
        return ReturnMapByBack.result(0,"fail");
    }

  /*  @RequestMapping(value = "/getServerInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getBasic()
    {
       String json =  RedisUtil.get("bag:serverconfig");
       if (StringUtils.isEmpty(json)){
           return ReturnMapByBack.result(0,"fail");
       }
        return ReturnMapByBack.result(1,"success");
    }

    @RequestMapping(value = "/setConfig",method = RequestMethod.POST)
    @ResponseBody
    public Object setConfig(@RequestBody ServerConfig config)
    {
        LoginInfo info = loginContext.getInfoWeb();
        RedisUtil.set("bag:serverconfig", JSON.toJSONString(config));
        System.out.println("AccountId:"+info.getAccountId()+"config the server info:"+JSON.toJSONString(config));
        return ReturnMapByBack.result(1,"success");
    }*/

    /**
     * 获取库存设备列表
     * @param param 条件对象参数
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Object getList(StockParam param){
        List<Stock> list = new ArrayList<>();
        param.setPage(false);
        list = stockService.customSelect(param);
        if(list.size()>0){
            return ReturnMapByBack.result(1,"success",list,list.size());
        }
        return ReturnMapByBack.result(0,"fail",list,0);
    }

    /**
     * 获取库存设备分页列表
     * @param param 条件对象参数
     * @param page 页码
     * @param rows 每页记录条数
     * @return
     */
    @RequestMapping(value = "page/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getList(StockParam param,int page, int rows){
        List<Stock> list = new ArrayList<>();
        param.setPage(true);
        param.setPageSize(rows);
        param.setIndex(page);
        long num = stockService.countByCustom(param);
        if(num>0){
            list = stockService.customSelect(param);
            return ReturnMapByBack.result(1,"success",list,list.size());
        }
        return ReturnMapByBack.result(0,"fail",list,0);
    }

}
