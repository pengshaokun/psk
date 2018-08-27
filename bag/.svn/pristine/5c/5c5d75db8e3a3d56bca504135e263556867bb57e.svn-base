
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.Role;
import com.zhskg.bag.param.RoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface RoleMapper {
    Integer add(Role role);
    
    Long addAndId(Role role);
    
    Integer batchAdd(List<Role> roleList);
    
    Integer removeById(@Param("roleId") Long roleId);
    
    Integer remove(RoleParam condition);
    
    Integer realRemoveById(@Param("roleId") Long roleId);
    
    Integer realRemove(RoleParam condition);
    
    Role get(@Param("roleId") Long roleId);
    
    Role getFirst(RoleParam condition);

    Role getDefaultRoleByAccountId(@Param("accountId") Long accountId);
    
    List<Role> getList(RoleParam condition);
    
    List<Role> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") RoleParam condition);
    
    Integer getCount(RoleParam condition);
    
    Integer updateById(Role role);
    
    Integer update(@Param("role") Role role, @Param("condition") RoleParam condition);
}