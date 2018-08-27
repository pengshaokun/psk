package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.OpinionEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.service.OpinionService;
import com.zhskg.bag.service.UserService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.PushUtil;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/opinion/")
public class WebOpinionController {
    @Reference(version = "1.0")
    private OpinionService opinionService;
    @Reference(version = "1.0")
    private UserService userService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键获取意见反馈详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        OpinionEntry entry = opinionService.get(id);
        return ReturnMapByBack.result(1, "获取成功", entry);
    }

    /**
     * 意见反馈信息查阅
     * @param entry
     * @return
     */
    @RequestMapping(value = "consult", method = RequestMethod.POST)
    @ResponseBody
    public Object consult(@RequestBody OpinionEntry entry) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            int num = opinionService.consult(entry.getOpinionId(), loginInfo.getUserId());
            if (num > 0) {
            	OpinionEntry op = opinionService.get(entry.getOpinionId());
            	UserEntry user = userService.get(op.getUserId());
            	if(user != null)
            		PushUtil.pushByAlias("您的意见反馈已被查阅受理!","", user.getMobileNumber());
                return ReturnMapByBack.result(1, "查阅成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "查阅失败");
    }

    /**
     * 获取意见反馈信息分页列表
     * @param page 页码
     * @param rows 每页记录条数
     * @param consultStatus 查阅状态
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(int page, int rows, Integer consultStatus) {
        List<OpinionEntry> list = new ArrayList<>();
        try {
            int num = opinionService.getCount(consultStatus);
            if(num>0){
                list = opinionService.getPageList(page,rows,consultStatus);
            }
            return ReturnMapByBack.result(1,"获取成功",list,num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0,"获取失败",list,0);
    }
}
