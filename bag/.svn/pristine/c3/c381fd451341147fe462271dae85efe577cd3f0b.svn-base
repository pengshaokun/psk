package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.LostInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LostInfoMapper {
    int addAndId(LostInfo lostInfo);

    LostInfo get(@Param("id")int id);

    int update(LostInfo lostInfo);

    int remove(Map<String, Object> map);

    List<LostInfo> getList(Map<String, Object> map);

    List<LostInfo> getPageList(Map<String, Object> map);

    int getCount(Map<String, Object> map);

    int setStatus(Map<String, Object> map);
}
