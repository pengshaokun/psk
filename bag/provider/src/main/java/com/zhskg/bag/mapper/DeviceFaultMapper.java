package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.DeviceFault;

import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/4/10.
 */
public interface DeviceFaultMapper
{
    DeviceFault get(int id);

    int add(DeviceFault model);

    int update(DeviceFault model);

    int remove(int id);

    List<DeviceFault> getList(Map<String, Object> map);

    int getCount(Map<String, Object> map);
}
