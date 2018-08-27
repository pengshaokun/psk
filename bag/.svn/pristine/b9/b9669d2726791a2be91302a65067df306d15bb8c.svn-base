package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ThresholdEntry;
import com.zhskg.bag.param.ThresholdParam;
import com.zhskg.bag.service.ThresholdService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/threshold/")
public class WebThresholdController {
    @Reference(version = "1.0")
    private ThresholdService thresholdService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键获取参数详细
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        try {
            ThresholdEntry entry = thresholdService.get(id);
            if(entry!=null){
                return ReturnMapByBack.result(1,"获取成功",entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0,"获取失败",null);
    }

    /**
     * 参数保存
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ThresholdEntry entry) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            if(loginInfo!=null){
                if (loginInfo != null) {
                    if (entry.getId() > 0) {
                        entry.setModifyOn(loginInfo.getUserId());
                        entry.setSetFlag(1);
                    } else {
                        entry.setCreateOn(loginInfo.getUserId());
                    }
                    int num = thresholdService.save(entry);
                    if (num > 0) {
                        return ReturnMapByBack.result(1, "保存成功");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "保存失败");
    }

    /**
     * 获取参数列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(ThresholdParam param) {
        List<ThresholdEntry> list = new ArrayList<>();
        try {
            list = thresholdService.getList(param);
            if(list.size()>0){
                return  ReturnMapByBack.result(1,"获取成功",list,list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 获取参数分页列表
     * @param param 条件参数对象
     * @param page 页码
     * @param rows 每页记录数
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(ThresholdParam param,int page,int rows) {
        List<ThresholdEntry> list = new ArrayList<>();
        try {
            int count = thresholdService.getCount(param);
            if(count>0){
                list = thresholdService.getPageList(param,page,rows);
                return  ReturnMapByBack.result(1,"获取成功",list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 删除参数
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(ThresholdParam param) {
        try {
            int count = thresholdService.remove(param);
            if(count>0){
                return  ReturnMapByBack.result(1,"删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"删除失败");
    }

    /**
     * 修改设置标记
     * @param entry
     * @return
     */
    @RequestMapping(value = "update/setFlag", method = RequestMethod.POST)
    @ResponseBody
    public Object updateSetFlag(ThresholdEntry entry) {
        try {
            int num = thresholdService.update(entry);
            if(num>0){
                return  ReturnMapByBack.result(1,"设置成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"设置失败");
    }


}
