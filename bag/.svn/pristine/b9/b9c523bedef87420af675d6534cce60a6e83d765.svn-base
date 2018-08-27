package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.AboutUsEntry;
import com.zhskg.bag.param.AboutUsParam;
import com.zhskg.bag.service.AboutService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于我们
 * Created by lwb on 2018/3/14.
 */
@Controller
@RequestMapping(value = "web/about/")
public class WebAboutController {
    @Reference(version = "1.0")
    private AboutService aboutService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键获取关于我们详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}")
    @ResponseBody
    public Object getById(@PathVariable("id") int id) {
        try {
            AboutUsEntry entry = aboutService.get(id);
            if (entry != null) {
                return ReturnMapByBack.result(1, "获取成功", entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "获取失败", null);
    }

    /**
     * 关于我们保存
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody AboutUsEntry entry) {
        try {
            int num = aboutService.save(entry);
            if (num > 0) {
                return ReturnMapByBack.result(1, "保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "保存失败");
    }

    /**
     * 根据条件获取关于我们列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Object getList(AboutUsParam param) {
        List<AboutUsEntry> list = new ArrayList<>();
        try {
            list = aboutService.getList(param);
            if(list.size()>0){
                return ReturnMapByBack.result(1,"获取成功",list,list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 根据主键id删除关于我们
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object removeById(@PathVariable("id") int id) {
        try {
            int num = aboutService.remove(id);
            if(num>0){
                return  ReturnMapByBack.result(1,"删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"删除失败");
    }
}
