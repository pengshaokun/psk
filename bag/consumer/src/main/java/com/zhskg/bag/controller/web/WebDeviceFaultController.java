package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.DeviceFaultEntry;
import com.zhskg.bag.param.DeviceFaultParam;
import com.zhskg.bag.service.DeviceFaultService;
import com.zhskg.bag.service.WebDeviceFaultService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 故障申报
 */
@Controller
@RequestMapping(value = "web/device/fault/")
public class WebDeviceFaultController
{
    @Reference(version = "1.0")
    private DeviceFaultService faultService;

    @Autowired
    private LoginContext loginContext;
    @Autowired
    private WebDeviceFaultService webDeviceFaultService;

    /**
     * 设备故障申报信息修改
     * @param model
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody DeviceFaultEntry model){
        try{
        	int result = webDeviceFaultService.update(model);
//            int result =faultService.update(model);
            if(result>0) {
                return ReturnMapByBack.result(1, "修改成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "修改失败！");
    }

    /**
     * 设备故障申报信息分页列表
     * @param page 页码
     * @param rows 每页记录条数
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "page/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(int page,int rows,DeviceFaultParam param)
    {
        if( rows>0){
            int end= rows;
            int start= (page - 1) * rows;
            param.setStart(start);
            param.setEnd(end);
        }
        /*LoginInfo info = loginContext.getInfoApp();
        param.setUserId(info.getUserId());*/
        int count = faultService.getCount(param);
        List<DeviceFaultEntry> result =faultService.getList(param);
        return ReturnMapByBack.result(1, "success",result,count);
    }

    /**
     * 根据主键获取设备故障申报详细信息
     * @param id 主键i的
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id")int id){
        DeviceFaultEntry entry = faultService.get(id);
        return ReturnMapByBack.result(1, "success",entry);
    }

    /**
     * 根据主键id删除
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(@PathVariable("id") int id) {
        int count = faultService.remove(id);
        if(count>0){
            return  ReturnMapByBack.result(1,"删除成功");
        }
        return  ReturnMapByBack.result(0,"删除失败");
    }


}
