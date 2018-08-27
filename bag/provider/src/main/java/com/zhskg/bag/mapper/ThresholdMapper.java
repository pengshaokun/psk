package com.zhskg.bag.mapper;
import com.zhskg.bag.entity.Threshold;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ThresholdMapper {

    int addAndId(Threshold threshold);

    Threshold get(@Param("id")int id);

    int update(Threshold threshold);

    int remove(Map<String, Object> map);

    List<Threshold> getList(Map<String, Object> map);

    List<Threshold> getPageList(Map<String, Object> map);

    int getCount(Map<String, Object> map);

    int updateSetFlag(Threshold threshold);
}
