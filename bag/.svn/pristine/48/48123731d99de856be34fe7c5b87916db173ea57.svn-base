package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.RepairPoint;
import com.zhskg.bag.mapper.RepairPointMapper;
import com.zhskg.bag.model.RepairPointEntry;
import com.zhskg.bag.param.RepairPointParam;
import com.zhskg.bag.service.RepairPointService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/4/10.
 */
@Service(interfaceName = "com.zhskg.bag.service.RepairPointService", version = "1.0", validation = "true")
public class RepairPointServiceImpl implements RepairPointService
{
    @Autowired
    private RepairPointMapper pointMapper;

    public RepairPointEntry get(int id) {
        RepairPoint model = pointMapper.get(id);
        if (model !=null){
            RepairPointEntry entry =new RepairPointEntry();
            BeanUtils.copyProperties(model,entry);
            return entry;
        }
        return null;
    }

    public int remove(int id) {
        return pointMapper.remove(id);
    }

    public int add(RepairPointEntry model) {
        RepairPoint entry =new RepairPoint();
        BeanUtils.copyProperties(model,entry);
        return pointMapper.add(entry);
    }

    public int updateById(RepairPointEntry model) {
        RepairPoint entry =new RepairPoint();
        BeanUtils.copyProperties(model,entry);
        return pointMapper.updateById(entry);
    }

    public List<RepairPointEntry> getList(RepairPointParam param)
    {
        Map<String,Object> map= new HashMap<>();
        if (StringUtils.isNotEmpty(param.getAreaCode())){
            map.put("areaCode",Integer.parseInt(param.getAreaCode()));
        }
        map.put("start",param.getStart());
        map.put("end",param.getEnd());
        List<RepairPoint> list = pointMapper.getList(map);
        List<RepairPointEntry> outs = new ArrayList<>();
        for(RepairPoint model : list){
            RepairPointEntry entry =new RepairPointEntry();
            BeanUtils.copyProperties(model,entry);
            outs.add(entry);
        }
        return outs;
    }

    public int getCount(RepairPointParam param) {
        Map<String,Object> map= new HashMap<>();
        if (StringUtils.isNotEmpty(param.getAreaCode())){
            map.put("areaCode",param.getAreaCode());
        }
        return pointMapper.getCount(map);
    }
}
