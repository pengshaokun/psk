package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.OpinionEntry;
import com.zhskg.bag.service.OpinionService;
import com.zhskg.bag.util.DateFormat;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "app/opinion/")
public class AppOpinionController {
    @Reference(version = "1.0")
    private OpinionService opinionService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 意见反馈
     * @param
     * @return
     */
    @RequestMapping(value = "commit", method = RequestMethod.POST)
    @ResponseBody
    @AllowAnonymous
    public Object add(@RequestBody OpinionEntry data) {
        if (StringUtils.isEmpty(data.getOptions())) {
            return ReturnMap.result(0, "反馈意见不能空");
        }
        if (data.getOptions().length() > 500) {
            return ReturnMap.result(0, "字数过长,限制在500字以内!");
        }
        LoginInfo loginInfo = loginContext.getInfoApp();
        if(StringUtils.isEmpty(data.getMobile())){
        	data.setMobile(loginInfo.getMobileNumber());
        }
        if(StringUtils.isEmpty(data.getFullName())){
        	data.setFullName(loginInfo.getAccount());
        }
        data.setCreateTime(System.currentTimeMillis()); // 创建时间
        data.setFullName(loginInfo.getFullName()); // 姓名
        data.setUserId(loginInfo.getUserId()); // id
        data.setMobile(loginInfo.getMobileNumber()); // 手机号码
        int result = opinionService.opinion(data);
        if (result > 0) {
            return ReturnMap.result(1, "反馈成功");
        }
        return ReturnMap.result(0, "反馈失败");
    }

    /**
     * 我的意见列表
     * @return
     */
    @RequestMapping(value = "my/options", method = RequestMethod.GET)
    @ResponseBody
    public Object myOptions() {
        LoginInfo loginInfo = loginContext.getInfoApp();
        List<OpinionEntry> list = opinionService.getByUser(1, 20, null, loginInfo.getUserId());
        List<JSONObject> objects = new ArrayList<>();
        for (OpinionEntry entry : list) {
            JSONObject object = new JSONObject();
            object.put("opinionId", entry.getOpinionId());
            object.put("options", entry.getOptions());
            object.put("ctime", DateFormat.date2String(entry.getCreateTime()));
            object.put("status", entry.getConsultStatus());
            objects.add(object);
        }
        return ReturnMap.result(1, "success", objects);
    }

    /**
     * 根据主键id获取我的意见详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        OpinionEntry entry = opinionService.get(id);
        return ReturnMap.result(1, "获取成功", entry);
    }

    /**
     * 修改查阅状态
     * @param entry
     * @return
     */
    @RequestMapping(value = "consult", method = RequestMethod.POST)
    @ResponseBody
    public Object consult(@RequestBody OpinionEntry entry) {
        try {
            LoginInfo loginInfo = loginContext.getInfoApp();
            int num = opinionService.consult(entry.getOpinionId(), loginInfo.getUserId());
            if (num > 0) {
                return ReturnMap.result(1, "查阅成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "查阅失败");
    }


}
