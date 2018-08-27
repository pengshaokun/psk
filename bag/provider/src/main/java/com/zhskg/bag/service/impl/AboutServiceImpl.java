package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.AboutUs;
import com.zhskg.bag.mapper.AboutUsMapper;
import com.zhskg.bag.model.AboutUsEntry;
import com.zhskg.bag.param.AboutUsParam;
import com.zhskg.bag.service.AboutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/3/14.
 */
@Service(version = "1.0")
public class AboutServiceImpl implements AboutService
{
    @Autowired
    private AboutUsMapper aboutUsMapper;

    public Integer add(AboutUsEntry data) {
        Integer id = 0;
        AboutUs entity = new AboutUs();
        BeanUtils.copyProperties(data, entity);
        int num = aboutUsMapper.addAndId(entity);
        if (num > 0) {
            id = entity.getAboutId();
        }
        return id;
    }

    public Integer updateById(AboutUsEntry data) {
        Integer id = data.getAboutId();
        AboutUs entity = aboutUsMapper.get(id);
        if (entity != null) {
            BeanUtils.copyProperties(data, entity);
            int num = aboutUsMapper.updateById(entity);
            if (num > 0) {
                id = entity.getAboutId();
            } else {
                id = 0;
            }
        }
        return id;
    }

    public Integer save(AboutUsEntry data) {
        Integer id = data.getAboutId();
        if (id != null && id > 0) {
            id = updateById(data);
        } else {
            id = add(data);
        }
        return id;
    }

    public Integer remove(Integer aboutId) {
        Integer num = aboutUsMapper.removeById(aboutId);
        return num;
    }

    public AboutUsEntry get(Integer aboutId) {
        AboutUsEntry data = new AboutUsEntry();
        AboutUs entity = aboutUsMapper.get(aboutId);
        BeanUtils.copyProperties(entity, data);
        return data;
    }

    public AboutUsEntry getDetail() {
        AboutUsEntry data = null;
        AboutUs entity = aboutUsMapper.getDetail();
        if(entity!=null){
            data = new AboutUsEntry();
            BeanUtils.copyProperties(entity, data);
        }
        return data;
    }

    public List<AboutUsEntry> getList(AboutUsParam param) {
        List<AboutUsEntry> list = new ArrayList<AboutUsEntry>();
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        List<AboutUs> entityList = aboutUsMapper.getList(map);
        if (entityList != null && entityList.size() > 0) {
            AboutUsEntry entry;
            for (AboutUs item : entityList) {
                entry = new AboutUsEntry();
                BeanUtils.copyProperties(item, entry);
                list.add(entry);
            }
        }
        return list;
    }
}
