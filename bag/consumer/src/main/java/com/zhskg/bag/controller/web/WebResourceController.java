package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ResourceEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.param.ResourceParam;
import com.zhskg.bag.service.ResourceService;
import com.zhskg.bag.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "web/resource/")
public class WebResourceController {
    @Reference(version = "1.0")
    private ResourceService resourceService;
    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键获取资源详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        try {
            return ReturnMapByBack.result(1, "获取成功！", resourceService.get(id));
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 获取资源分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param params 条件对象参数对象
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(Integer page, Integer rows, ResourceParam params) {
        try {
            Integer total = resourceService.getCount(params);
            if (total != null && total > 0) {
                return ReturnMapByBack.result(1, "获取成功！",resourceService.getPageList(page, rows, params),total);
            }
            return ReturnMapByBack.result(1, "获取成功！",null,total);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！",null,0);
        }
    }

    /**
     * 获取资源异步列表
     * @param id
     * @return
     */
    @RequestMapping(value = "asyn/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getAsynList(Long id) {
        try {
            if (id == null) id = 0l;
            List<ResourceEntry> list = resourceService.getAsyncTreeList(id);
            return ReturnMapByBack.result(1, "获取成功！", list,list.size());
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null,0);
        }
    }

    /**
     * 获取资源树
     * @return
     */
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getTree() {
        try {
            return resourceService.getTree();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取资源异步树
     * @param id
     * @return
     */
    @RequestMapping(value = "asyn/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<TreeNode> getAsynTree(Long id) {
        try {
            if (id == null) id = 0l;
            return resourceService.getAsyncTree(id);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 保存资源信息
     * @param data
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ResourceEntry data) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            if (loginInfo != null) {
                if (data != null) {
                    data.setCreateOn(loginInfo.getAccountId());
                    data.setCreateTime(System.currentTimeMillis());
                    data.setModifyTime(System.currentTimeMillis());
                    data.setModifyOn(loginInfo.getAccountId());
                    Long resourceId = resourceService.save(data);
                    if (resourceId != null && resourceId > 0) {
                        return  ReturnMapByBack.result(1, "保存成功！");
                    }
                }
            }
            return  ReturnMapByBack.result(0, "保存失败！");
        } catch (Exception ex) {
            return  ReturnMapByBack.result(0, "保存失败！");
        }
    }

    /**
     * 删除资源信息
     * @param id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(@PathVariable("id") Long id) {
        try {
            Integer num = resourceService.realRemoveById(id);
            if (num != null && num > 0) {
                return  ReturnMapByBack.result(1, "删除成功！");
            }
            return  ReturnMapByBack.result(0, "删除失败！");
        } catch (Exception ex) {
            return  ReturnMapByBack.result(0, "删除失败！");
        }
    }
}
