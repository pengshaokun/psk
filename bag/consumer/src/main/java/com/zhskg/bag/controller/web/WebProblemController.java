package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ProblemEntry;
import com.zhskg.bag.param.ProblemParam;
import com.zhskg.bag.service.ProblemService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/problem/")
public class WebProblemController {
    @Reference(version = "1.0")
    private ProblemService problemService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 常见问题保存
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody ProblemEntry entry) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            int num = 0;
            if (entry.getId() > 0) {
                num = problemService.update(entry);
            } else {
                entry.setCreateOn(loginInfo.getUserId());
                entry.setCreateName(loginInfo.getFullName());
                num = problemService.add(entry);
            }
            if (num > 0) {
                return ReturnMapByBack.result(1, "保存成功！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "保存失败！！！");
    }

    /**
     * 根据主键获取常见问题详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "get/{id}")
    @ResponseBody
    public Object getDetail(@PathVariable("id") int id) {
        try {
            ProblemEntry entry = problemService.get(id);
            if (entry != null) {
                return ReturnMapByBack.result(1, "success", entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "fail", null);
    }

    /**
     * 获取常见问题列表
     * @param param 条件对象参数
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Object getList(ProblemParam param) {
        List<ProblemEntry> list = new ArrayList<>();
        try {
            list = problemService.getList(param);
            if (list != null && list.size()>0) {
                return ReturnMapByBack.result(1, "success", list,list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "fail", list,0);
    }

    /**、
     * 获取常见问题分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param param 条件对象参数
     * @return
     */
    @RequestMapping(value = "page/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getList(int page,int rows,ProblemParam param) {
        List<ProblemEntry> list = new ArrayList<>();
        try {
            int count = problemService.count(param);
            if(count>0){
                list = problemService.getPageList(page,rows,param);
                return ReturnMapByBack.result(1, "success", list,count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "fail", list,0);
    }

    /**
     * 根据主键删除常见问题
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "remove/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object remove(@PathVariable("id")int id) {
        try {
            int num = problemService.remove(id);
            if(num>0){
                return ReturnMapByBack.result(1, "删除成功！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "删除失败！！");
    }

}
