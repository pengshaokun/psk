package com.zhskg.bag.service;

import com.zhskg.bag.model.HardwareVersionEntry;
import com.zhskg.bag.param.HardwareVersionParam;

import java.util.List;
import java.util.Map;

/**
 * @Auther:jean
 * @Date:2018/8/9
 * @Descripsion
 */
public interface HardwareVersionService {

    /**
     * 获取版本列表
     * @return
     * 版本列表
     */
    HardwareVersionEntry get(Integer id);

    List<HardwareVersionEntry> getList(HardwareVersionParam param);

    Integer updateById(HardwareVersionEntry entry);

    Integer add(HardwareVersionEntry entry);

    Integer save(HardwareVersionEntry entry);

    Integer remove(Integer id);

    Boolean isExistCode(HardwareVersionParam param);

}
