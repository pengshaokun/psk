package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.entity.AppVersion;
import com.zhskg.bag.mapper.HardwareVersionMapper;
import com.zhskg.bag.entity.HardwareVersion;
import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.model.HardwareVersionEntry;
import com.zhskg.bag.param.HardwareVersionParam;
import com.zhskg.bag.service.HardwareVersionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther:jean
 * @Date:2018/8/9
 * @Descripsion
 */
@Service
public class HardwareVersionServiceImpl implements HardwareVersionService {

    @Autowired
    HardwareVersionMapper hardwareVersionMapper;

    @Override
    public List<HardwareVersionEntry> getList(HardwareVersionParam param) {
//        int pageIndex = 0;
//        int pageSize = 0;
//        int start = (pageIndex == 0 ? 0 : pageIndex - 1) * pageSize;
//        map.put("start", start);
//        map.put("end", pageSize);
        List<HardwareVersionEntry> list = new ArrayList<>();
        Map map = JSON.parseObject(JSON.toJSONString(param));
        List<HardwareVersion> dtoList = hardwareVersionMapper.getList(map);
        if (dtoList != null && dtoList.size() > 0) {
            HardwareVersionEntry entry;
            for (HardwareVersion item : dtoList) {
                entry = new HardwareVersionEntry();
                BeanUtils.copyProperties(item, entry);
                entry.setCreateTimeStr(entry.getCreateTime().getTime());
                entry.setUpdateTimeStr(entry.getUpdateTime().getTime());
                list.add(entry);
            }
        }
        return list;
    }

    @Override
    public Integer updateById(HardwareVersionEntry entry) {
        if (entry != null) {
            HardwareVersion tdo = new HardwareVersion();
            BeanUtils.copyProperties(entry, tdo);
            int num = hardwareVersionMapper.updateById(tdo);
            if (num > 0) {
                return entry.getId();
            }
        }
        return 0;
    }

    @Override
    public Integer add(HardwareVersionEntry entry) {
        HardwareVersion tdo = new HardwareVersion();
        BeanUtils.copyProperties(entry, tdo);
        int num = hardwareVersionMapper.addAndId(tdo);
        if (num > 0) {
            return num;
        }
        return 0;
    }

    @Override
    public Integer save(HardwareVersionEntry entry) {
        Integer id = entry.getId();
        if (id != null && id > 0) {
            id = updateById(entry);
        } else {
            id = add(entry);
        }
        return id;
    }

    @Override
    public HardwareVersionEntry get(Integer id) {
        HardwareVersionEntry entry = new HardwareVersionEntry();
        HardwareVersion tdo = hardwareVersionMapper.get(id);
        BeanUtils.copyProperties(tdo, entry);
        return entry;
    }

    @Override
    public Integer remove(Integer id) {
        Integer num = hardwareVersionMapper.removeById(id);
        return num;
    }

    @Override
    public Boolean isExistCode(HardwareVersionParam param) {
        List<HardwareVersionEntry> list = new ArrayList<>();
        Map map = JSON.parseObject(JSON.toJSONString(param));
        List<HardwareVersion> dtoList = hardwareVersionMapper.getList(map);
        if (dtoList != null && dtoList.size() == 0) {
                return false;
        }
        return true;
    }
}
