package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.zhskg.bag.data.CacheData;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.BagData;
import com.zhskg.bag.model.LostInfoEntry;
import com.zhskg.bag.param.BagDataParam;
import com.zhskg.bag.param.LostInfoParam;
import com.zhskg.bag.service.BagDataService;
import com.zhskg.bag.service.LostInfoService;
import com.zhskg.bag.util.DateFormat;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.RedisUtil;
import com.zhskg.bag.util.ReturnMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 挂失控制器
 */
@Controller
@RequestMapping(value = "app/lost/info/")
public class AppLostInfoController
{
    public static final String REDIS_KEY="bag:data:";

    @Reference(version = "1.0")
    private LostInfoService lostInfoService;

    @Autowired
    private LoginContext loginContext;

    @Reference(version = "1.0")
    private BagDataService bagDataService;



   /* @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LostInfoEntry entry) {
        try {
            if (StringUtils.isEmpty(entry.getClientId())){
                return ReturnMap.result(0, "设备编码不能为空！！");
            }
            LoginInfo loginInfo = loginContext.getInfoApp();
            if (loginInfo != null) {
                entry.setLostFlag(1);
                entry.setLostTime(System.currentTimeMillis());
                if (entry.getId() > 0) {
                    entry.setModifyOn(loginInfo.getUserId());
                    entry.setUserId(loginInfo.getUserId());
                } else {
                    entry.setFullName(loginInfo.getFullName());
                    entry.setUserId(loginInfo.getUserId());
                }
                int num = lostInfoService.save(entry);
                LostInfoParam param = new LostInfoParam();
                param.setClientId(entry.getClientId());
                param.setLostFlag(1);
                List<LostInfoEntry> list = lostInfoService.getList(param);
                if (list.size()>0) {
                    return ReturnMap.result(0, "该设备已经挂失过，请勿重复挂失！");
                }
                String dataJson = RedisUtil.get(REDIS_KEY+entry.getClientId());
                if (StringUtils.isEmpty(dataJson)){
                    CacheData cacheData=new CacheData();
                    cacheData.setClientId(entry.getClientId());
                    cacheData.setLossFlag(1);
                    cacheData.setParamFlag(0);
                    cacheData.setPhoneNumber(loginInfo.getMobileNumber());
                    RedisUtil.set(REDIS_KEY+entry.getClientId(),JSON.toJSONString(cacheData));
                }else {
                    JSONObject object = JSON.parseObject(dataJson);
                    object.put("lossFlag",1);
                    RedisUtil.set(REDIS_KEY+entry.getClientId(),object.toJSONString());
                }
                if (num > 0) {
                    return ReturnMap.result(1, "保存成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "保存失败");
    }*/


    /**
     * 保存挂失信息
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LostInfoEntry entry) {
        try {
            if (StringUtils.isEmpty(entry.getClientId())){
                return ReturnMap.result(0, "设备编码不能为空！！");
            }
            LoginInfo loginInfo = loginContext.getInfoApp();
            if (loginInfo != null) {
                //查询该设备是否有挂失信息
                LostInfoParam param = new LostInfoParam();
                param.setClientId(entry.getClientId());
                List<LostInfoEntry> list = lostInfoService.getList(param);
                if (list.size()>0 && list.get(0).getLostFlag()==1) return ReturnMap.result(0, "该设备已经挂失过，请勿重复挂失！");

                int operateFlag=0;
                if (list.size()>0) operateFlag=1;//flag=1 时 说明有挂失记录但状态为未挂失状态

                int num=0;
                if (operateFlag==1){
                    LostInfoEntry outLostInfo=list.get(0);
                    outLostInfo.setUserId(loginInfo.getUserId());//设置挂失用户id
                    outLostInfo.setFullName(loginInfo.getFullName());//设置挂失用户名
                    outLostInfo.setModifyOn(loginInfo.getUserId());
                    outLostInfo.setLostFlag(1);
                    num= lostInfoService.save(outLostInfo);
                }else{
                    LostInfoEntry newLostInfoEntry=new LostInfoEntry();
                    newLostInfoEntry.setClientId(entry.getClientId());
                    newLostInfoEntry.setLostFlag(1);
                    newLostInfoEntry.setModifyOn(loginInfo.getUserId());
                    newLostInfoEntry.setUserId(loginInfo.getUserId());
                    num= lostInfoService.save(newLostInfoEntry);
                }

                String dataJson = RedisUtil.get(REDIS_KEY+entry.getClientId());
                if (StringUtils.isEmpty(dataJson)){
                    CacheData cacheData=new CacheData();
                    cacheData.setClientId(entry.getClientId());
                    cacheData.setLossFlag(1);
                    cacheData.setParamFlag(0);
                    cacheData.setPhoneNumber(loginInfo.getMobileNumber());
                    RedisUtil.set(REDIS_KEY+entry.getClientId(),JSON.toJSONString(cacheData));
                }else {
                    JSONObject object = JSON.parseObject(dataJson);
                    object.put("lossFlag",1);
                    RedisUtil.set(REDIS_KEY+entry.getClientId(),object.toJSONString());
                }

                if (num > 0) {

                    return ReturnMap.result(1, "保存成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "保存失败");
    }

    /**
     * 撤销挂失
     * @param param 条件参数对象
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object setStatus(@RequestBody LostInfoParam param)
    {
        LoginInfo loginInfo = loginContext.getInfoApp();
        LostInfoEntry entry = lostInfoService.get(param.getId());
        if (entry==null || entry.getLostFlag()==0){
            return ReturnMap.result(0, "该设备未挂失，无法撤销！");
        }
        String dataJson = RedisUtil.get(REDIS_KEY+entry.getClientId());
        if (!StringUtils.isEmpty(dataJson)){
            JSONObject object = JSON.parseObject(dataJson);
            object.put("lossFlag",0);
            RedisUtil.set(REDIS_KEY+entry.getClientId(),object.toJSONString());
        }
        if (!loginInfo.getUserId().equals(entry.getUserId())){
            return ReturnMap.result(0, "撤销失败！");
        }
        int rs = lostInfoService.setStatus(param.getId(),0);
        if (rs>0){
            return ReturnMap.result(1, "撤销成功");
        }
        return ReturnMap.result(0, "撤销失败");
    }

    /**
     * 我申请的挂失
     */
    @RequestMapping(value = "apply", method = RequestMethod.GET)
    @ResponseBody
    public Object getLostInfo(LostInfoParam param)
    {
        if (StringUtils.isEmpty(param.getClientId())){
            return ReturnMap.result(0, "设备编码不能为空！！");
        }
        LoginInfo loginInfo = loginContext.getInfoApp();
        param.setUserId(loginInfo.getUserId());
        param.setLostFlag(1);
        List<LostInfoEntry> list = lostInfoService.getList(param);
        List<BagData> dataList = bagDataService.getLat(param.getClientId(),1,1);
        BagData bagData = new BagData();
        if (dataList.size()>0){
            bagData = dataList.get(0);
        }
        JSONObject object = new JSONObject();
        if (list.size()>0) {
            LostInfoEntry entry = list.get(0);
            object.put("clientId",entry.getClientId());
            object.put("content",entry.getLostContent());
            object.put("id",entry.getId());
            object.put("lon",bagData.getLon());
            object.put("lat",bagData.getLat());
            object.put("lostTime",entry.getLostTime());
            return ReturnMap.result(1, "success",object);
        }
        return ReturnMap.result(0, "no data");

    }

    /**
     * 查看最新位置
     * @param clientId 设备编码
     * @return
     */
    @RequestMapping(value = "getLat", method = RequestMethod.GET)
    @ResponseBody
    public Object getLast(@RequestParam  String clientId)
    {
        List<BagData> list = bagDataService.getLat(clientId,1,1);
        if (list.size()>0){
            BagData bagData = list.get(0);
            JSONObject object = new JSONObject();
            object.put("id",bagData.getId());
            object.put("clientId",bagData.getClientId());
            object.put("lon",bagData.getLon());
            object.put("lat",bagData.getLat());
            object.put("createTime", DateFormat.date2String(bagData.getCreateTime()));
            return ReturnMap.result(1, "success",object);
        }
        return ReturnMap.result(0, "no data");
    }

    /**
     * 查看最新位置列表
     * @param clientId 设备编码
     * @return
     */
    @RequestMapping(value = "getLatList", method = RequestMethod.GET)
    @ResponseBody
    public Object getLast(@RequestParam String clientId,@RequestParam int page,@RequestParam int size)
    {
        if (page<=0){
            page = 1;
        }
        BagDataParam param = new BagDataParam();
        param.setClientId(clientId);
        int count = bagDataService.count(param);
        if (count==0)return ReturnMap.result(1,"success",null,0);
        List<BagData> list = bagDataService.getLat(clientId,page,size);
        return ReturnMap.result(1, "success",list,count);
    }


}
