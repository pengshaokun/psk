package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.App;
import com.zhskg.bag.param.AppParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/10.
 */
public interface AppMapper {
    Integer add(App app);

    Integer batchAdd(List<App> appList);

    Integer removeById(@Param("appId") String appId);

    Integer remove(AppParam condition);

    Integer realRemoveById(@Param("appId") String appId);

    Integer realRemove(AppParam condition);

    App get(@Param("appId") String appId);

    App getFirst(AppParam condition);

    List<App> getList(AppParam condition);

    List<App> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") AppParam condition);

    Integer getCount(AppParam condition);

    Integer updateById(App app);

    Integer update(@Param("app") App app, @Param("condition") AppParam condition);
}