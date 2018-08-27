package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.UserDevice;

import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/5/11.
 */
public interface UserDeviceMapper
{
    int add(UserDevice model);

    /**
     * 根据ID删除数据，不是真删，把userId设置为0
     * @param map
     * @return
     */
    int update(Map<String,Object> map);


    int setDefault(Map<String,Object> map);

    /**
     * 真实的删除
     * @param id
     * @return
     */
    int deleteReal(int id);

    int updateRemark(Map<String,Object> map);

    UserDevice get(int id);

    List<UserDevice> getList(Map<String,Object> map);

    int count(Map<String,Object> map);
}
