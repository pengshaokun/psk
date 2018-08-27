package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.ThresholdEntry;
import com.zhskg.bag.param.ThresholdParam;
import com.zhskg.bag.service.ThresholdService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "app/threshold/")
public class AppThresholdController {
    @Reference(version = "1.0")
    private ThresholdService thresholdService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 根据设备编码获取参数设备参数
     * @param clientId 设备编码
     * @return
     */
    @RequestMapping(value = "get/{clientId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("clientId") String clientId) {
        try {
           // ThresholdEntry entry = thresholdService.get(clientId);
            ThresholdParam param = new ThresholdParam();
            param.setClientId(clientId);
            param.setSetFlag(1);
            List<ThresholdEntry> list = thresholdService.getList(param);
            if(list!=null &&list.size()>0){
                return ReturnMap.result(1,"获取成功",list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0,"获取失败",null);
    }

    /**
     * 保存设备参数
     * @param entry
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ThresholdEntry entry) {
        try {
            LoginInfo loginInfo = loginContext.getInfoApp();
            if (loginInfo != null) {
                if (entry.getId() > 0) {
                    entry.setModifyOn(loginInfo.getUserId());
                } else {
                    entry.setCreateOn(loginInfo.getUserId());
                }
                entry.setSetFlag(1);
                int num = thresholdService.save(entry);
                if (num > 0) {
                    return ReturnMap.result(1, "保存成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "保存失败");
    }

    /**
     * 更改下发标记
     * @param threshold_id
     * @return
     */
    @RequestMapping(value = "setFlag", method = RequestMethod.POST)
    @ResponseBody
    public Object setFlag(@RequestBody int threshold_id)
    {
        ThresholdEntry entry = new ThresholdEntry();
        entry.setId(threshold_id);
        entry.setSetFlag(0);
        int rs = thresholdService.updateSetFlag(entry);
        if (rs>0){
            return ReturnMap.result(1, "success");
        }
        return ReturnMap.result(0, "fail");
    }


}
