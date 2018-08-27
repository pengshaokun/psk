package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.AccountRoleEntry;
import com.zhskg.bag.model.RoleEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.RoleParam;
import com.zhskg.bag.service.RoleService;
import com.zhskg.bag.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/role/")
public class WebRoleController {
    @Reference(version = "1.0")
    private RoleService roleService;
    @Autowired
    private LoginContext loginContext;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        try {
            return ReturnMapByBack.result(1, "获取成功！", roleService.get(id));
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 保存角色信息
     * @param data
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody RoleEntry data) {
        try{
           if(data!=null){
               LoginInfo loginInfo=loginContext.getInfoWeb();
               if(loginInfo!=null){
                   data.setCreateOn(loginInfo.getAccountId());
                   data.setModifyOn(loginInfo.getAccountId());
                   data.setCreateTime(System.currentTimeMillis());
                   data.setModifyTime(System.currentTimeMillis());
                   Long roleId = roleService.save(data);
                   if (roleId != null && roleId > 0) {
                       return ReturnMapByBack.result(1, "保存成功！");
                   }
               }
           }
            return ReturnMapByBack.result(0, "保存失败！");
        }catch (Exception ex){
            return ReturnMapByBack.result(0, "保存失败！");
        }
    }

    /**
     * 获取角色分页列表
     * @param page 页码
     * @param rows 每页记录条数
     * @param params 条件对象参数
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(Integer page, Integer rows, RoleParam params) {
        try {
            int total = roleService.getCount(params);
            if (total > 0) {
                return ReturnMapByBack.result(1, "获取成功！", roleService.getPageList(page, rows, params),total);
            }
            return ReturnMapByBack.result(1, "获取成功！",new ArrayList<RoleEntry>(),total);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(@PathVariable("id") Long id) {
        try {
            roleService.removeById(id);
            return ReturnMapByBack.result(1, "删除成功！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "删除失败！");
        }
    }

    /**
     * 获取角色列表
     * @param params 条件对象参数
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(RoleParam params) {
        try {
            List<RoleEntry> list = roleService.getList(params);
            return ReturnMapByBack.result(1, "获取成功！", list,list.size());
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 获取用户角色列表
     * @param accountId
     * @return
     */
    @RequestMapping(value = "list/account", method = RequestMethod.GET)
    @ResponseBody
    public Object getListByAccountId(Long accountId) {
        List<AccountRoleEntry> list = new ArrayList<>();
        try {
            list =  roleService.getRoleByAccountId(accountId,null);
            return ReturnMapByBack.result(1, "获取成功！", list,list.size());
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！",list,list.size());
        }
    }

    /**
     * 获取角色菜单树
     * @param roleId
     * @return
     */
    @RequestMapping(value = "menu/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getRoleMenuTree(Long roleId) {
        try {
            return roleService.getCheckedRoleMenu(roleId);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取资源树
     * @param roleId
     * @return
     */
    @RequestMapping(value = "resource/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getRoleResourceTree(Long roleId) {
        try {
            return roleService.getCheckedRoleResource(roleId);
        } catch (Exception ex) {
            return null;
        }
    }
}
