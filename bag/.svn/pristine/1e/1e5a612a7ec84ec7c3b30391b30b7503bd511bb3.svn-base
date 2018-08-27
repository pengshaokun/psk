package com.zhskg.bag.service;

import com.zhskg.bag.model.AppEntry;
import com.zhskg.bag.param.AppParam;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/9.
 */
public interface AppService {
    /**
     * 添加应用
     *
     * @param appEntry 应用信息
     * @return 添加的应用Id
     */
    String addAndId(AppEntry appEntry);

    /**
     * 批量添加应用
     *
     * @param appList 应用信息列表
     * @return 添加的应用数
     */
    Integer batchAdd(List<AppEntry> appList);

    /**
     * 根据应用Id逻辑删除应用
     *
     * @param appId 应用Id
     * @return 逻辑删除的应用数
     */
    Integer removeById(String appId);

    /**
     * 根据查询条件逻辑删除应用
     * @param condition 查询条件
     * @return 逻辑删除的应用数
     */
    Integer remove(AppParam condition);

    /**
     * 根据应用Id物理删除应用
     *
     * @param appId 应用Id
     * @return 物理删除的应用数
     */
    Integer realRemoveById(String appId);

    /**
     * 根据查询条件物理删除应用
     * @param condition 查询条件
     * @return 物理删除的应用数
     */
    Integer realRemove(AppParam condition);

    /**
     * 根据应用Id获取应用
     *
     * @param appId 应用Id
     * @return 应用信息
     */
    AppEntry get(String appId);

    /**
     * 根据查询条件获取第一个应用
     *
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个应用信息
     */
    AppEntry getFirst(AppParam condition);

    /**
     * 根据查询条件获取应用信息列表
     *
     * @param condition 查询条件
     * @return 符合查询条件的账号信息列表
     */
    List<AppEntry> getList(AppParam condition);

    /**
     * 根据查询条件获取第pageIndex页的应用信息列表（pageSize条）
     *
     * @param pageIndex 页索引
     * @param pageSize  页大小
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第pageIndex页的应用信息列表（pageSize条）
     */
    List<AppEntry> getPageList(Integer pageIndex, Integer pageSize, AppParam condition);

    /**
     * 根据查询条件获取应用数
     *
     * @param condition 查询条件
     * @return 符合查询条件的应用数
     */
    Integer getCount(AppParam condition);

    /**
     * 更新应用
     *
     * @param appEntry 应用信息
     * @return 更新的应用Id
     */
    String save(AppEntry appEntry);

    /**
     * 根据查询条件更新应用
     *
     * @param appEntry 要更新的应用信息
     * @param condition 查询条件
     * @return 更新的应用数
     */
    Integer update(AppEntry appEntry, AppParam condition);
}
