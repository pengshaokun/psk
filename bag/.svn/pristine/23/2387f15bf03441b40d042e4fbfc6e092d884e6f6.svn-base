
package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Menu;
import com.zhskg.bag.mapper.MenuMapper;
import com.zhskg.bag.model.MenuEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.MenuParam;
import com.zhskg.bag.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Service(version = "1.0")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    private TreeNode getTreeNode(Menu entity) {
        TreeNode node = new TreeNode();
        if (entity != null) {
            node.setId(entity.getMenuId());
            node.setText(entity.getMenuName());
            node.setState(entity.getTotal() > 0 ? "closed" : "open");
            MenuEntry entry = new MenuEntry();
            BeanUtils.copyProperties(entity, entry);
            node.setAttributes(entry);
        }
        return node;
    }

    private List<TreeNode> GetChildren(Long parentId, List<Menu> source) {
        List<TreeNode> rslt = new ArrayList<>();
        List<Menu> current = new ArrayList<>();
        source.forEach((entity) -> {
            if (entity.getParentId().equals(parentId)) {
                current.add(entity);
            }
        });
        if (current.size() > 0) {
            current.forEach((entity) -> {
                TreeNode node = new TreeNode();
                List<TreeNode> children = GetChildren(entity.getMenuId(), source);
                if (entity != null) {
                    node.setId(entity.getMenuId());
                    node.setText(entity.getMenuName());
                    node.setState(children.size() > 0 ? "closed" : "open");
                    node.setChecked(entity.getTotal() > 0 ? true : false);
                    node.setChildren(children);
                    MenuEntry entry = new MenuEntry();
                    BeanUtils.copyProperties(entity, entry);
                    node.setAttributes(entry);
                }
                rslt.add(node);
            });
        }
        return rslt;
    }

    public Long addAndId(MenuEntry entry) {
        //Menu entity=getEntity(data);
        Menu entity = new Menu();
        BeanUtils.copyProperties(entry, entity);
        entity.setLevel(Integer.parseInt(String.valueOf(entry.getParentLevel() == null ? 0 : entry.getParentLevel() + 1)));
        menuMapper.addAndId(entity);
        return entity.getMenuId();
    }

    public Integer batchAdd(List<MenuEntry> menuList) {
        List<Menu> list = new ArrayList<Menu>();
        Menu entity;
        for (MenuEntry item : menuList) {
            entity = new Menu();
            BeanUtils.copyProperties(item, entity);
            entity.setLevel(Integer.parseInt(String.valueOf(item.getParentLevel() == null ? 0 : item.getParentLevel() + 1)));
            list.add(entity);
        }
        // menuList.forEach((data) -> list.add(getEntity(data)));
        return menuMapper.batchAdd(list);
    }

    public Integer removeById(Long menuId) {
        Menu menu = menuMapper.get(menuId);
        if (menu != null) {
            return menuMapper.removeByPath(menu.getPath());
        } else {
            return 0;
        }
    }

    public Integer remove(MenuParam condition) {
        return menuMapper.remove(condition);
    }

    public Integer realRemoveById(Long menuId) {
        return menuMapper.realRemoveById(menuId);
    }

    public Integer realRemove(MenuParam condition) {
        return menuMapper.realRemove(condition);
    }

    public MenuEntry get(Long menuId) {
        Menu entity = menuMapper.get(menuId);
        MenuEntry entry = new MenuEntry();
        BeanUtils.copyProperties(entity, entry);
        entry.setState(entity.getTotal() > 0 ? "closed" : "open");
        Menu menu = menuMapper.get(entity.getParentId());
        if (menu != null) {
            entry.setParentLevel(menu.getLevel());
            entry.setParentPath(menu.getPath());
        }
        return entry;
    }

    public MenuEntry getFirst(MenuParam condition) {
        Menu entity = menuMapper.getFirst(condition);
        MenuEntry entry = new MenuEntry();
        BeanUtils.copyProperties(entity, entry);
        entry.setState(entity.getTotal() > 0 ? "closed" : "open");
        Menu menu = menuMapper.get(entity.getParentId());
        if (menu != null) {
            entry.setParentLevel(menu.getLevel());
            entry.setParentPath(menu.getPath());
        }
        return entry;
    }

    public List<MenuEntry> getList(MenuParam condition) {
        List<MenuEntry> list = new ArrayList<MenuEntry>();
        List<Menu> menuList = menuMapper.getList(condition);
        //menuList.forEach((entity)->list.add(getEntry(entity)));
        MenuEntry entry;
        for (Menu item: menuList) {
            entry = new MenuEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<MenuEntry> getPageList(Integer pageIndex, Integer pageSize, MenuParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<MenuEntry> list = new ArrayList<MenuEntry>();
        List<Menu> menuList = menuMapper.getPageList(pageIndex, pageSize, condition);
        //menuList.forEach((entity) -> list.add(getEntry(entity)));
        MenuEntry entry;
        for (Menu item: menuList) {
            entry = new MenuEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<MenuEntry> getAsyncTreeList(Long parentId) {
        MenuParam menuParam = new MenuParam();
        menuParam.setParentId(parentId);
        List<MenuEntry> list = new ArrayList<MenuEntry>();
        List<Menu> menuList = menuMapper.getList(menuParam);
       // menuList.forEach((entity) -> list.add(getEntry(entity)));
        MenuEntry entry;
        for (Menu item: menuList) {
            entry = new MenuEntry();
            BeanUtils.copyProperties(item,entry);
            entry.setState(item.getTotal() > 0 ? "closed" : "open");
            list.add(entry);
        }
        return list;
    }

    public List<TreeNode> getAsyncTree(Long parentId) {
        MenuParam menuParam = new MenuParam();
        menuParam.setParentId(parentId);
        List<TreeNode> list = new ArrayList<TreeNode>();
        List<Menu> menus = menuMapper.getList(menuParam);
        menus.forEach((entity) -> list.add(getTreeNode(entity)));
        return list;
    }

    public List<TreeNode> getTree() {
        MenuParam menuParam = new MenuParam();
        List<Menu> menus = menuMapper.getList(menuParam);
        return GetChildren(0L, menus);
    }

    public Integer getCount(MenuParam condition) {
        return menuMapper.getCount(condition);
    }

    public Long save(MenuEntry menuEntry) {
        // Menu entity = getEntity(menuData);
        Menu entity = new Menu();
        BeanUtils.copyProperties(menuEntry, entity);
        entity.setLevel(Integer.parseInt(String.valueOf(menuEntry.getParentLevel() == null ? 0 : menuEntry.getParentLevel() + 1)));
        if (entity.getMenuId() != null && entity.getMenuId().longValue() != 0) {
            menuMapper.updateById(entity);
        } else {
            menuMapper.addAndId(entity);
            ArrayList<String> path = new ArrayList<>();
            for (String i : menuEntry.getParentPath().split(",")) {
                if (!"".equals(i)) path.add(i);
            }
            path.add(entity.getMenuId().toString());
            menuMapper.updatePath(String.join(",", String.join(",", path)), entity.getMenuId());
        }
        return entity.getMenuId();
    }

    public Integer update(MenuEntry menuEntry, MenuParam condition) {
        Menu entity = new Menu();
        BeanUtils.copyProperties(menuEntry, entity);
        entity.setLevel(Integer.parseInt(String.valueOf(menuEntry.getParentLevel() == null ? 0 : menuEntry.getParentLevel() + 1)));
        return menuMapper.update(entity, condition);
    }
}