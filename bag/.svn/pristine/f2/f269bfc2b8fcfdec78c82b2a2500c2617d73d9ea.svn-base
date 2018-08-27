package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.AuthData;
import com.zhskg.bag.data.CacheData;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.Stock;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.StockParam;
import com.zhskg.bag.param.UserDeviceParam;
import com.zhskg.bag.param.UserParam;
import com.zhskg.bag.service.StockService;
import com.zhskg.bag.service.UserDeviceService;
import com.zhskg.bag.service.UserService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.RedisUtil;
import com.zhskg.bag.util.RegexValidateUtil;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.zhskg.bag.controller.app.AppLostInfoController.REDIS_KEY;

@Controller
@RequestMapping(value = "app/user/device/")
public class AppUserDeviceController {
    @Reference(version = "1.0")
    private UserDeviceService userDeviceService;

    @Reference(version = "1.0")
    private StockService stockService;

    @Reference(version = "1.0")
    private UserService userService;

    @Autowired
    private LoginContext loginContext;

    /**
     * 设备添加
     *
     * @param entry
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody UserDeviceEntry entry) {
        try {
            if (entry.getClientId()==null||"".equals(entry.getClientId())||entry.getMac()==null||"".equals(entry.getMac())) {
                return ReturnMap.result(0, "添加失败，设备不存在！");
            }
            LoginInfo loginInfo = loginContext.getInfoApp();
            int num = 0;
            if (loginInfo != null && loginInfo.getUserId() > 0) {
                StockParam stockParam = new StockParam();
                stockParam.setClientId(entry.getClientId());
                stockParam.setStockState("1");
                List<Stock> stockList = stockService.customSelect(stockParam);
                if (stockList.size() == 0) {
                    return ReturnMap.result(0, "设备不存在！");
                }
//                if (!entry.getMac().equals(stockList.get(0).getMac())) {
//                    return ReturnMap.result(0, "设备不存在！");
//                }
                UserDeviceParam userDeviceParam = new UserDeviceParam();
                userDeviceParam.setClientId(entry.getClientId());
                userDeviceParam.setAuthType(0);
                List<UserDeviceEntry> getCount = userDeviceService.getList(userDeviceParam);
                if (getCount.size() == 0) {
                    //创建
                    UserDeviceParam hasBag = new UserDeviceParam();
                    hasBag.setUserId(loginInfo.getUserId());
                    List<UserDeviceEntry> hasList = userDeviceService.getList(hasBag);
                    if (hasList.size() > 0) {
                        entry.setDefaultFlag(0);
                    } else {
                        entry.setDefaultFlag(1);
                    }
                    Stock item = stockList.get(0);
                    entry.setConnPwd(item.getPwd());
                    entry.setDeviceName(item.getProductName());
                    entry.setProductName(item.getProductName());
                    entry.setMac(item.getMac());
                    entry.setProductCode(item.getProductCode());
                    entry.setUserId(loginInfo.getUserId());
                    entry.setStockId(item.getStockId());
                    num = userDeviceService.add(entry);
                    if (num > 0) {
                        String key = "bag:data:"+entry.getClientId();
                        CacheData cacheData=new CacheData();
                        cacheData.setPhoneNumber(loginInfo.getMobileNumber());
                        String jsonStr= JSON.toJSONString(cacheData);
                        RedisUtil.set(key,jsonStr);
                        return ReturnMap.result(1, "添加成功");
                    }
                } else {
                    UserDeviceEntry deviceEntry = getCount.get(0);
                    if (deviceEntry.getUserId() == 0) {
                        deviceEntry.setUserId(loginInfo.getUserId());
                        num = userDeviceService.update(deviceEntry);
                        return ReturnMap.result(1, "添加成功");
                    }
                    return ReturnMap.result(0, "设备被绑定");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "添加失败");
    }

    /**
     * 设置默认箱包
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "set/default", method = RequestMethod.POST)
    @ResponseBody
    public Object setDefault(@RequestParam int id) {
        LoginInfo loginInfo = loginContext.getInfoApp();
        if (loginInfo!=null) {
            UserDeviceEntry entry = userDeviceService.getById(id);
            if (entry==null || loginInfo.getUserId()!=entry.getUserId()) {
                return ReturnMap.result(0, "设置失败，设备不存在！！");
            }
            int num = userDeviceService.setDefaultFlag(id);
            if (num > 0) {
                return ReturnMap.result(1, "设置成功");
            }
        }
        return ReturnMap.result(0, "设置失败");
    }

    /**
     * 用户删除箱包
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "untie", method = RequestMethod.POST)
    @ResponseBody
    public Object remove(@RequestBody UserDeviceParam param) {
        try {
            int id = param.getId();
            UserDeviceEntry entry = userDeviceService.getById(id);
            if (entry.getAuthType() == 1) {
                userDeviceService.remvoeReal(entry.getId());
                return ReturnMap.result(1, "删除成功");
            }
            param = new UserDeviceParam();
            param.setClientId(entry.getClientId());
            param.setAuthType(1);
            List<UserDeviceEntry> list = userDeviceService.getList(param);
            if (list.size() > 0) {
                for (UserDeviceEntry item : list) {
                    userDeviceService.remvoeReal(item.getId());
                }
            }
            int num = userDeviceService.removeDevice(id);
            if (num > 0) {
                return ReturnMap.result(1, "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "删除失败");
    }

    /**
     * 设置箱包名称
     *
     * @param entry
     * @return
     */
    @RequestMapping(value = "update/name", method = RequestMethod.POST)
    @ResponseBody
    public Object updateName(@RequestBody UserDeviceEntry entry) {
        try {
            if (entry.getId()>0) {
                UserDeviceEntry data = userDeviceService.getById(entry.getId());
                data.setDeviceName(entry.getDeviceName());
                data.setRemark(entry.getRemark());
                int num = userDeviceService.update(data);
                if (num > 0) {
                    return ReturnMap.result(1, "修改成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "修改失败");
    }

    /**
     * 箱包详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getById(@PathVariable("id") int id) {
        try {
            UserDeviceEntry entry = userDeviceService.getById(id);
            if (entry != null) {
                String dataJson = RedisUtil.get(REDIS_KEY+entry.getClientId());
                if (dataJson != null) {
                    JSONObject jsStr = JSONObject.parseObject(dataJson);
                    int lostFlag = jsStr.getInteger("lossFlag");
                    entry.setLostFlag(lostFlag);
                }
                return ReturnMap.result(1, "获取成功", entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "获取失败", null);
    }

    /**
     * 用户设备列表
     *
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList() {
        LoginInfo loginInfo = loginContext.getInfoApp();
        List<UserDeviceEntry> list = new ArrayList<UserDeviceEntry>();
        try {
            UserDeviceParam param = new UserDeviceParam();
            param.setUserId(loginInfo.getUserId());
            int count = userDeviceService.count(param);
            if (count > 0) {
                list = userDeviceService.getList(param);
                for (UserDeviceEntry item:list) {
                    String dataJson = RedisUtil.get(REDIS_KEY+item.getClientId());
                    if (dataJson!=null) {
                        JSONObject jsStr = JSONObject.parseObject(dataJson);
                        int lostFlag = jsStr.getInteger("lossFlag");
                        item.setLostFlag(lostFlag);
                    }
                }
                return ReturnMap.result(1, "获取成功", list, count);
            } else {
                return ReturnMap.result(1, "获取成功", list, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0, "获取失败");
        }
    }

    /**
     * 获取默认箱包
     *
     * @return
     */
    @RequestMapping(value = "getDefault", method = RequestMethod.GET)
    @ResponseBody
    public Object getDefault() {
        LoginInfo loginInfo = loginContext.getInfoApp();
        UserDeviceParam param = new UserDeviceParam();
        param.setUserId(loginInfo.getUserId());
        param.setDefaultFlag(1);
        try {
            List<UserDeviceEntry> list = userDeviceService.getList(param);
            if (list.size() > 0) {
                String dataJson = RedisUtil.get(REDIS_KEY+list.get(0).getClientId());
                if (dataJson!=null) {
                    JSONObject jsStr = JSONObject.parseObject(dataJson);
                    int lostFlag = jsStr.getInteger("lossFlag");
                    list.get(0).setLostFlag(lostFlag);
                }
                return ReturnMap.result(1, "获取成功", list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMap.result(0, "获取失败", null);
    }

    /**
     * 设备授权
     *
     * @return
     */
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    @ResponseBody
    public Object auth(@RequestBody AuthData data) {
        if(data.getMobileNumber()==null || "".equals(data.getMobileNumber())){
            return ReturnMap.result(0, "授权失败，手机号不能为空！");
        }
        if (!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())) {
            return ReturnMap.result(0, "授权失败，手机号码错误！");
        }
        UserParam userParam = new UserParam();
        userParam.setMobileNumber(data.getMobileNumber());
        // UserEntry entry= userService.getByFirst(userParam);
        List<UserEntry> userEntries = userService.getList(userParam);
        if (userEntries == null || userEntries.size() == 0) {
            return ReturnMap.result(0, "授权的用户不存在！");
        }
        UserEntry entry = userEntries.get(0);
        if (entry != null && entry.getUserId() > 0) {
            UserDeviceEntry deviceEntry = userDeviceService.getById(data.getId());
            UserDeviceParam param = new UserDeviceParam();
            param.setClientId(deviceEntry.getClientId());
            param.setUserId(entry.getUserId());
            int count = userDeviceService.count(param);
            if (count > 0) {
                return ReturnMap.result(0, "改用户已经授权！");
            }
            if (deviceEntry.getAuthType() == 0) {
                deviceEntry.setUserId(entry.getUserId());
                deviceEntry.setAuthType(1);
                deviceEntry.setCreateTime(System.currentTimeMillis());
                int result = userDeviceService.add(deviceEntry);
                if (result > 0) {
                    return ReturnMap.result(1, "授权成功！");
                }
            }
            return ReturnMap.result(0, "授权失败！");
        } else {
            return ReturnMap.result(0, "授权的用户不存在！");
        }
    }

    /**
     * 设备授权信息
     */
    @RequestMapping(value = "auth_info", method = RequestMethod.GET)
    @ResponseBody
    public Object authInfo(@RequestParam String clientId) {
        LoginInfo loginInfo=loginContext.getInfoApp();
        UserDeviceParam infoParam = new UserDeviceParam();
        infoParam.setClientId(clientId);
        infoParam.setAuthType(0);
        infoParam.setUserId(loginInfo.getUserId());
        List<UserDeviceEntry> infoList = userDeviceService.getList(infoParam);
        if (infoList.size()==0)return  ReturnMap.result(0, "只能查找自己的授权信息");
        UserDeviceParam param = new UserDeviceParam();
        param.setClientId(clientId);
        param.setAuthType(1);
        List<UserDeviceEntry> list = userDeviceService.getList(param);
        List<JSONObject> objects = new ArrayList<JSONObject>();
        for (UserDeviceEntry entry : list) {
            UserEntry userEntry = userService.get(entry.getUserId());
            JSONObject object = new JSONObject();
            object.put("authId", entry.getId());
            object.put("tel", userEntry.getMobileNumber());
            object.put("nickName", userEntry.getNickName());
            object.put("fullName", userEntry.getFullName());
            object.put("authTime", entry.getCreateTime());
            object.put("headImg", userEntry.getHeadPortrait());
            objects.add(object);
        }
        return ReturnMap.result(1, "success", objects);
    }

    /**
     * 取消授权
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object cancel(@RequestBody AuthData data) {
        UserDeviceEntry entry = userDeviceService.getById(data.getId());
        if (entry.getAuthType() == 1) {
            int rs = userDeviceService.remvoeReal(data.getId());
            if (rs > 0) {
                return ReturnMap.result(1, "取消成功！");
            }
        }
        return ReturnMap.result(0, "取消失败");
    }
}
