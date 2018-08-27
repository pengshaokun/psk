package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.Opinion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by heshuang on 2017-11-15.
 */
public interface OpinionMapper {
    Opinion get(@Param("opinionId") Integer opinionId);

    List<Opinion> getPageList(Map<String, Object> map);

    Integer addAndId(Opinion opinion);

    Integer updateById(Opinion opinion);

    Integer getCount(@Param("consultStatus") Integer consultStatus);
}
