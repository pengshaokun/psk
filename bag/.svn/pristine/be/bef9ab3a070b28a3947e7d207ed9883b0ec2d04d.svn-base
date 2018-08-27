
package com.zhskg.bag.service;


import com.zhskg.bag.model.MenuEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.MenuParam;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface MenuService {
    /**
     * 添加菜单
     * @param menuEntry 要添加的菜单信息
     * @return 添加的菜单Id
     */
    Long addAndId(MenuEntry menuEntry);

    /**
     * 批量添加菜单
     * @param menuList 要添加的菜单信息列表
     * @return 添加的菜单数
     */
    Integer batchAdd(List<MenuEntry> menuList);

    /**
     * 根据菜单Id逻辑删除菜单
     * @param menuId 菜单Id
     * @return 逻辑删除的菜单数
     */
    Integer removeById(Long menuId);

    /**
     * 根据查询条件逻辑删除菜单
     * @param condition 查询条件
     * @return 逻辑删除的符合查询条件的菜单数
     */
    Integer remove(MenuParam condition);

    /**
     * 根据菜单Id物理删除菜单
     * @param menuId 菜单Id
     * @return 物理删除的菜单数
     */
    Integer realRemoveById(Long menuId);

    /**
     * 根据查询条件物理删除菜单
     * @param condition 查询条件
     * @return 物理删除的符合查询条件的菜单数
     */
    Integer realRemove(MenuParam condition);

    /**
     * 根据菜单Id获取菜单
     * @param menuId 菜单Id
     * @return 菜单信息
     */
    MenuEntry get(Long menuId);

    /**
     * 根据查询条件获取第一个菜单
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个菜单
     */
    MenuEntry getFirst(MenuParam condition);

    /**
     * 根据查询条件获取菜单列表
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的菜单列表
     */
    List<MenuEntry> getList(MenuParam condition);

    /**
     * 根据查询条件获取第pageIndex页的菜单列表(pageSize条)
     * @param pageIndex 页索引
     * @param pageSize 页大小
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第pageIndex页的菜单列表(pageSize条)
     */
    List<MenuEntry> getPageList(Integer pageIndex, Integer pageSize, MenuParam condition);

    /**
     * 根据应用Id和父级菜单Id异步获取下级菜单
     * @param parentId 父级菜单Id
     * @return 下级菜单
     */
    List<MenuEntry> getAsyncTreeList(Long parentId);

    /**
     * 根据应用Id和父级菜单Id异步获取下级菜单
     * @param parentId 父级菜单Id
     * @return 下级菜单
     */
    List<TreeNode> getAsyncTree(Long parentId);

    /**
     * 根据应用Id获取其下的所有菜单
     * @return 应用Id下所有菜单
     */
    List<TreeNode> getTree();

    /**
     * 根据查询条件获取菜单数
     * @param condition 查询条件
     * @return 符合查询条件的菜单数
     */
    Integer getCount(MenuParam condition);

    /**
     * 添加或修改菜单
     * @param menuEntry 要添加或修改的菜单信息
     * @return 添加或修改的菜单Id
     */
    Long save(MenuEntry menuEntry);

    /**
     * 根据查询条件更新菜单
     * @param menuEntry 要更新的菜单信息
     * @param condition 查询条件
     * @return 更新的菜单数
     */
    Integer update(MenuEntry menuEntry, MenuParam condition);
}