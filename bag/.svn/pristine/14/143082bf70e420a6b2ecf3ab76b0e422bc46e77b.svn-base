package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.ServerInfoEntry;
import com.zhskg.bag.param.ServerInfoParam;
import com.zhskg.bag.service.ServerInfoService;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "app/server/info/")
public class AppServerInfoController {
    @Reference(version = "1.0")
    private ServerInfoService serverInfoService;

    /**
     * 根据主键id获取服务信息详情
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        ServerInfoEntry entry = serverInfoService.get(id);
        if (entry != null) {
            return ReturnMap.result(1, "获取成功！！", entry);
        }
        return ReturnMap.result(0, "获取失败！！", null);
    }

    /**
     * 获取服务信息列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(ServerInfoParam param) {
        List<ServerInfoEntry> list = new ArrayList<>();
        list = serverInfoService.getList(param);
        if(list!=null && list.size()>0){
            return ReturnMap.result(1,"获取成功！！",list,list.size());
        }
        return ReturnMap.result(0,"获取失败！！！",list,list.size());
    }
}
