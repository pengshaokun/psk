package com.zhskg.bag.service;

import com.zhskg.bag.model.AboutUsEntry;
import com.zhskg.bag.param.AboutUsParam;

import java.util.List;

/**
 * Created by lwb on 2018/3/14.
 */
public interface AboutService
{
    /**
     * 添加关于我们
     * @param data{}
     * @return
     * id
     */
    Integer add(AboutUsEntry data);

    /**
     * 修改关于我们
     * @param data{}
     * @return
     * id
     */
    Integer updateById(AboutUsEntry data);

    /**
     * 保存关于我们
     * @param data{}
     * @return
     * id
     */
    Integer save(AboutUsEntry data);

    /**
     * 删除关于我们
     * @param aboutId id
     * @return
     * 删除条数
     */
    Integer remove(Integer aboutId);

    /**
     * 获取信息
     * @param aboutId id
     * @return
     * 信息
     */
    AboutUsEntry get(Integer aboutId);

    /**
     *获取信息APP
     * @return
     * 信息
     */
    AboutUsEntry getDetail();

    /**
     * 获取列表
     * @param param{}
     * @return
     * 列表
     */
    List<AboutUsEntry> getList(AboutUsParam param);
}
