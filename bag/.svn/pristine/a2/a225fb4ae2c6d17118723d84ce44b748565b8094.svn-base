package com.zhskg.bag.service;


import com.zhskg.bag.model.AppVersionEntry;
import com.zhskg.bag.param.AppVersionParam;

import java.util.List;

/**
 * Created by luochaojun on 2017/12/12.
 */
public interface AppVersionService {
    /**
     * 添加版本信息
     * @param data{}
     * @return
     * 版本id
     */
    Integer add(AppVersionEntry data);

    /**
     * 修改版本信息
     * @param data{}
     * @return
     * 版本id
     */
    Integer updateById(AppVersionEntry data);

    /**
     * 保存版本信息
     * @param data{}
     * @return
     * 版本id
     */
    Integer save(AppVersionEntry data);

    /**
     * 移除版本
     * @param versionId 版本ID
     * @return
     * 删除条数
     */
    Integer removeId(Integer versionId);

    /**
     * 获取版本信息
     * @param versionId 版本ID
     * @return
     * 版本信息
     */
    AppVersionEntry get(Integer versionId);

    /**
     * 获取版本列表
     * @return
     * 版本列表
     */
    List<AppVersionEntry> getList(AppVersionParam param);

    /**
     * 获取最新版本信息
     * @param systemCategory{0：安卓 1：ios}
     * @return
     * 版本信息
     */
    AppVersionEntry getNewestVersion(Integer systemCategory, Integer customerType);

    /**
     * 验证版本编码是否大于已有编码
     * @param systemCategory 系统类别
     * @param versionCode 版本编码
     * @return true:大于 false：小于
     */
    boolean isExistCode(Integer systemCategory, Integer versionCode, Integer customerType);
}
