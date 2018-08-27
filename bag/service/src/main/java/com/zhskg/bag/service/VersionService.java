package com.zhskg.bag.service;

import com.zhskg.bag.model.VersionEntry;
import com.zhskg.bag.param.VersionParam;
import java.util.List;

/**
 * 版本Service
 */
public interface VersionService {
    /**
     * 添加版本
     * @param version 版本
     * @return int
     */
    public int add(VersionParam version);

    /**
     * 根据id删除版本
     * @param id 版本id
     * @return int
     */
    public int delete(long id);

    /**
     * 根据id修改版本
     * @param version 版本
     * @return int
     */
    public int update(VersionParam version);

    /**
     * 根据参数查询版本列表
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param versionParam 版本
     * @return List<VersionParam>
     */
    public List<VersionParam> query(Integer pageIndex, Integer pageSize, VersionParam versionParam);

    /**
     * 根据id查看版本详情
     * @param id 版本号id
     * @return List<VersionEntry>
     */
    public List<VersionEntry> queryById(long id);

    /**
     * @param versionParam 版本
     * @return int 版本列表总条目数
     */
    public int queryAllVersionCount(VersionParam versionParam);
}
