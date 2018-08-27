
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.RoleResource;
import com.zhskg.bag.param.RoleResourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface RoleResourceMapper {
    Integer add(RoleResource roleResource);
    
    Long addAndId(RoleResource roleResource);
    
    Integer batchAdd(List<RoleResource> roleResourceList);
    
    Integer removeById(@Param("id") Long id);
    
    Integer remove(RoleResourceParam condition);
    
    Integer realRemoveById(@Param("id") Long id);
    
    Integer realRemove(RoleResourceParam condition);
    
    RoleResource get(@Param("id") Long id);
    
    RoleResource getFirst(RoleResourceParam condition);
    
    List<RoleResource> getList(RoleResourceParam condition);
    
    List<RoleResource> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") RoleResourceParam condition);
    
    Integer getCount(RoleResourceParam condition);
    
    Integer updateById(RoleResource roleResource);
    
    Integer update(@Param("roleResource") RoleResource roleResource, @Param("condition") RoleResourceParam condition);
}