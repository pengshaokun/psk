package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.MenuEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.MenuParam;
import com.zhskg.bag.service.MenuService;
import com.zhskg.bag.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("web/menu/")
public class WebMenuController {
    @Reference(version = "1.0")
    private MenuService menuService;
    @Autowired
    private LoginContext loginContext;

    /**
     * 获取菜单信息
     *
     * @param id 菜单id
     * @return 菜单信息
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        try {
            return ReturnMapByBack.result(1, "获取成功！", menuService.get(id));
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 获取菜单列表
     *
     * @param param{}
     * @return 菜单列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(MenuParam param) {
        try {
            List<MenuEntry> list = menuService.getList(param);
            return ReturnMapByBack.result(0, "获取成功！",list ,list.size());
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 获取菜单分页列表
     *
     * @param page    页码
     * @param rows    条数
     * @param param{}
     * @return 菜单分页列表
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(Integer page, Integer rows, MenuParam param) {
        try {
            int total = menuService.getCount(param);
            if (total > 0) {
                return ReturnMapByBack.result(1, "获取成功！", menuService.getPageList(page, rows, param),total);
            }
            return ReturnMapByBack.result(1, "获取成功！",null, 0);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return 1：成功 0：失败
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object removeById(@PathVariable("id") Long id) {
        try {
            menuService.removeById(id);
            return ReturnMapByBack.result(1, "删除成功！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "删除失败！");
        }
    }

    /**
     * 保存菜单
     *
     * @param data{}
     * @return 1：成功 0：失败
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody MenuEntry data) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            if (loginInfo != null) {
                data.setCreateOn(loginInfo.getAccountId());
                data.setCreateTime(System.currentTimeMillis());
                data.setModifyOn(loginInfo.getAccountId());
                data.setModifyTime(System.currentTimeMillis());
                Long menuId = menuService.save(data);
                if (menuId != null && menuId > 0) {
                    return ReturnMapByBack.result(1, "保存成功！");
                }
            }
            return ReturnMapByBack.result(0, "保存失败！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "保存失败！");
        }
    }

    /**
     * 获取异步列表
     *
     * @param id {}
     * @return 列表
     */
    @RequestMapping(value = "asyn/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getAsynList(Long id) {
        try {
            if (id == null) id = 0l;
            List<MenuEntry> list = menuService.getAsyncTreeList(id);
            return ReturnMapByBack.result(0, "获取成功！", list,list.size());
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 获取树列表
     *
     * @return 列表
     */
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getTree() {
        try {
            List<TreeNode> list = menuService.getTree();
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取异步树列表
     *
     * @param id{}
     * @return 树列表
     */
    @RequestMapping(value = "asyn/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getAsynTree(Long id) {
        try {
            if (id == null) id = 0l;
            List<TreeNode> list = menuService.getAsyncTree(id);
            return list;
        } catch (Exception ex) {
            return null;
        }
    }
}
