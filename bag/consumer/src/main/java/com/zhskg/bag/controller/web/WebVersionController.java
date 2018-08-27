package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.VersionEntry;
import com.zhskg.bag.param.VersionParam;
import com.zhskg.bag.service.VersionService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Web 版本 Controller
 */
@Controller
@RequestMapping("web/version/")
public class WebVersionController {

    @Reference(version = "1.0")
    private VersionService versionService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 添加版本号
     * @param versionParam 客户端传过来的数据
     * @return object 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addVersion(@RequestBody VersionParam versionParam) {

        try {
            LoginInfo loginInfo = loginContext.getInfoWeb(); // 获取用户信息

            /** 数据非法处理 必填字段校验 */
//            if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录
//            else versionParam.setCreateor(loginInfo.getUserId()); // 设置userId
//        if (StringUtils.isEmpty(versionParam.getName())) return ReturnMapByBack.result(0, "版本号名称不能为空!");
//        if (StringUtils.isEmpty(versionParam.getNumber())) return ReturnMapByBack.result(0, "版本号不能为空!");
//        if (StringUtils.isEmpty(versionParam.getBinPath())) return ReturnMapByBack.result(0, "bin路径不能为空!");
//        if (versionParam.getType() == null) return ReturnMapByBack.result(0, "版本类型不能为空!");
//        if (StringUtils.isEmpty(versionParam.getDescription())) return ReturnMapByBack.result(0, "版本描述不能为空!");

            int flag = versionService.add(versionParam); // 执行添加

            if (flag > 0) {
                return ReturnMapByBack.result(1, "版本号添加成功!");
            } else {
                return ReturnMapByBack.result(0, "版本号添加失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据id删除版本
     * @param id 版本id
     * @return object 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteVersionById(@PathVariable("id") Long id) {
       try {
           LoginInfo loginInfo = loginContext.getInfoWeb(); // 获取用户信息
           if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

           int flag = versionService.delete(id); // 执行删除
           if (flag > 0) return ReturnMapByBack.result(1, "版本号删除成功!");
           else return ReturnMapByBack.result(0, "版本号删除失败!");
       } catch (Exception e) {
           return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
       }
    }

    /**
     * 根据id修改版本
     * @param versionParam 版本
     * @return object 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateVersionById(@RequestBody VersionParam versionParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb(); // 获取用户信息
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录
            if (versionParam.getId() == null) return ReturnMapByBack.result(0, "版本ID不能为空"); // 未登录

            int flag = versionService.update(versionParam); // 调service

            if (flag > 0) return ReturnMapByBack.result(1, "版本号修改成功!");
            else return ReturnMapByBack.result(0, "版本号修改失败!");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据id查看版本详情
     * @param id 版本号id
     * @return object 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "queryById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object queryVersionById(@PathVariable("id") Long id) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb(); // 获取用户信息
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

            List<VersionEntry> list = versionService.queryById(id); // 执行操作

            if (list.size() > 0) {
                return ReturnMapByBack.result(1, "获取版本号详情成功!", list);
            } else {
                return ReturnMapByBack.result(0, "获取版本号详情失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据参数获取版本信息列表
     * @param page 第几页
     * @param rows 页大小
     * @param versionParam 版本对象
     * @ahthor huchuan
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(Integer page, Integer rows, VersionParam versionParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb(); // 获取用户信息
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录
            List<VersionParam> list = versionService.query(page, rows, versionParam); // 执行查询操作
            if (list != null && list.size() > 0) {
                int count = versionService.queryAllVersionCount(versionParam); // 查询条目数
                if (count > 0) {
                    return ReturnMapByBack.result(1, "获取版本号成功!", list, count);
                } else {
                    return ReturnMapByBack.result(0, "获取版本号失败!");
                }
            } else {
                return ReturnMapByBack.result(0, "获取版本号失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }
}
