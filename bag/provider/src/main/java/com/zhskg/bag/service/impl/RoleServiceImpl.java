
package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.zhskg.bag.entity.*;
import com.zhskg.bag.mapper.*;
import com.zhskg.bag.model.*;
import com.zhskg.bag.param.*;
import com.zhskg.bag.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Service(interfaceName = "com.zhskg.bag.service.RoleService",version = "1.0")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AccountRoleMapper accountRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public List<TreeNode> getMenus(Long pid, List<Menu> source) {
        List<TreeNode> rslt = new ArrayList<TreeNode>();
        List<Menu> current = new ArrayList<>();
        source.forEach((entity) -> {
            if (entity.getParentId().equals(pid)) {
                current.add(entity);
            }
        });
        if (current.size() > 0) {
            current.forEach((entity) -> {
                TreeNode node = new TreeNode();
                List<TreeNode> children = getMenus(entity.getMenuId(), source);
                if (entity != null) {
                    node.setId(entity.getMenuId());
                    node.setText(entity.getMenuName());
                    node.setIconCls(entity.getIconCls());
                    node.setState(children.size() > 0 ? "closed" : "open");
                    node.setChecked(entity.getTotal() > 0 ? true : false);
                    node.setChildren(children);
                    MenuEntry entry =new MenuEntry();
                    BeanUtils.copyProperties(entity,entry);
                    node.setAttributes(entry);
                }
                rslt.add(node);
            });
        }
        return rslt;
    }

    private List<TreeNode> getResourcesTree(Long pid, List<Resource> source) {
        List<TreeNode> rslt = new ArrayList<TreeNode>();
        List<Resource> current = new ArrayList<>();
        source.forEach((entity) -> {
            if (entity.getParentId().equals(pid)) {
                current.add(entity);
            }
        });
        if (current.size() > 0) {
            current.forEach((entity) -> {
                TreeNode node = new TreeNode();
                List<TreeNode> children = getResourcesTree(entity.getResourceId(), source);
                if (entity != null) {
                    node.setId(entity.getResourceId());
                    node.setText(entity.getResourceName());
                    node.setState(children.size() > 0 ? "closed" : "open");
                    node.setChildren(children);
                    ResourceEntry entry = new ResourceEntry();
                    BeanUtils.copyProperties(entity,entry);
                    node.setAttributes(entry);
                }
                rslt.add(node);
            });
        }
        return rslt;
    }

    private List<Permission> getResources(Long roleId, List<Resource> list) {
        List<Permission> rslt = new ArrayList<>();
        list.forEach((entity) -> {
            Permission permission = new Permission();
            permission.setRoleId(roleId);
            permission.setResourceId(entity.getResourceId());
            permission.setResourceNo(entity.getResourceNo());
            permission.setResourceName(entity.getResourceName());
            permission.setParentId(entity.getParentId());
            permission.setPath(entity.getPath());
            permission.setLevel(entity.getLevel());
            permission.setSortNo(entity.getSortNo());
            permission.setCategory(entity.getCategory());
            permission.setCaption(entity.getCaption());
            permission.setUrl(entity.getUrl());
            permission.setMethod(entity.getMethod());
            rslt.add(permission);
        });
        return rslt;
    }

    public Long addAndId(RoleEntry data) {
        Role role = new Role();
        BeanUtils.copyProperties(data,role);
        roleMapper.addAndId(role);
        return role.getRoleId();
    }

    public Integer batchAdd(List<RoleEntry> roleList) {
        List<Role> list = new ArrayList<Role>();
        //roleList.forEach((data) -> list.add(getEntity(data)));
        Role role;
        for (RoleEntry item:roleList) {
            role = new Role();
            BeanUtils.copyProperties(item,role);
            list.add(role);
        }
        return roleMapper.batchAdd(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer removeById(Long roleId) {
        try {
            AccountRoleParam condition = new AccountRoleParam();
            RoleMenuParam roleMenuParam = new RoleMenuParam();
            RoleResourceParam roleResourceParam = new RoleResourceParam();
            condition.setRoleId(roleId);
            roleMenuParam.setRoleId(roleId);
            roleResourceParam.setRoleId(roleId);
            accountRoleMapper.realRemove(condition);
            roleMenuMapper.realRemove(roleMenuParam);
            roleResourceMapper.realRemove(roleResourceParam);
            return roleMapper.removeById(roleId);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    public Integer remove(RoleParam condition) {
        return roleMapper.remove(condition);
    }

    public Integer realRemoveById(Long roleId) {
        return roleMapper.realRemoveById(roleId);
    }

    public Integer realRemove(RoleParam condition) {
        return roleMapper.realRemove(condition);
    }

    public RoleEntry get(Long roleId) {
        Role role = roleMapper.get(roleId);
        RoleEntry entry = new RoleEntry();
        BeanUtils.copyProperties(role,entry);
        return entry;
    }

    public RoleEntry getFirst(RoleParam condition) {
        Role role = roleMapper.getFirst(condition);
        RoleEntry entry = new RoleEntry();
        BeanUtils.copyProperties(role,entry);
        return entry;
    }

    public List<RoleEntry> getList(RoleParam condition) {
        List<RoleEntry> list = new ArrayList<RoleEntry>();
        List<Role> roleList = roleMapper.getList(condition);
        //roleList.forEach((entity) -> list.add(getEntry(entity)));
        RoleEntry entry;
        for (Role item:roleList){
            entry = new RoleEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        return list;
    }

    public List<RoleEntry> getPageList(Integer pageIndex, Integer pageSize, RoleParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<RoleEntry> list = new ArrayList<RoleEntry>();
        List<Role> roleList = roleMapper.getPageList(pageIndex, pageSize, condition);
        //roleList.forEach((entity) -> list.add(getEntry(entity)));
        RoleEntry entry;
        for (Role item:roleList){
            entry = new RoleEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        return list;
    }

    public Integer getCount(RoleParam condition) {
        return roleMapper.getCount(condition);
    }

    public RoleEntry getDefaultRoleByAccountId(Long accountId) {
        Role role = roleMapper.getDefaultRoleByAccountId(accountId);
        if(role == null){
        	return null;
        }
        RoleEntry entry = new RoleEntry();
        BeanUtils.copyProperties(role,entry);
        return entry;
    }

    public List<RoleEntry> getRoleByAppId() {
        List<RoleEntry> list = new ArrayList<RoleEntry>();
        RoleParam condition = new RoleParam();
        List<Role> roleList = roleMapper.getList(condition);
        //roleList.forEach((entity) -> list.add(getEntry(entity)));
        RoleEntry entry;
        for (Role item:roleList){
            entry = new RoleEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        return list;
    }

    public List<AccountRoleEntry> getRoleByAccountId(Long accountId, String roleCode) {
        List<AccountRoleEntry> list = new ArrayList<>();
        List<AccountRole> accountRoles = accountRoleMapper.getRoleByAccountId(accountId, roleCode);
       // accountRoles.forEach((entity) -> list.add(getAccountRoleEntry(entity)));
        AccountRoleEntry entry;
        for(AccountRole item:accountRoles){
            entry = new AccountRoleEntry();
            BeanUtils.copyProperties(item,entry);
            list.add(entry);
        }
        return list;
    }

    public List<TreeNode> getCheckedRoleMenu(Long roleId) {
        List<TreeNode> list = new ArrayList<>();
        MenuParam condition = new MenuParam();
        List<Menu> menus = menuMapper.getCheckedRoleMenu(roleId, condition);
        //menus.forEach((entity) -> list.add(getMenuTreeNode(entity)));
        if (menus.size() > 0) {
            return getMenus(menus.get(0).getParentId(), menus);
        }
        return list;
    }

    public List<TreeNode> getCheckedRoleResource(Long roleId) {
        List<TreeNode> list = new ArrayList<>();
        ResourceParam condition = new ResourceParam();
        List<Resource> resources = resourceMapper.getCheckedRoleResource(roleId, condition);
        //resources.forEach((entity) -> list.add(getResourceTreeNode(entity)));
        if (resources.size() > 0) {
            return getResourcesTree(resources.get(0).getParentId(), resources);
        }
        return list;
    }

    public List<TreeNode> getRoleMenu(Integer adminFlag,Long roleId) {
        List<Menu> menus;
        if (adminFlag.equals(1)) {
            MenuParam menuParam = new MenuParam();
            menus = menuMapper.getList(menuParam);
        } else {
            menus = menuMapper.getRoleMenu(roleId);
        }
        if (menus.size() > 0) {
            return getMenus(menus.get(0).getParentId(), menus);
        }
        return null;
    }

    public List<Permission> getRoleResource(Integer adminFlag, Long roleId) {
        List<Resource> resources;
        if (adminFlag.equals(1)) {
            ResourceParam resourceParam = new ResourceParam();
            resources = resourceMapper.getList(resourceParam);
        } else {
            resources = resourceMapper.getRoleResource(roleId);
        }
        return getResources(roleId, resources);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long save(RoleEntry roleData) {
        try {
            Role entity = new Role();
            BeanUtils.copyProperties(roleData,entity);
            if (entity.getRoleId() != null && entity.getRoleId().longValue() != 0) {
                roleMapper.updateById(entity);
            } else {
                roleMapper.addAndId(entity);
            }
            if (roleData.menus != null && roleData.menus.size() > 0) {
                RoleMenuParam roleMenuParam = new RoleMenuParam();
                roleMenuParam.setRoleId(entity.getRoleId());
                roleMenuMapper.realRemove(roleMenuParam);
                List<RoleMenu> roleMenus = new ArrayList<>();
                for (MenuEntry menu : roleData.menus) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menu.getMenuId());
                    roleMenu.setRoleId(entity.getRoleId());
                    roleMenu.setChecked(menu.getChecked());
                    roleMenus.add(roleMenu);
                }
                roleMenuMapper.batchAdd(roleMenus);
            }
            if (roleData.resources != null && roleData.resources.size() > 0) {
                RoleResourceParam roleResourceParam = new RoleResourceParam();
                roleResourceParam.setRoleId(entity.getRoleId());
                roleResourceMapper.realRemove(roleResourceParam);
                List<RoleResource> roleResources = new ArrayList<>();
                for (ResourceEntry resource : roleData.resources) {
                    RoleResource roleResource = new RoleResource();
                    roleResource.setResourceId(resource.getResourceId());
                    roleResource.setRoleId(entity.getRoleId());
                    roleResources.add(roleResource);
                }
                roleResourceMapper.batchAdd(roleResources);
            }
            return entity.getRoleId();
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Integer update(RoleEntry roleData, RoleParam condition) {
        Role entity = new Role();
        BeanUtils.copyProperties(roleData,entity);
        return roleMapper.update(entity, condition);
    }

    public List<AccountRoleEntry> getAccountRoleList(Long accountId) {
        Map<String, Object> map = new HashMap<>();
        map.put("accountId", accountId);
        List<AccountRoleEntry> list = new ArrayList<>();
        List<AccountRole> accountRoles = accountRoleMapper.getAccountRoleList(map);
        if (accountRoles != null && accountRoles.size() > 0) {
           // accountRoles.forEach((entity) -> list.add(getAccountRoleEntry(entity)));
            AccountRoleEntry entry;
            for(AccountRole item:accountRoles){
                entry = new AccountRoleEntry();
                BeanUtils.copyProperties(item,entry);
                list.add(entry);
            }
        }
        return list;
    }
}