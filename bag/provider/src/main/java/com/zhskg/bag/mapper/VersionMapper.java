package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.Version;
import com.zhskg.bag.model.VersionEntry;
import com.zhskg.bag.param.VersionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版本Dao
 */
public interface VersionMapper {
    /**
     * 添加版本
     * @param version 版本
     * @return int
     */
    public int add(Version version);

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
    public int update(Version version);

    /**
     * 根据参数查询版本列表
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param versionParam 版本
     * @return List<Version>
     */
    public List<VersionParam> query(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("versionParam") VersionParam versionParam);

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
    public int queryAllVersionCount(@Param("versionParam") VersionParam versionParam);
}
