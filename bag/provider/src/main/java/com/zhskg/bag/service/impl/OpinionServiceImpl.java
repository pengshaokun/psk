package com.zhskg.bag.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Opinion;
import com.zhskg.bag.mapper.OpinionMapper;
import com.zhskg.bag.model.OpinionEntry;
import com.zhskg.bag.service.OpinionService;
import com.zhskg.bag.util.CommonUtil;


@Service(version = "1.0")
public class OpinionServiceImpl implements OpinionService {
    @Autowired
    private OpinionMapper opinionMapper;

    /**
     * 获取投诉意见详情
     *
     * @param opinionId 主键id
     * @return
     */
    public OpinionEntry get(Integer opinionId) {
        OpinionEntry data = new OpinionEntry();
        try {
            Opinion opinion = opinionMapper.get(opinionId);
            BeanUtils.copyProperties(opinion,data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public List<OpinionEntry> getPageList(Integer page, Integer rows, Integer consultStatus) {
        List<OpinionEntry> list = new ArrayList<OpinionEntry>();
        try {
            Map<String, Object> map = CommonUtil.setPageParam(page, rows);
            if(consultStatus!=null){
                map.put("consultStatus", consultStatus);
            }
            List<Opinion> _list = opinionMapper.getPageList(map);
            if (_list != null && _list.size() > 0) {
                list = getEntryList(_list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Integer getCount(Integer consultStatus) {
        return opinionMapper.getCount(consultStatus);
    }

    public Integer opinion(OpinionEntry data) {
        Integer result;
        try {
            Opinion opinion = new Opinion();
            BeanUtils.copyProperties(data,opinion);
            opinion.setCreateTime(System.currentTimeMillis());
            opinion.setConsultStatus(0);
            result = opinionMapper.addAndId(opinion);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }


    public Integer consult(Integer opinionId, Long consultOn) {
        Integer result = 0;
        Opinion opinion = opinionMapper.get(opinionId);
        if (opinion != null) {
            opinion.setOpinionId(opinionId);
            opinion.setConsultTime(System.currentTimeMillis());
            opinion.setConsultStatus(1);
            opinion.setConsultOn(consultOn);
            result = opinionMapper.updateById(opinion);
        }
        return result;
    }


    public List<OpinionEntry> getByUser(Integer page, Integer rows, Integer consultStatus, long userId) {
        List<OpinionEntry> list = new ArrayList<>();
        try {
            Map<String, Object> map = CommonUtil.setPageParam(page, rows);
            if(consultStatus!=null){
                map.put("consultStatus", consultStatus);
            }
            map.put("userId",userId);
            List<Opinion> _list = opinionMapper.getPageList(map);
            if (_list != null && _list.size() > 0) {
               list = getEntryList(_list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private List<OpinionEntry> getEntryList(List<Opinion> list){
        List<OpinionEntry> _list = new ArrayList<>();
        OpinionEntry entry;
        for (Opinion item : list) {
            entry = new OpinionEntry();
            BeanUtils.copyProperties(item,entry);
            _list.add(entry);
        }
        return _list;
    }
}
