package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.service.AppVersionService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "app/appVersion/")
public class AppVersionController {
    @Reference(version = "1.0")
    private AppVersionService versionService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 获取app版本信息
     * @param platform app类型0是安卓，1是IOS
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestParam int platform) {
        try {
            AppVersionEntry data = versionService.getNewestVersion(platform, 0);
            return ReturnMap.result(1, "success", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "fail", null);
    }

}
