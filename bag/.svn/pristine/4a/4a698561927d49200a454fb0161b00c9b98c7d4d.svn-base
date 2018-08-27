package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.BagDataEntry;
import com.zhskg.bag.model.BagData;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.param.BagDataParam;
import com.zhskg.bag.param.UserDeviceParam;
import com.zhskg.bag.service.BagDataService;
import com.zhskg.bag.service.UserDeviceService;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "web/bag/data/")
public class WebBagDataController {
    @Reference(version = "1.0")
    private BagDataService bagDataService;

    @Reference(version = "1.0")
    private UserDeviceService userDeviceService;

    /**
     * 获取箱包数据分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "page/list")
    @ResponseBody
    public Object getPageList(int page, int rows, BagDataParam param){
        List<BagDataEntry> entryList = new ArrayList<>();
        param.start = (page -1) * rows;
        param.end = page * rows;
        param.setAlarmFlag(1);
        //param.setClientId("0041000000001");
        int total = bagDataService.count(param);
        if(total>0){
            List<BagData> list = bagDataService.getByDate(param);
            BagDataEntry entry;
            for (BagData item:list){
                entry = new BagDataEntry();
                BeanUtils.copyProperties(item,entry);
                if(item.getAf().size()>0){
                    for (Integer af:item.getAf()){
                        if(af==10){
                          entry.setVaFlag(10);
                        }
                        if(af==11){
                            entry.setElFlag(11);
                        }
                        if(af==12){
                            entry.setEvFlag(12);
                        }
                        if(af==13){
                            entry.setTeFlag(13);
                        }
                    }
                }
                JSONObject jsonObject = JSONObject.parseObject(item.getData());
                entry.setEl(jsonObject.getDouble("el"));
                entry.setTe(jsonObject.getDouble("te"));
                entry.setVa(jsonObject.getDouble("va"));
                entry.setEv(jsonObject.getDouble("ev"));
                UserDeviceParam userDeviceParam = new UserDeviceParam();
                userDeviceParam.setClientId(item.getClientId());
                userDeviceParam.setStockId(item.getStockId());
                List<UserDeviceEntry> deviceEntryList = userDeviceService.getList(userDeviceParam);
                for (UserDeviceEntry deviceEntry:deviceEntryList){
                    entry.setDeviceName(deviceEntry.getDeviceName());
                    entry.setProductName(deviceEntry.getProductName());
                    break;
                }
                entryList.add(entry);
            }
            return ReturnMapByBack.result(1,"获取成功！",entryList,total);
        }
        return ReturnMapByBack.result(0,"获取失败！",entryList,0);
    }
}
