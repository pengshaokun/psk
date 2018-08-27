package com.zhskg.bag.service;


import com.zhskg.bag.model.RepairPointEntry;
import com.zhskg.bag.param.RepairPointParam;

import java.util.List;

/**
 * Created by lwb on 2018/4/10.
 */
public interface RepairPointService
{
    /**
     * 获取维修点详细
     * @param id 主键
     * @return
     */
    RepairPointEntry get(int id);

    /**
     * 删除维修点
     * @param id 主键
     * @return
     */
    int remove(int id);

    /**
     * 添加维修点
     * @param model 维修点信息
     * @return
     */
    int add(RepairPointEntry model);

    /**
     * 修改维修点
     * @param model 维修点信息
     * @return
     */
    int updateById(RepairPointEntry model);

    /**
     * 获取维修点列表
     * @param param 查询条件
     * @return
     */
    List<RepairPointEntry> getList(RepairPointParam param);

    /**
     * 获取维修点记录数
     * @param param 查询条件
     * @return
     */
    int  getCount(RepairPointParam param);
}
