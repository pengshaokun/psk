package com.zhskg.bag.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.LostInfoEntry;
import com.zhskg.bag.param.LostInfoParam;
import com.zhskg.bag.service.LostInfoService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;

@Controller
@RequestMapping(value = "web/lost/info/")
public class WebLostInfoController {
    @Reference(version = "1.0")
    private LostInfoService lostInfoService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键id获丢失信息详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        try {
            LostInfoEntry lostInfoEntry = lostInfoService.get(id);
            if (lostInfoEntry != null) {
                return ReturnMapByBack.result(1, "获取成功", lostInfoEntry);
            }
            return ReturnMapByBack.result(0, "获取失败", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "获取失败", null);
        }
    }

    /**
     * 获取丢失信息列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(LostInfoParam param) {
        List<LostInfoEntry> list = new ArrayList<>();
        try {
            list = lostInfoService.getList(param);
            if(list.size()>0){
                return  ReturnMapByBack.result(1,"获取成功",list,list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 获取丢失信息分页列表
     * @param param 条件参数对象
     * @param page 页码
     * @param rows 每页记录条数
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(LostInfoParam param,int page,int rows) {
        List<LostInfoEntry> list = new ArrayList<>();
        try {
            int count = lostInfoService.getCount(param);
            if(count>0){
                list = lostInfoService.getPageList(param,page,rows);
            }
            return  ReturnMapByBack.result(1,"获取成功",list,count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 根据条件删除丢失信息
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(LostInfoParam param) {
        try {
            int count = lostInfoService.remove(param);
            if(count>0){
                return  ReturnMapByBack.result(1,"删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"删除失败");
    }
    /**
     * 修改丢失信息状态
     * @param id
     * @param lostFlag
     * @return
     */
    @RequestMapping("updateLostFlag")
    @ResponseBody
    public Object updateLostFlag(@RequestBody LostInfoParam p){
    	return lostInfoService.updateLostFlag(p.getId(),p.getLostFlag());
    }
}
