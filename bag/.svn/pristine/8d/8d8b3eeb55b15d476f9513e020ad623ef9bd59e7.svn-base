package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;

import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "web/file/")
public class WebFileController {
    @Reference(version = "1.0")
    private FileService fileService;
    @Reference(version = "1.0")
    private AccountService accountService;
    @Autowired
    private LoginContext loginContext;
    @Autowired
    private FileConsumer fileConsumer;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return fileConsumer.uploadFile(file, 0, 1);
    }

    /**
     * 批量图片上传
     * @param files
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object batchUpload(@RequestParam(value = "files", required = false) MultipartFile[] files) {
        return fileConsumer.batchUploadFile(files, 0, 1);
    }

    /**
     * 图片上传fast
     * @param file
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "upload/fast", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadToFast(@RequestParam(value = "file", required = false) MultipartFile file) {
        return fileConsumer.uploadFileToFastDfs(file, 1);
    }

    /**
     * 图片上传disk
     * @param file
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "upload/disk", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadToDisk(@RequestParam(value = "file", required = false) MultipartFile file) {
        return fileConsumer.uploadFileToDisk(file, 1);
    }

    /**
     * 批量图片上传fast
     * @param files
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "batch/upload/fast", method = RequestMethod.POST)
    @ResponseBody
    public Object batchUploadToFast(@RequestParam(value = "files", required = false) MultipartFile[] files) {
        return fileConsumer.batchUploadToFastDfs(files, 1);
    }

    /**
     * 批量图片上传disk
     * @param files
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "batch/upload/disk", method = RequestMethod.POST)
    @ResponseBody
    public Object batchUploadToDisk(@RequestParam(value = "files", required = false) MultipartFile[] files) {
        return fileConsumer.batchUploadToDisk(files, 1);
    }

}
