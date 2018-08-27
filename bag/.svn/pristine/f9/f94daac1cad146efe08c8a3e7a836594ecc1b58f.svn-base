package com.zhskg.bag.service;

import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.param.UserDeviceParam;

import java.util.List;

/**
 * Created by lwb on 2018/5/11.
 */
public interface UserDeviceService
{
    /**
     * 用户添加设备
     * @param model
     * @return
     */
    int add(UserDeviceEntry model);

    /**
     * 用户删除设备:用户删除设备只是把该用户的userId 设置为o
     * @param id
     * @return
     */
    int removeDevice(int id);

    /**
     * 真实的删除数据
     * @param id
     * @return
     */
    int remvoeReal(int id);

    /**
     * 设置默认
     * @param id
     * @return
     */
    int setDefaultFlag(int id);

    /**
     * 修改备注名
     * @return
     */
    int updateRemark(int id,String remark);

    /**
     * 根据Id查询设备
     * @param id
     * @return
     */
    UserDeviceEntry getById(int id);

    /**
     * 根据条件查询数据
     * @param param
     * @return
     */
    List<UserDeviceEntry> getList(UserDeviceParam param);

    /**
     * 根据条件统计数据
     * @param param
     * @return
     */
    int count(UserDeviceParam param);

    /**
     * 修改用户设备
     * @param entry
     * @return
     */
    int update(UserDeviceEntry entry);
}
