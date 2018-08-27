package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.AppVersion;
import com.zhskg.bag.mapper.AppVersionMapper;
import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.param.AppVersionParam;
import com.zhskg.bag.service.AppVersionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(version = "1.0")
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;

    public Integer add(AppVersionEntry data) {
        Integer id = 0;
        AppVersion entity = new AppVersion();
        BeanUtils.copyProperties(data, entity);
        int num = appVersionMapper.addAndId(entity);
        if (num > 0) {
            id = entity.getVersionId();
        }
        return id;
    }

    public Integer updateById(AppVersionEntry data) {
        Integer id = data.getVersionId();
        AppVersion entity = appVersionMapper.get(id);
        if (entity != null) {
            BeanUtils.copyProperties(data, entity);
            int num = appVersionMapper.updateById(entity);
            if (num > 0) {
                return id;
            }
        }
        return 0;
    }

    public Integer save(AppVersionEntry data) {
        Integer id = data.getVersionId();
        if (id != null && id > 0) {
            id = updateById(data);
        } else {
            id = add(data);
        }
        return id;
    }

    public Integer removeId(Integer versionId) {
        Integer num = appVersionMapper.removeById(versionId);
        return num;
    }

    public AppVersionEntry get(Integer versionId) {
        AppVersionEntry data = new AppVersionEntry();
        AppVersion entity = appVersionMapper.get(versionId);
        BeanUtils.copyProperties(entity, data);
        return data;
    }

    public List<AppVersionEntry> getList(AppVersionParam param) {
        List<AppVersionEntry> list = new ArrayList<AppVersionEntry>();
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        List<AppVersion> entityList = appVersionMapper.getList(map);
        if (entityList != null && entityList.size() > 0) {
            AppVersionEntry entry;
            for (AppVersion item : entityList) {
                entry = new AppVersionEntry();
                BeanUtils.copyProperties(item, entry);
                list.add(entry);
            }
        }
        return list;
    }

    public AppVersionEntry getNewestVersion(Integer systemCategory,Integer customerType) {
        AppVersionEntry data = new AppVersionEntry();
        AppVersion entity = appVersionMapper.getNewestVersion(systemCategory,customerType);
        BeanUtils.copyProperties(entity, data);
        return data;
    }

    public boolean isExistCode(Integer systemCategory, Integer versionCode,Integer customerType) {
        AppVersion entity = appVersionMapper.isExistCode(systemCategory, versionCode,customerType);
        if (entity != null) {
            return true;
        }
        return false;
    }
}
