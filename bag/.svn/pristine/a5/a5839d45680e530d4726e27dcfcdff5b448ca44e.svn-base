package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.AppVersionModel;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.param.AppVersionParam;
import com.zhskg.bag.service.AppVersionService;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/appVersion/")
public class WebAppVersionController {
    @Reference(version = "1.0")
    private AppVersionService appVersionService;

    @Reference(version = "1.0")
    private FileService fileService;


    @Autowired
    private LoginContext loginContext;


    /**
     * 获取版本明细
     *
     * @param id 版本id
     * @return 版本对象
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable Integer id) {
        AppVersionEntry entry = appVersionService.get(id);
        return ReturnMapByBack.result(1, "获取成功！", entry);
    }

    /**
     * 删除版本信息
     *
     * @param id 版本id
     * @return Result
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object removeById(@PathVariable Integer id) {
        AppVersionEntry data = appVersionService.get(id);
        if (data.getDownloadUrl() != null && !"".equals(data.getDownloadUrl())) {
            fileService.updateOverdueFlag(data.getDownloadUrl(), 1);
        }
        Integer num = appVersionService.removeId(id);
        if (num > 0) {
            return ReturnMapByBack.result(1, "删除成功！");
        }
        return ReturnMapByBack.result(0, "删除失败！");
    }

    /**
     * 根据条件获取APP版本信息列表
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(AppVersionParam param) {
        List<AppVersionEntry> list = new ArrayList<>();
        list = appVersionService.getList(param);
        if (list != null && list.size() > 0) {
            return ReturnMapByBack.result(1, "获取成功！", list,list.size());
        }
        return ReturnMapByBack.result(0, "暂无记录！", list,list.size());
    }

    /**
     * 保存关于我们信息
     * @param model
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody AppVersionModel model) {
        Integer num = 0;
        Integer id;
        LoginInfo info = loginContext.getInfoWeb();
        if (info != null && info.getAccountId() != null) {
            AppVersionEntry entry = new AppVersionEntry();
            BeanUtils.copyProperties(model, entry);
            if (entry != null) {
                entry.setUpdateTime(System.currentTimeMillis());
                id = entry.getVersionId();
                if (id != null && id > 0) {
                    //修改
                    num = appVersionService.updateById(entry);
                } else {
                    //新增
                    entry.setCreateOn(info.getAccountId());
                    num = appVersionService.add(entry);
                }
                if (num != null && num > 0) {
                    if (id == null) {
                        //推送更新
                        //appVersionConsumer.save(model);
                        //设置文件不过期
                        if (entry.getDownloadUrl() != null && !"".equals(entry.getDownloadUrl())) {
                            fileService.updateOverdueFlag(entry.getDownloadUrl(), 0);
                        }
                    }
                    return ReturnMapByBack.result(1, "保存成功!", num);
                }
                return ReturnMapByBack.result(0, "保存失败!", num);
            }
            return ReturnMapByBack.result(0, "提交信息有误!", 0);
        }
        return ReturnMapByBack.result(-1, "用户未登录，无权进行此操作!", 0);

    }

    /**
     * 检查版本编号是否已经存在
     * @param systemCategory 系统类别
     * @param versionCode 版本编号
     * @param customerType 经销商类型预留字段
     * @return
     */
    @RequestMapping(value = "check/code", method = RequestMethod.GET)
    @ResponseBody
    public boolean isExistCode(Integer systemCategory, Integer versionCode,Integer customerType) {
        boolean flag = appVersionService.isExistCode(systemCategory, versionCode,customerType);
        return flag;
    }
}
