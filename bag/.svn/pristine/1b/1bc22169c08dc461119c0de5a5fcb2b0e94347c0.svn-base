
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.RoleMenu;
import com.zhskg.bag.param.RoleMenuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface RoleMenuMapper {
    Integer add(RoleMenu roleMenu);
    
    Long addAndId(RoleMenu roleMenu);
    
    Integer batchAdd(List<RoleMenu> roleMenuList);
    
    Integer removeById(@Param("id") Long id);
    
    Integer remove(RoleMenuParam condition);
    
    Integer realRemoveById(@Param("id") Long id);
    
    Integer realRemove(RoleMenuParam condition);
    
    RoleMenu get(@Param("id") Long id);
    
    RoleMenu getFirst(RoleMenuParam condition);
    
    List<RoleMenu> getList(RoleMenuParam condition);
    
    List<RoleMenu> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") RoleMenuParam condition);
    
    Integer getCount(RoleMenuParam condition);
    
    Integer updateById(RoleMenu roleMenu);
    
    Integer update(@Param("roleMenu") RoleMenu roleMenu, @Param("condition") RoleMenuParam condition);
}