package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.util.FastDFSUtil;
import com.zhskg.bag.util.ReturnMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2018/5/9.
 */
@Controller
@RequestMapping(value = "app/common/")
public class AppCommController
{

    @Reference(version = "1.0")
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object setHead(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if(file == null){
                return ReturnMap.result(0,"请选择上传的图片！");
            }
            InputStream intputStream = file.getInputStream();
            String inFileName = file.getOriginalFilename();
            //获取文件类型
            String extName = inFileName.substring(inFileName.lastIndexOf(".")).substring(1).toUpperCase();
            if(extName.equals("JPG") || extName.equals("JPEG") || extName.equals("PNG") || extName.equals("GIF")) {
                String path = FastDFSUtil.upload_file(intputStream, extName);
                FileTempEntry fileTempData = new FileTempEntry();
                fileTempData.setOverdueFlag(0);
                fileTempData.setFilePath(path);
                fileTempData.setCreateTime(System.currentTimeMillis());
                fileTempData.setFileType(extName);
                int num = fileService.add(fileTempData);
                if (StringUtils.isNotEmpty(path)) {
                    return ReturnMap.result(1, "保存成功", path);
                }
                return ReturnMap.result(0, "保存失败");
            }else {
                return ReturnMap.result(0, "图片格式错误");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "程序异常");
        }
    }


    /**
     * 文件批量上传
     * @param files
     * @return
     */
    @RequestMapping(value = "fileUploads", method = RequestMethod.POST)
    @ResponseBody
    public Object setHeads(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        try
        {
            List<String> list = new ArrayList<String>();
            for (MultipartFile file :files) {
                InputStream intputStream = file.getInputStream();
                String inFileName = file.getOriginalFilename();
                //获取文件类型
                String extName = inFileName.substring(inFileName.lastIndexOf(".")).substring(1).toUpperCase();
                if (extName.equals("JPG") || extName.equals("JPEG") || extName.equals("PNG") || extName.equals("GIF")) {
                    String path = FastDFSUtil.upload_file(intputStream, extName);
                    FileTempEntry fileTempData = new FileTempEntry();
                    fileTempData.setOverdueFlag(0);
                    fileTempData.setFilePath(path);
                    fileTempData.setCreateTime(System.currentTimeMillis());
                    fileTempData.setFileType(extName);
                    int num = fileService.add(fileTempData);
                    if (StringUtils.isNotEmpty(path)) {
                        list.add(path);
                    }
                }else {
                    return ReturnMap.result(0, "图片格式不正确！");
                }
            }
            return ReturnMap.result(1, "success！",list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "程序异常");
        }
    }
}
