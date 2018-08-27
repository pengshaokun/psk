package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ThirdPartyInfoEntry;
import com.zhskg.bag.param.ThirdPartyInfoParam;
import com.zhskg.bag.service.ThirdPartyInfoService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 第三方登录
 */
@Controller
@RequestMapping(value = "web/third/party/")
public class WebThirdPartyInfoController {
    @Reference(version = "1.0")
    private ThirdPartyInfoService thirdPartyInfoService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 第三方登录信息保存
     * @param entry
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ThirdPartyInfoEntry entry){
        LoginInfo loginInfo = loginContext.getInfoWeb();
        if(entry.getOpenId()==null||entry.getOpenId().equals("")){
            entry.setUserId(loginInfo.getUserId());
            entry.setCreateOn(loginInfo.getUserId());
            entry.setCreateName(loginInfo.getFullName());
            entry.setCreateTime(System.currentTimeMillis());
        }
        boolean flag = thirdPartyInfoService.save(entry);
        if (flag){
            return ReturnMapByBack.result(1,"保存成功");
        }
        return ReturnMapByBack.result(0,"保存失败");
    }

    /**
     * 第三方登录信息列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(ThirdPartyInfoParam param) {
        List<ThirdPartyInfoEntry> list = new ArrayList<>();
        try {
            list = thirdPartyInfoService.getList(param);
            if(list.size()>0){
                return  ReturnMapByBack.result(1,"获取成功",list,list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

    /**
     * 第三方登录信息分页列表
     * @param param 条件参数对象
     * @param page 页码
     * @param rows 每页记录条数
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(ThirdPartyInfoParam param,int page,int rows) {
        List<ThirdPartyInfoEntry> list = new ArrayList<>();
        try {
            int count = thirdPartyInfoService.getCount(param);
            if(count>0){
                list = thirdPartyInfoService.getPageList(page,rows,param);
            }
            return  ReturnMapByBack.result(1,"获取成功",list,count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ReturnMapByBack.result(0,"获取失败",list,list.size());
    }

}