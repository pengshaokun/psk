package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.HardwareVersion;

import java.util.List;
import java.util.Map;

/**
 * @Auther:jean
 * @Date:2018/8/9
 * @Descripsion
 */
public interface HardwareVersionMapper {

    HardwareVersion get(Integer id);

    List<HardwareVersion> getPageList(Map map);

    List<HardwareVersion> getList(Map map);

    Integer updateById(HardwareVersion hardwareVersion);

    Integer addAndId(HardwareVersion hardwareVersion);

    Integer removeById(Integer id);
}
