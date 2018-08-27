package com.zhskg.bag.service;

import com.zhskg.bag.model.ThresholdEntry;
import com.zhskg.bag.param.ThresholdParam;

import java.util.List;

public interface ThresholdService {

    /**
     * 添加阀值信息
     * @param entry
     * @return 主键id
     */
    int addAndId(ThresholdEntry entry);

    /**
     * 保存阀值信息
     * @param entry
     * @return
     */
    int save(ThresholdEntry entry);

    /**
     * 获取阀值信息
     * @param id
     * @return
     */
    ThresholdEntry get(int id);

    /**
     * 修改阀值信息
     * @param entry
     * @return
     */
    int update(ThresholdEntry entry);

    /**
     * 删除阀值信息
     * @param param
     * @return
     */
    int remove(ThresholdParam param);

    /**
     * 获取阀值信息列表
     * @param param
     * @return
     */
    List<ThresholdEntry> getList(ThresholdParam param);

    /**
     * 获取阀值信息分页；列表
     * @param param
     * @param page
     * @param rows
     * @return
     */
    List<ThresholdEntry> getPageList(ThresholdParam param, int page, int rows);

    /**
     * 获取阀值记录数
     * @param param
     * @return
     */
    int getCount(ThresholdParam param);

    /**
     * 修改设置标记
     * @param entry
     * @return
     */
    int updateSetFlag(ThresholdEntry entry);
}
