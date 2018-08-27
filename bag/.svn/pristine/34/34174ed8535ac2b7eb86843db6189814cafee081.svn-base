package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ProblemEntry;
import com.zhskg.bag.param.ProblemParam;
import com.zhskg.bag.service.ProblemService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "app/problem/")
public class AppProblemController {
    @Reference(version = "1.0")
    private ProblemService problemService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 常见问题列表
     * @param page 页码
     * @param size 每页记录条数
     * @return
     */
    @RequestMapping(value = "getAns",method = RequestMethod.GET)
    @ResponseBody
    public Object ans(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size)
    {
        ProblemParam param =new ProblemParam();
        param.setCxtType(0);
        List<JSONObject> objects = new ArrayList<JSONObject>();
        int count = problemService.count(param);
        if(count>0){
            List<ProblemEntry> list=problemService.getPageList(page,size,param);
            for (ProblemEntry entry : list)
            {
                JSONObject object = new JSONObject();
                object.put("id",entry.getId());
                object.put("contxt",entry.getContxt());
                object.put("title",entry.getTitle());
                object.put("createTime",entry.getCreateTime());
                object.put("type",entry.getCxtType());
                objects.add(object);
            }
        }
        return ReturnMap.result(1,"success",objects,count);
    }

    /**
     * 获取常见问题详情
     * @param id 主键id
     */
    @RequestMapping(value = "getAnsDetail/{id}")
    @ResponseBody
    public Object ansDetail(@PathVariable("id") int id)
    {
        ProblemEntry entry = problemService.get(id);
        JSONObject object = new JSONObject();
        object.put("id",entry.getId());
        object.put("contxt", entry.getContxt());
        object.put("title", entry.getTitle());
        object.put("type", entry.getCxtType());
        object.put("createTime",entry.getCreateTime());
        return ReturnMap.result(1, "success", object);
    }

    /**
     * 问题申报
     * @param data
     * @return
     */
    @RequestMapping(value = "declare",method = RequestMethod.POST)
    @ResponseBody
    public Object declare(@RequestBody ProblemEntry data)
    {
        if (StringUtils.isEmpty(data.getTitle()) || StringUtils.isEmpty(data.getContxt())){
            return ReturnMap.result(0, "申报设备和原因不能为空！");
        }
        LoginInfo loginInfo = loginContext.getInfoApp();
        data.setCxtType(2);//设备申报为2
        data.setCreateName(loginInfo.getFullName());
        data.setCreateOn(loginInfo.getUserId());
        int result =problemService.add(data);
        if (result>0) {
            return ReturnMap.result(1, "申报成功");
        }
        return ReturnMap.result(0, "申报失败");
    }

}
