
package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by fuhaibo on 2017/06/07.
 */
public interface AppVersionMapper {
    AppVersion get(@Param("versionId") Integer versionId);

    Integer removeById(@Param("versionId") Integer versionId);

    Integer addAndId(AppVersion appVersion);

    Integer updateById(AppVersion appVersion);

    List<AppVersion> getList(Map<String, Object> map);

    AppVersion getNewestVersion(@Param("systemCategory") Integer systemCategory, @Param("customerType") Integer customerType);

    AppVersion isExistCode(@Param("systemCategory") Integer systemCategory, @Param("versionCode") Integer versionCode, @Param("customerType") Integer customerType);
    
}