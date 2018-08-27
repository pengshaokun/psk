package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.Problem;
import com.zhskg.bag.mapper.ProblemMapper;
import com.zhskg.bag.model.ProblemEntry;
import com.zhskg.bag.param.ProblemParam;
import com.zhskg.bag.service.ProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/3/20.
 */
@Service(version = "1.0")
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<ProblemEntry> getList(ProblemParam param) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
        List<Problem> list = problemMapper.getList(map);
        List<ProblemEntry> entryList = new ArrayList<>();
        for (Problem model : list) {
            ProblemEntry entry = new ProblemEntry();
            BeanUtils.copyProperties(model, entry);
            entryList.add(entry);
        }
        return entryList;
    }

    @Override
    public List<ProblemEntry> getPageList(int page, int size, ProblemParam param) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
        map.put("start", (page - 1) * size);
        map.put("end",size);
        List<Problem> list = problemMapper.getList(map);
        List<ProblemEntry> entryList = new ArrayList<>();
        for (Problem model : list) {
            ProblemEntry entry = new ProblemEntry();
            BeanUtils.copyProperties(model, entry);
            entryList.add(entry);
        }
        return entryList;
    }

    public int add(ProblemEntry model) {
        int num = 0;
        try {
            Problem problem = new Problem();
            BeanUtils.copyProperties(model,problem);
            problem.setCreateTime(System.currentTimeMillis());
            num = problemMapper.add(problem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public int update(ProblemEntry model) {
        int num = 0;
        try {
            Problem problem = new Problem();
            BeanUtils.copyProperties(model,problem);
            num = problemMapper.updateById(problem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }


    public ProblemEntry get(int id) {
        Problem model = problemMapper.get(id);
        if (model != null) {
            ProblemEntry entry = new ProblemEntry();
            BeanUtils.copyProperties(model, entry);
            return entry;
        }
        return null;
    }

    public int count(ProblemParam param) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(param));
        int result = problemMapper.count(map);
        return result;
    }

    @Override
    public int remove(int id) {
        int num = problemMapper.removeById(id);
        return num;
    }
}
