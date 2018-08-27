package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.enums.HardwareType;
import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.model.HardwareVersionEntry;
import com.zhskg.bag.param.AppVersionParam;
import com.zhskg.bag.param.HardwareVersionParam;
import com.zhskg.bag.service.HardwareVersionService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Auther:jean
 * @Date:2018/8/9
 * @Descripsion
 */
@Controller
@RequestMapping("web/hardware/version/")
public class WebHardwareVersionController {

    @Reference
    private HardwareVersionService hardwareVersionService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 获取版本信息列表
     *
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(HardwareVersionParam param) {

        List<HardwareVersionEntry> list = hardwareVersionService.getList(param);
        if (list != null && list.size() > 0) {
            return ReturnMapByBack.result(1, "获取成功！", list, list.size());
        }
        return ReturnMapByBack.result(0, "暂无记录！", list, list.size());
    }

//    @RequestMapping(value = "entry", method = RequestMethod.GET)
//    @ResponseBody
//    public Object getEntry(@RequestBody) {
//
//        List<HardwareVersionEntry> list = hardwareVersionService.getList(param);
//        if (list != null && list.size() > 0) {
//            return ReturnMapByBack.result(1, "获取成功！", list, list.size());
//        }
//        return ReturnMapByBack.result(0, "暂无记录！", list, list.size());
//    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable Integer id) {
        HardwareVersionEntry entry = hardwareVersionService.get(id);
        return ReturnMapByBack.result(1, "获取成功！", entry);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody HardwareVersionParam param) {

        Integer num;
        LoginInfo info = loginContext.getInfoWeb();
        if (info != null && info.getAccountId() != null) {
            HardwareVersionEntry entry = new HardwareVersionEntry();
            BeanUtils.copyProperties(param, entry);
            if (entry != null) {
                if (entry.getId() != null && entry.getId() > 0) {
                    entry.setUpdateTime(new Date());
                    num = hardwareVersionService.updateById(entry);
                } else {
                    entry.setOperator(info.getAccountId());
                    Integer type = entry.getType();
                    Integer number = entry.getVersionNumber();
                    entry.setVersionName(HardwareType.getName(type) + number);
                    entry.setUrl("");
                    entry.setCreateTime(new Date());
                    entry.setUpdateTime(new Date());
                    num = hardwareVersionService.add(entry);
                }
                if (num != null && num > 0) {
                    return ReturnMapByBack.result(1, "保存成功", num);
                }
                return ReturnMapByBack.result(0, "保存失败！", num);
            }
            return ReturnMapByBack.result(0, "提交信息有误!", 0);
        }
        return ReturnMapByBack.result(-1, "用户未登录，无权进行此操作!", 0);
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object removeById(@PathVariable Integer id) {
        HardwareVersionEntry entry = hardwareVersionService.get(id);
//        if (data.getDownloadUrl() != null && !"".equals(data.getDownloadUrl())) {
//            fileService.updateOverdueFlag(data.getDownloadUrl(), 1);
//        }
        Integer num = hardwareVersionService.remove(id);
        if (num > 0) {
            return ReturnMapByBack.result(1, "删除成功！");
        }
        return ReturnMapByBack.result(0, "删除失败！");
    }

    @RequestMapping(value = "check/code", method = RequestMethod.GET)
    @ResponseBody
    public Object isExistCode(String type, Integer versionNumber, Integer deleteFlag) {

        Integer t = HardwareType.getIndex(type);
        HardwareVersionParam param = new HardwareVersionParam();
        param.setType(t);
        param.setVersionNumber(versionNumber);
        param.setDeleteFlag(deleteFlag);
        Boolean isExist = hardwareVersionService.isExistCode(param);
        if (isExist == false) {
            return ReturnMapByBack.result(1, "");
        }else {
            return ReturnMapByBack.result(0, "",0);
        }
    }

}
