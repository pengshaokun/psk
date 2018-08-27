
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.Resource;
import com.zhskg.bag.param.ResourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface ResourceMapper {
    Integer add(Resource resource);
    
    Long addAndId(Resource resource);
    
    Integer batchAdd(List<Resource> resourceList);
    
    Integer removeById(@Param("resourceId") Long resourceId);
    
    Integer remove(ResourceParam condition);
    
    Integer realRemoveById(@Param("resourceId") Long resourceId);
    
    Integer realRemove(ResourceParam condition);
    
    Resource get(@Param("resourceId") Long resourceId);
    
    Resource getFirst(ResourceParam condition);
    
    List<Resource> getList(ResourceParam condition);
    
    List<Resource> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") ResourceParam condition);
    
    Integer getCount(ResourceParam condition);

    List<Resource> getRoleResource(@Param("roleId") Long roleId);

    List<Resource> getCheckedRoleResource(@Param("roleId") Long roleId, @Param("condition") ResourceParam condition);

    Integer updateById(Resource resource);

    Integer update(@Param("resource") Resource resource, @Param("condition") ResourceParam condition);

    Integer updatePath(@Param("path") String path, @Param("resourceId") Long resourceId);

    Integer removeByPath(@Param("path") String path);
}