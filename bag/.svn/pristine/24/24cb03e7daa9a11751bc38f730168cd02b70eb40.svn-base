package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.Threshold;
import com.zhskg.bag.mapper.ThresholdMapper;
import com.zhskg.bag.model.ThresholdEntry;
import com.zhskg.bag.param.ThresholdParam;
import com.zhskg.bag.service.ThresholdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(version = "1.0")
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    ThresholdMapper thresholdMapper;

    public int addAndId(ThresholdEntry entry) {
        try {
            Threshold threshold = new Threshold();
            BeanUtils.copyProperties(entry, threshold);
            threshold.setCreateTime(System.currentTimeMillis());
            int num = thresholdMapper.addAndId(threshold);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int save(ThresholdEntry entry) {
        int num = 0;
        try {
            int id = entry.getId();
            if (id != 0) {
                num = update(entry);
            } else {
                num = addAndId(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public ThresholdEntry get(int id) {
        try {
            Threshold threshold = thresholdMapper.get(id);
            ThresholdEntry entry = new ThresholdEntry();
            BeanUtils.copyProperties(threshold, entry);
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(ThresholdEntry entry) {
        try {
            Threshold threshold = new Threshold();
            BeanUtils.copyProperties(entry, threshold);
            threshold.setModifyTime(System.currentTimeMillis());
            int num = thresholdMapper.update(threshold);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int remove(ThresholdParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            int num = thresholdMapper.remove(map);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public List<ThresholdEntry> getList(ThresholdParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            List<Threshold> list = thresholdMapper.getList(map);
            List<ThresholdEntry> entryList = new ArrayList<ThresholdEntry>();
            for (Threshold item : list) {
                ThresholdEntry entry = new ThresholdEntry();
                BeanUtils.copyProperties(item, entry);
                entryList.add(entry);
            }
            return entryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ThresholdEntry> getPageList(ThresholdParam param, int page, int rows) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            map.put("pageIndex", (page - 1) * rows);
            map.put("pageSize", rows);
            List<Threshold> list = thresholdMapper.getPageList(map);
            List<ThresholdEntry> entryList = new ArrayList<ThresholdEntry>();
            for (Threshold item : list) {
                ThresholdEntry entry = new ThresholdEntry();
                BeanUtils.copyProperties(item, entry);
                entryList.add(entry);
            }
            return entryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount(ThresholdParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            int num = thresholdMapper.getCount(map);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateSetFlag(ThresholdEntry entry) {
        Threshold threshold = new Threshold();
        BeanUtils.copyProperties(entry,threshold);
        int num = thresholdMapper.updateSetFlag(threshold);
        return num;
    }
}
