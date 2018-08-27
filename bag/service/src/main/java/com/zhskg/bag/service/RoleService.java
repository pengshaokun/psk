
package com.zhskg.bag.service;

import com.zhskg.bag.model.AccountRoleEntry;
import com.zhskg.bag.model.Permission;
import com.zhskg.bag.model.RoleEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.RoleParam;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface RoleService {
    /**
     * 添加角色
     * @param roleEntry 要添加的角色信息
     * @return 添加的角色Id
     */
    Long addAndId(RoleEntry roleEntry);

    /**
     * 批量添加角色
     * @param roleList 要添加的角色信息列表
     * @return 添加的角色数
     */
    Integer batchAdd(List<RoleEntry> roleList);

    /**
     * 根据角色Id逻辑删除角色
     * @param roleId 角色Id
     * @return 逻辑删除的角色数
     */
    Integer removeById(Long roleId);

    /**
     * 根据查询条件逻辑删除角色
     * @param condition 查询条件
     * @return 逻辑删除的符合查询条件的角色数
     */
    Integer remove(RoleParam condition);

    /**
     * 根据角色Id物理删除角色
     * @param roleId 角色Id
     * @return 物理删除的角色数
     */
    Integer realRemoveById(Long roleId);

    /**
     * 根据查询条件物理删除角色
     * @param condition 查询条件
     * @return 物理删除的符合查询条件的角色数
     */
    Integer realRemove(RoleParam condition);

    /**
     * 根据角色Id获取角色
     * @param roleId 角色Id
     * @return 角色信息
     */
    RoleEntry get(Long roleId);

    /**
     * 根据查询条件获取第一个角色
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个角色
     */
    RoleEntry getFirst(RoleParam condition);

    /**
     * 根据查询条件获取角色列表
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的角色列表
     */
    List<RoleEntry> getList(RoleParam condition);

    /**
     * 根据查询条件获取第pageIndex页的角色列表(pageSize条)
     * @param pageIndex 页索引
     * @param pageSize 页大小
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的角色列表(pageSize条)
     */
    List<RoleEntry> getPageList(Integer pageIndex, Integer pageSize, RoleParam condition);

    /**
     * 根据查询条件获取角色数
     * @param condition 查询条件
     * @return 符合查询条件的角色数
     */
    Integer getCount(RoleParam condition);

    /**
     * 根据应用Id和账号Id获取其下的默认角色
     * @param accountId 账号Id
     * @return 应用Id和账号Id下的默认角色
     */
    RoleEntry getDefaultRoleByAccountId(Long accountId);

    /**
     * 根据应用Id获取其下的角色列表
     * @return 应用Id下的角色列表
     */
    List<RoleEntry> getRoleByAppId();

    /**
     * 根据应用Id和账号Id获取其下角色列表
     * @param accountId 账号Id
     * @param roleCode 角色编码
     * @return 应用Id和账号Id下的角色列表
     */
    List<AccountRoleEntry> getRoleByAccountId(Long accountId, String roleCode);

    /**
     * 获取应用Id下的所有菜单并选中角色Id的菜单
     * @param roleId 角色Id
     * @return 应用Id下的所有菜单其中角色Id的菜单会标记为选中
     */
    List<TreeNode> getCheckedRoleMenu(Long roleId);

    /**
     * 获取应用Id下的所有资源并选中角色Id的资源
     * @param roleId 角色Id
     * @return 应用Id下的所有资源其中角色Id的资源会标记为选中
     */
    List<TreeNode> getCheckedRoleResource(Long roleId);

    /**
     * 根据角色Id获取其下的菜单
     * @param adminFlag 系统管理员标记
     * @param roleId 角色Id
     * @return 角色Id下的菜单
     */
    List<TreeNode> getRoleMenu(Integer adminFlag,Long roleId);

    /**
     * 根据角色Id获取其下的资源
     * @param adminFlag 系统管理员标记
     * @param roleId 角色Id
     * @return 角色Id下的资源
     */
    List<Permission> getRoleResource(Integer adminFlag, Long roleId);

    /**
     * 添加或修改角色
     * @param roleEntry 要添加或修改的角色信息
     * @return 添加或修改的角色Id
     */
    Long save(RoleEntry roleEntry);

    /**
     * 根据查询条件更新角色
     * @param roleEntry 要更新的角色信息
     * @param condition 查询条件
     * @return 更新的角色数
     */
    Integer update(RoleEntry roleEntry, RoleParam condition);

    /**
     * 获取当前登录用户角色列表
     * @param accountId 账户id
     * @return
     * 角色列表
     */
    List<AccountRoleEntry> getAccountRoleList(Long accountId);
}