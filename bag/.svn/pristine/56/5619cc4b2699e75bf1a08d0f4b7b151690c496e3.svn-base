package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.AboutUsEntry;
import com.zhskg.bag.service.AboutService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * 关于我们
 * Created by lwb on 2018/3/14.
 */
@Controller
@RequestMapping(value = "app/about/")
public class AppAboutController {
    @Reference(version = "1.0")
    private AboutService aboutService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 获取关于我们
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public Object get() {
        try {
            AboutUsEntry entry = aboutService.getDetail();
            if (entry != null) {
                return ReturnMap.result(1, "success", entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return ReturnMap.result(0, "error", null);
    }
}
