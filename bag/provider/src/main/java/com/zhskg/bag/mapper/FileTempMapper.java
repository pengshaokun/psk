package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.FileTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileTempMapper {
    FileTemp get(@Param("fileId") Long fileId);

    FileTemp getFirst(Map<String, Object> map);

    Integer remove(Map<String, Object> map);

    Integer removeById(@Param("fileId") Long fileId);

    Integer realRemove(Map<String, Object> map);

    Integer realRemoveById(@Param("fileId") Long fileId);

    Integer add(FileTemp fileTemp);

    Integer addAndId(FileTemp fileTemp);

    Integer batchAdd(List<FileTemp> fileTempList);

    Integer update(Map<String, Object> map);

    Integer updateById(FileTemp fileTemp);

    List<FileTemp> getList(Map<String, Object> map);

    List<FileTemp> getPageList(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);

    Integer updateOverdue(Map<String, Object> map);

    List<FileTemp> getOverdueList(Map<String, Object> map);

    Integer removeOverdueFile(Map<String, Object> map);

    Integer batchUpdateOverdue(Map<String, Object> map);
}

