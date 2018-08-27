package com.zhskg.bag.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.FileInfo;
import com.zhskg.bag.data.FileStorageEnum;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileConsumer {
    @Reference(version = "1.0")
    private FileService fileService;
    @Reference(version = "1.0")
    private AccountService accountService;
    @Autowired
    private LoginContext loginContext;

    public Object uploadFile(MultipartFile file, int storageSite, int overDueFlag) {
        if (storageSite == FileStorageEnum.TO_FAST_DFS.getValue()) {
            return uploadFileToFastDfs(file, overDueFlag);
        }
        return uploadFileToDisk(file, overDueFlag);
    }

    public Object batchUploadFile(MultipartFile[] files, int storageSite, int overDueFlag) {
        if (storageSite == FileStorageEnum.TO_FAST_DFS.getValue()) {
            return batchUploadToFastDfs(files, overDueFlag);
        }
        return batchUploadToDisk(files, overDueFlag);
    }

    public Object uploadFileToFastDfs(MultipartFile file, int overDueFlag) {
        try {
            String fileName = file.getOriginalFilename();
            String extName = getExtName(fileName);
            InputStream inputStream = file.getInputStream();
            String path = FastDFSUtil.upload_file(inputStream, extName);
            if (path != null && !"".equals(path)) {
                FileTempEntry data = new FileTempEntry();
                data.setCreateTime(System.currentTimeMillis());
                data.setOverdueFlag(overDueFlag);
                data.setTermValidity(ConfigUtil.TERM_VALIDITY);
                data.setFilePath(path);
                data.setFileType(extName);
                data.setTermValidity(86400000l);
                LoginInfo loginInfo = loginContext.getInfoApp();
                if (loginInfo != null) {
                    data.setCreateOn(loginInfo.getAccountId());
                }
                int num = fileService.add(data);
                if (num > 0) {
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(fileName);
                    fileInfo.setFilePath(path);
                    fileInfo.setFileType(extName);
                    return ReturnMap.result(1, "上传成功！", fileInfo);
                } else {
                    FastDFSUtil.dealFilePath(path);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "服务器异常，上传失败！", null);
        }
        return ReturnMap.result(0, "上传失败！", null);
    }

    public Object uploadFileToDisk(MultipartFile file, int overDueFlag) {
        try {
            String absoluteDiskPath = ConfigUtil.ABSOLUTE_DISK_PATH;
            String relativePath = ConfigUtil.RELATIVE_DISK_PATH;
            // 解决中文问题，liunx下中文路径，图片显示问题
            String fileName = file.getOriginalFilename();
            String extName = getExtName(fileName);
            String _fileName = UUID.randomUUID() + "." + extName;
            File dest = new File(absoluteDiskPath + _fileName);
            //判断父级文件是否存在
            if (!dest.getParentFile().exists()) {
                //创建父级文件
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            String filePath = relativePath + _fileName;
            FileTempEntry data = new FileTempEntry();
            data.setCreateTime(System.currentTimeMillis());
            data.setOverdueFlag(overDueFlag);
            data.setTermValidity(ConfigUtil.TERM_VALIDITY);
            data.setFilePath(filePath);
            data.setFileType(extName);
            LoginInfo loginInfo = loginContext.getInfoApp();
            if (loginInfo != null) {
                data.setCreateOn(loginInfo.getAccountId());
            }
            int num = fileService.add(data);
            if (num > 0) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileType(extName);
                fileInfo.setFilePath(filePath);
                fileInfo.setFileName(fileName);
                return ReturnMap.result(1, "上传成功！", fileInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "服务器异常，上传失败！", null);
        }
        return ReturnMap.result(0, "上传失败！", null);
    }

    public Object batchUploadToFastDfs(MultipartFile[] files, int overDueFlag) {
        try {
            if (files != null && files.length > 0) {
                List<FileInfo> list = new ArrayList<>();
                FileInfo fileInfo;
                List<FileTempEntry> fileTempDataList = new ArrayList<>();
                FileTempEntry fileTempData;
                LoginInfo loginInfo = loginContext.getInfoApp();
                Long accountId = null;
                if (loginInfo != null) {
                    accountId = loginInfo.getAccountId();
                }
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    String extName = getExtName(fileName);
                    InputStream inputStream = file.getInputStream();
                    String path = FastDFSUtil.upload_file(inputStream, extName);
                    if (path != null && !"".equals(path)) {
                        fileInfo = new FileInfo();
                        fileTempData = new FileTempEntry();
                        fileInfo.setFilePath(path);
                        fileInfo.setFileName(fileName);
                        fileInfo.setFileType(extName);
                        fileTempData.setCreateTime(System.currentTimeMillis());
                        fileTempData.setOverdueFlag(overDueFlag);
                        fileTempData.setTermValidity(ConfigUtil.TERM_VALIDITY);
                        fileTempData.setCreateOn(accountId);
                        fileTempData.setFilePath(path);
                        fileTempData.setFileType(extName);
                        list.add(fileInfo);
                        fileTempDataList.add(fileTempData);
                    }
                }
                boolean rslt = fileService.bathAdd(fileTempDataList);
                if (rslt) {
                    return ReturnMap.result(1, "上传成功！", list,list.size());
                } else {
                    if (fileTempDataList != null && fileTempDataList.size() > 0) {
                        for (FileTempEntry item : fileTempDataList) {
                            FastDFSUtil.deleteFile(item.getFilePath());
                        }
                    }
                }
            } else {
                return ReturnMap.result(0, "请选择上传文件！", null);
            }
        } catch (Exception ex) {
            return ReturnMap.result(-1, "服务器异常，上传失败！", null);
        }
        return ReturnMap.result(0, "上传失败！", null);
    }

    public Object batchUploadToDisk(MultipartFile[] files, int overDueFlag) {
        try {
            if (files != null && files.length > 0) {
                List<FileInfo> list = new ArrayList<>();
                List<FileTempEntry> fileTempDataList = new ArrayList<>();
                FileInfo fileInfo;
                FileTempEntry fileTempData;
                LoginInfo loginInfo = loginContext.getInfoApp();
                Long accountId = null;
                if (loginInfo != null) {
                    accountId = loginInfo.getAccountId();
                }
                String absoluteDiskPath = ConfigUtil.ABSOLUTE_DISK_PATH;
                String relativePath = ConfigUtil.RELATIVE_DISK_PATH;
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    String extName = getExtName(fileName);
                    String _fileName = UUID.randomUUID() + "." + extName;
                    File dest = new File(absoluteDiskPath + _fileName);
                    //判断父级文件是否存在
                    if (!dest.getParentFile().exists()) {
                        //创建父级文件
                        dest.getParentFile().mkdirs();
                    }
                    file.transferTo(dest);
                    String filePath = relativePath + _fileName;
                    fileTempData = new FileTempEntry();
                    fileTempData.setCreateTime(System.currentTimeMillis());
                    fileTempData.setOverdueFlag(overDueFlag);
                    fileTempData.setCreateOn(accountId);
                    fileTempData.setTermValidity(ConfigUtil.TERM_VALIDITY);
                    fileTempData.setFilePath(filePath);
                    fileTempData.setFileType(extName);
                    fileTempDataList.add(fileTempData);
                    fileInfo = new FileInfo();
                    fileInfo.setFileType(extName);
                    fileInfo.setFilePath(filePath);
                    fileInfo.setFileName(fileName);
                    list.add(fileInfo);
                }
                if (fileTempDataList != null && fileTempDataList.size() > 0) {
                    boolean rslt = fileService.bathAdd(fileTempDataList);
                    if (rslt) {
                        return ReturnMap.result(1, "上传成功！", list,list.size());
                    }
                }
            } else {
                return ReturnMap.result(0, "请选择上传文件！", null);
            }
        } catch (Exception ex) {
            return ReturnMap.result(-1, "服务器异常，上传失败！", null);
        }
        return ReturnMap.result(0, "上传失败！", null);
    }

    private String getExtName(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.')).substring(1);
    }
}
