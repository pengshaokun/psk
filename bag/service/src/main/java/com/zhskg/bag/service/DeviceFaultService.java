package com.zhskg.bag.service;


import com.zhskg.bag.model.DeviceFaultEntry;
import com.zhskg.bag.param.DeviceFaultParam;

import java.util.List;

/**
 * Created by lwb on 2018/4/10.
 */
public interface DeviceFaultService
{
    /**
     * 获取设备故障申报详细
     * @param id 主键
     * @return
     */
    DeviceFaultEntry get(int id);

    /**
     * 添加设备故障申报
     * @param model 设备故障申报信息
     * @return
     */
    int add(DeviceFaultEntry model);

    /**
     * 修改设备故障申报
     * @param model 设备故障申报信息
     * @return
     */
    int update(DeviceFaultEntry model);

    /**
     * 删除设备故障申报
     * @param id 主键id
     * @return
     */
    int remove(int id);

    /**
     * 获取设备故障申报列表
     * @param param 查询条件
     * @return
     */
    List<DeviceFaultEntry> getList(DeviceFaultParam param);

    /**
     * 获取设备故障申报记录数
     * @param param 查询条件
     * @return
     */
    int getCount(DeviceFaultParam param);
}
