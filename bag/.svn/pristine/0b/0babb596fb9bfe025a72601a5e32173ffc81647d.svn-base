package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.UserDevice;
import com.zhskg.bag.mapper.UserDeviceMapper;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.param.UserDeviceParam;
import com.zhskg.bag.service.UserDeviceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/5/11.
 */
@Service(interfaceName = "com.zhskg.bag.service.UserDeviceService", version = "1.0")
public class UserDeviceServiceImpl implements UserDeviceService
{
    @Autowired
    private UserDeviceMapper userDeviceMapper;

    public int add(UserDeviceEntry model) {
        model.setCreateTime(System.currentTimeMillis());
        model.setDefaultFlag(0);
        UserDevice item = new UserDevice();
        BeanUtils.copyProperties(model,item);
        return userDeviceMapper.add(item);
    }

    public int removeDevice(int id)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("userId",0);
        return userDeviceMapper.update(map);
    }

    public int remvoeReal(int id) {
        return userDeviceMapper.deleteReal(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int setDefaultFlag(int id)
    {
        try {
            UserDevice model = userDeviceMapper.get(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", model.getUserId());
            map.put("defaultFlag", 0);
         //   map.put("id",id);
            userDeviceMapper.setDefault(map);
            Map<String, Object> up = new HashMap<String, Object>();
            up.put("id", id);
            up.put("defaultFlag", 1);
            return userDeviceMapper.update(up);
        }catch (Exception ex){
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }

    public int updateRemark(int id, String remark) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("deviceName",remark);
        return userDeviceMapper.update(map);
    }

    public UserDeviceEntry getById(int id) {
        UserDevice model = userDeviceMapper.get(id);
        UserDeviceEntry entry = new UserDeviceEntry();
        BeanUtils.copyProperties(model,entry);
        return entry;
    }

    public List<UserDeviceEntry> getList(UserDeviceParam param) {
        Map<String,Object> map= map(param);
        if(param.end>0){
            map.put("start",param.start);
            map.put("end",param.end);
        }
        List<UserDevice> list = userDeviceMapper.getList(map);
        List<UserDeviceEntry> entryList=new ArrayList<>();
        for (UserDevice model : list){
            UserDeviceEntry entry=new UserDeviceEntry();
            BeanUtils.copyProperties(model,entry);
            entryList.add(entry);
        }
        return entryList;
    }

    public int count(UserDeviceParam param) {
        Map<String,Object> map = map(param);
        int num = userDeviceMapper.count(map);
        return num;
    }

    private Map<String,Object> map(UserDeviceParam param)
    {
        Map<String,Object> map=new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(param.getClientId())){
            map.put("clientId",param.getClientId());
        }
        if (param.getUserId() !=null){
            map.put("userId",param.getUserId());
        }
        if (param.getAuthType()!=null){
            map.put("authType",param.getAuthType());
        }
        if (StringUtils.isNotEmpty(param.getStockId())){
            map.put("stockId",param.getStockId());
        }
        if (param.getDefaultFlag() !=null){
            map.put("defaultFlag",param.getDefaultFlag());
        }
        return map;
    }

    public int update(UserDeviceEntry entry) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(entry));
        int num = userDeviceMapper.update(map);
        return num;
    }
}
