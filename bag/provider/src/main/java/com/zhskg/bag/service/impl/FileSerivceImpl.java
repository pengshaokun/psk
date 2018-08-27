package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.FileTemp;
import com.zhskg.bag.mapper.FileTempMapper;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.param.FileTempParam;
import com.zhskg.bag.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceName = "com.zhskg.bag.service.FileService", version = "1.0")
public class FileSerivceImpl implements FileService {
    @Autowired
    private FileTempMapper fileTempMapper;


    public int add(FileTempEntry entry) {
        int result = 0;
        try {
            if (entry != null) {
                if (entry.getOverdueFlag() == null) {
                    entry.setOverdueFlag(1);
                }
                entry.setCreateTime(System.currentTimeMillis());
                FileTemp fileTemp = new FileTemp();
                BeanUtils.copyProperties(entry, fileTemp);
                result = fileTempMapper.add(fileTemp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public boolean bathAdd(List<FileTempEntry> dataList) {
        boolean result = false;
        try {
            if (dataList != null && dataList.size() > 0) {
                List<FileTemp> fileTempList = new ArrayList<FileTemp>();
                FileTemp fileTemp;
                for (FileTempEntry item : dataList) {
                    fileTemp=new FileTemp();
                    BeanUtils.copyProperties(item,fileTemp);
                    fileTempList.add(fileTemp);
                }
                int num = fileTempMapper.batchAdd(fileTempList);
                if (num == dataList.size()) {
                    result = true;
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public boolean clearOverdueFile() {
        boolean rslt = false;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("currentTime", System.currentTimeMillis());
            List<FileTemp> fileTempList = fileTempMapper.getOverdueList(map);
            if (fileTempList != null && fileTempList.size() > 0) {
                fileTempMapper.removeOverdueFile(map);
                rslt = true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rslt = false;
        }
        return rslt;
    }

    public boolean updateOverdueFlag(String filePath, Integer overdueFlag) {
        boolean rslt;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("filePath", filePath);
            map.put("overdueFlag", overdueFlag);
            if (overdueFlag == 1) {
                map.put("createTime", System.currentTimeMillis());
            }
            fileTempMapper.updateOverdue(map);
            rslt = true;
        } catch (Exception e) {
            rslt = false;
        }
        return rslt;
    }


    public boolean updateOverdueFlag(List<String> filePaths, Integer overdueFlag) {
        boolean rslt;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("overdueFlag", overdueFlag);
            map.put("list", filePaths);
            if (overdueFlag == 1) {
                map.put("createTime", System.currentTimeMillis());
            }
            fileTempMapper.batchUpdateOverdue(map);
            rslt = true;
        } catch (Exception e) {
            rslt = false;
        }
        return rslt;
    }


    public int getCount(FileTempParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        return fileTempMapper.getCount(map);
    }


    public List<FileTempEntry> getPageList(int pageIndex, int pageSize, FileTempParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        int start = (pageIndex == 0 ? 0 : pageIndex - 1) * pageSize;
        map.put("start", start);
        map.put("end", pageSize);
        List<FileTemp> list = fileTempMapper.getPageList(map);
        List<FileTempEntry> _list = new ArrayList<>();
        if (list != null && list.size() > 0) {
            FileTempEntry entry;
            for (FileTemp item : list) {
                entry = new FileTempEntry();
                entry.setCreateOn(item.getCreateOn());
                entry.setCreateTime(item.getCreateTime());
                entry.setFileId(item.getFileId());
                entry.setFilePath(item.getFilePath());
                entry.setFileType(item.getFileType());
                entry.setOverdueFlag(item.getOverdueFlag());
                entry.setTermValidity(item.getTermValidity());
                _list.add(entry);
            }
        }
        return _list;
    }

    public List<FileTempEntry> getFileTempList() {
        List<FileTempEntry> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("currentTime", System.currentTimeMillis());
        List<FileTemp> fileTempList = fileTempMapper.getOverdueList(map);
        if (fileTempList != null && fileTempList.size() > 0) {
            FileTempEntry entry;
            for (FileTemp item : fileTempList) {
                entry = new FileTempEntry();
                BeanUtils.copyProperties(item, entry);
                list.add(entry);
            }
        }
        return list;
    }

    @Override
    public List<FileTempEntry> getOverdueFile() {
        List<FileTempEntry> list =  getFileTempList();
        return list;
    }
}
