package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.DeviceFaultEntry;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.param.DeviceFaultParam;
import com.zhskg.bag.param.UserDeviceParam;
import com.zhskg.bag.service.DeviceFaultService;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.service.UserDeviceService;
import com.zhskg.bag.util.FastDFSUtil;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.RegexValidateUtil;
import com.zhskg.bag.util.ReturnMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 故障申报
 */
@Controller
@RequestMapping(value = "app/device/fault/")
public class AppDeviceFaultController
{
    @Reference(version = "1.0")
    private DeviceFaultService faultService;

    @Autowired
    private LoginContext loginContext;

    @Reference(version = "1.0")
    private FileService fileService;

    @Reference(version = "1.0")
    private UserDeviceService userDeviceService;


    /**
     * 设备故障申报
     * @param map
     * @param files
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam Map<String,Object> map,@RequestParam(value = "file", required = false) MultipartFile[] files)
    {
        try{
            String json = JSON.toJSONString(map);
            DeviceFaultEntry data = JSON.parseObject(json, DeviceFaultEntry.class);
            if (StringUtils.isEmpty(data.getReason())){
                return ReturnMap.result(0, "故障描述不能为空！");
            }
            if (StringUtils.isEmpty(data.getClientId())){
                return ReturnMap.result(0, "设备编码不能为空！！");
            }
            if (StringUtils.isEmpty(data.getTel())){
                return ReturnMap.result(0, "联系电话不能为空！！");
            }
            if(!RegexValidateUtil.checkMobileNumber(data.getTel())){
                return  ReturnMap.result(0,"手机号码格式错误");
            }

            LoginInfo info = loginContext.getInfoApp();
            List<String> paths = new ArrayList<>();
            if(files!=null&&files.length>0){
                if(files.length>3){
                    return ReturnMap.result(0,"上传图片不能超过3张");
                }
                for(MultipartFile file:files){
                    InputStream intputStream = file.getInputStream();
                    String inFileName = file.getOriginalFilename();
                    //获取文件类型
                    String extName = inFileName.substring(inFileName.lastIndexOf(".")).substring(1);
                    String path = FastDFSUtil.upload_file(intputStream, extName);
                    if (StringUtils.isEmpty(path)) {
                        return ReturnMap.result(0, "图片上传失败！");
                    }
                    paths.add(path);
                    FileTempEntry fileTempData = new FileTempEntry();
                    fileTempData.setOverdueFlag(0);
                    fileTempData.setFilePath(path);
                    fileTempData.setCreateTime(System.currentTimeMillis());
                    fileTempData.setFileType(extName);
                    fileService.add(fileTempData);
                    fileService.updateOverdueFlag(path, 0);
                }
                data.setImg(StringUtils.join(paths.toArray(), ";"));
            }
            UserDeviceParam param = new UserDeviceParam();
            param.setClientId(data.getClientId());
            List<UserDeviceEntry> list = userDeviceService.getList(param);
            if (list.size()==0) {
                return ReturnMap.result(0, "该用户设备不存在！！");
            }
            data.setProductName(list.get(0).getProductName());
            data.setUserId(info.getUserId());
            data.setCreateTime(System.currentTimeMillis());
            data.setCreateName(info.getFullName());
            int result =faultService.add(data);
            if(result>0) {
                return ReturnMap.result(1, "申报成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ReturnMap.result(0, "申报失败！");
    }

    /**
     * 获取最新的10条处理申报
     * @param param 条件参数对象
     * @return
     */
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    @ResponseBody
    public Object list(DeviceFaultParam param)
    {
        param.setStart(0);
        param.setEnd(10);
        LoginInfo info = loginContext.getInfoApp();
        param.setUserId(info.getUserId());
        List<DeviceFaultEntry> result =faultService.getList(param);
        return ReturnMap.result(1, "success",result);
    }

    /**
     * 修改故障申报信息
     * @param model
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody DeviceFaultEntry model){
        try{
            LoginInfo info = loginContext.getInfoApp();
            model.setUserId(info.getUserId());
            int result =faultService.update(model);
            if(result>0) {
                return ReturnMap.result(1, "修改成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ReturnMap.result(0, "修改失败！");
    }

    /**
     * 获取故障申报分页列表
     * @param page 页码
     * @param rows 每页记录条数
     * @return
     */
    @RequestMapping(value = "get/page/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(int page,int rows)
    {
        DeviceFaultParam param = new DeviceFaultParam();
        if( rows>0){
            int start= (page - 1) * rows;
            int end= rows;
            param.setStart(start);
            param.setEnd(end);
        }
        LoginInfo info = loginContext.getInfoApp();
        param.setUserId(info.getUserId());
        int count = faultService.getCount(param);
        List<DeviceFaultEntry> result =faultService.getList(param);
        return ReturnMap.result(1, "success",result,count);
    }

    /**
     * 根据主键获取故障申报详细
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id")int id){
        DeviceFaultEntry entry = faultService.get(id);
        return ReturnMap.result(1, "success",entry);
    }

    /**
     * 根据主键删除故障申报信息
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object remove(@PathVariable("id") int id) {
        int count = faultService.remove(id);
        if(count>0){
            return  ReturnMap.result(1,"删除成功");
        }
        return  ReturnMap.result(0,"删除失败");
    }


}
