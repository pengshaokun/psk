package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ServerInfoEntry;
import com.zhskg.bag.param.ServerInfoParam;
import com.zhskg.bag.service.ServerInfoService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/server/info/")
public class WebServerInfoController {
    @Reference(version = "1.0")
    private ServerInfoService serverInfoService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据主键id获取服务信息详细
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        ServerInfoEntry entry = serverInfoService.get(id);
        if (entry != null) {
            return ReturnMapByBack.result(1, "获取成功！！", entry);
        }
        return ReturnMapByBack.result(0, "获取失败！！", null);
    }

    /**
     * 保存服务信息
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ServerInfoEntry entry) {
        LoginInfo loginInfo = loginContext.getInfoWeb();
        if (loginInfo != null) {
            if (entry.getId() > 0) {
                entry.setModifyName(loginInfo.getFullName());
                entry.setModifyOn(loginInfo.getUserId());
            } else {
                entry.setCreateName(loginInfo.getFullName());
                entry.setCreateOn(loginInfo.getUserId());
            }
            int num = serverInfoService.save(entry);
            if (num > 0) {
                return ReturnMapByBack.result(1, "保存成功！！");
            }
            return ReturnMapByBack.result(0, "保存失败！！！");
        }
        return ReturnMapByBack.result(0, "保存失败，您尚未登录！！！");
    }

    /**
     * 删除服务信息
     * @param param
     * @return
     */
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(ServerInfoParam param) {
        int num = serverInfoService.remove(param);
        if(num>0){
            return ReturnMapByBack.result(1,"删除成功！！");
        }
        return ReturnMapByBack.result(0,"删除失败！！！");
    }

    /**
     * 获取服务信息列表
     * @param param 条件对象参数
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(ServerInfoParam param) {
        List<ServerInfoEntry> list = new ArrayList<>();
        list = serverInfoService.getList(param);
        if(list!=null && list.size()>0){
            return ReturnMapByBack.result(1,"获取成功！！",list,list.size());
        }
        return ReturnMapByBack.result(0,"获取失败！！！",list,list.size());
    }

}
