package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.param.UserDeviceParam;
import com.zhskg.bag.service.StockService;
import com.zhskg.bag.service.UserDeviceService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/user/device/")
public class WebUserDeviceController {
    @Reference(version = "1.0")
    private UserDeviceService userDeviceService;
    @Reference(version = "1.0")
    private StockService stockService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 获取用户设备分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(int page,int rows,UserDeviceParam param) {
        List<UserDeviceEntry> list = new ArrayList<>();
        try {
            param.start =(page - 1) * rows;
            param.end = page * rows;
            System.out.println(param.getUserId());
            int count = userDeviceService.count(param);
            if (count>0){
                list = userDeviceService.getList(param);
                return ReturnMapByBack.result(1,"获取成功",list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0,"获取失败",list,0);
    }

}
