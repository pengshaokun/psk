package com.zhskg.bag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.LostInfo;
import com.zhskg.bag.mapper.LostInfoMapper;
import com.zhskg.bag.model.LostInfoEntry;
import com.zhskg.bag.param.LostInfoParam;
import com.zhskg.bag.service.LostInfoService;
@Service(version = "1.0")
public class LostInfoServiceImpl implements LostInfoService {
    @Autowired
    LostInfoMapper lostInfoMapper;

    public int addAndId(LostInfoEntry entry) {
        try {
            LostInfo lostInfo = new LostInfo();
            BeanUtils.copyProperties(entry, lostInfo);
            lostInfo.setLostTime(System.currentTimeMillis());
            lostInfo.setModifyTime(System.currentTimeMillis());
            int num = lostInfoMapper.addAndId(lostInfo);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    public int save(LostInfoEntry entry) {
        int num = 0;
        try {
            int id = entry.getId();
            if (id > 0) {
                num = update(entry);
            } else {
                num = addAndId(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public LostInfoEntry get(int id) {
        try {
            LostInfo lostInfo = lostInfoMapper.get(id);
            LostInfoEntry entry = new LostInfoEntry();
            BeanUtils.copyProperties(lostInfo, entry);
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int setStatus(int id, int flag) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("lostFlag", flag);
            return lostInfoMapper.setStatus(map);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int update(LostInfoEntry entry) {
        try {
            LostInfo lostInfo = new LostInfo();
            BeanUtils.copyProperties(entry, lostInfo);
            lostInfo.setModifyTime(System.currentTimeMillis());
            Integer num = lostInfoMapper.update(lostInfo);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int remove(LostInfoParam param) {
        int num = 0;
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            num = lostInfoMapper.remove(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public List<LostInfoEntry> getList(LostInfoParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            List<LostInfo> list = lostInfoMapper.getList(map);
            List<LostInfoEntry> entryList = new ArrayList<>();
            for (LostInfo item : list) {
                LostInfoEntry entry = new LostInfoEntry();
                BeanUtils.copyProperties(item, entry);
                entryList.add(entry);
            }
            return entryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LostInfoEntry> getPageList(LostInfoParam param, int page, int rows) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            map.put("pageIndex", (page - 1) * rows);
            map.put("pageSize", rows);
            List<LostInfo> list = lostInfoMapper.getPageList(map);
            List<LostInfoEntry> entryList = new ArrayList<>();
            for (LostInfo item : list) {
                LostInfoEntry entry = new LostInfoEntry();
                BeanUtils.copyProperties(item, entry);
                entryList.add(entry);
            }
            return entryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount(LostInfoParam param) {
        try {
            Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
            int num = lostInfoMapper.getCount(map);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public Map<String, Object> updateLostFlag(Integer id, Integer lostFlag) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	LostInfo db = lostInfoMapper.get(id);
    	if(db == null){
    		map.put("code", 0);
    		map.put("msg", "未找到丢失信息");
    		return map;
    	}
    	db.setLostFlag(lostFlag);
    	lostInfoMapper.update(db);
    	map.put("code", 1);
		map.put("msg", "修改成功");
    	return map;
    }
}
