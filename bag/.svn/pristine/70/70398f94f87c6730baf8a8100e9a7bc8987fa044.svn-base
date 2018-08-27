
package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.Menu;
import com.zhskg.bag.param.MenuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface MenuMapper {
    Integer add(Menu menu);
    
    Long addAndId(Menu menu);
    
    Integer batchAdd(List<Menu> menuList);
    
    Integer removeById(@Param("menuId") Long menuId);
    
    Integer remove(MenuParam condition);
    
    Integer realRemoveById(@Param("menuId") Long menuId);
    
    Integer realRemove(MenuParam condition);
    
    Menu get(@Param("menuId") Long menuId);
    
    Menu getFirst(MenuParam condition);
    
    List<Menu> getList(MenuParam condition);
    
    List<Menu> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") MenuParam condition);
    
    Integer getCount(MenuParam condition);

    List<Menu> getRoleMenu(@Param("roleId") Long roleId);

    List<Menu> getCheckedRoleMenu(@Param("roleId") Long roleId, @Param("condition") MenuParam condition);

    Integer updateById(Menu menu);

    Integer update(@Param("menu") Menu menu, @Param("condition") MenuParam condition);

    Integer updatePath(@Param("path") String path, @Param("menuId") Long menuId);

    Integer removeByPath(@Param("path") String path);
}