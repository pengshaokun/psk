package com.zhskg.bag.mapper;


import com.zhskg.bag.entity.RepairPoint;

import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/4/10.
 */
public interface RepairPointMapper
{
    RepairPoint get(int id);

    int remove(int id);

    int add(RepairPoint model);

    int updateById(RepairPoint model);

    List<RepairPoint> getList(Map<String, Object> map);

    int  getCount(Map<String, Object> map);
}
