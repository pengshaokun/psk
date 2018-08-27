package com.zhskg.bag.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.DeviceFault;
import com.zhskg.bag.mapper.DeviceFaultMapper;
import com.zhskg.bag.model.DeviceFaultEntry;
import com.zhskg.bag.param.DeviceFaultParam;
import com.zhskg.bag.service.DeviceFaultService;

/**
 * Created by lwb on 2018/4/10.
 */
@Service(interfaceName = "com.zhskg.bag.service.DeviceFaultService", version = "1.0")
public class DeviceFaultServiceImpl implements DeviceFaultService
{
    @Autowired
    private DeviceFaultMapper faultMapper;

    public DeviceFaultEntry get(int id)
    {
        DeviceFault model = faultMapper.get(id);
        if (model!=null){
            DeviceFaultEntry entry = new DeviceFaultEntry();
            BeanUtils.copyProperties(model,entry);
            return entry;
        }
        return null;
    }

    public int add(DeviceFaultEntry model) {
        DeviceFault entry = new DeviceFault();
        BeanUtils.copyProperties(model,entry);
        return faultMapper.add(entry);
    }

    public int update(DeviceFaultEntry model)
    {
        DeviceFault entry = new DeviceFault();
        BeanUtils.copyProperties(model,entry);
        return faultMapper.update(entry);
    }

    public int remove(int id) {
        return faultMapper.remove(id);
    }

    public List<DeviceFaultEntry> getList(DeviceFaultParam param)
    {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
       /* map.put("start",param.getStart());
        map.put("end",param.getEnd());
        if (param.getUserId()>0){
            map.put("userId",param.getUserId());
        }*/
        List<DeviceFault> list = faultMapper.getList(map);
        List<DeviceFaultEntry> outs = new ArrayList<DeviceFaultEntry>();
        for (DeviceFault model : list){
            DeviceFaultEntry entry = new DeviceFaultEntry();
            BeanUtils.copyProperties(model,entry);
            outs.add(entry);
        }
        return outs;
    }

    public int getCount(DeviceFaultParam param) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
        /*Map<String,Object> map = new HashMap<String,Object>();
        if (param.getUserId()>0){
            map.put("userId",param.getUserId());
        }*/
        return faultMapper.getCount(map);
    }
}
