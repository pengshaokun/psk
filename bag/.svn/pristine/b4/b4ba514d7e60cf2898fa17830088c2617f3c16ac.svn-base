
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.Area;
import com.zhskg.bag.param.AreaParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface AreaMapper {
    Integer add(Area area);
    
    Long addAndId(Area area);
    
    Integer batchAdd(List<Area> areaList);
    
    Integer removeById(@Param("areaId") Long areaId);
    
    Integer remove(AreaParam condition);
    
    Integer realRemoveById(@Param("areaId") Long areaId);
    
    Integer realRemove(AreaParam condition);
    
    Area get(@Param("areaId") Long areaId);
    
    Area getFirst(AreaParam condition);
    
    List<Area> getList(AreaParam condition);
    
    List<Area> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") AreaParam condition);
    
    Integer getCount(AreaParam condition);
    
    Integer updateById(Area area);
    
    Integer update(@Param("area") Area area, @Param("condition") AreaParam condition);

    Integer updatePath(@Param("path") String path, @Param("areaId") Long areaId);

    Integer removeByPath(@Param("path") String path);
}