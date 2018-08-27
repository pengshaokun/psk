package com.zhskg.bag.service;

import com.zhskg.bag.model.BagData;
import com.zhskg.bag.param.BagDataParam;

import java.util.List;

/**
 * Created by lwb on 2018/5/19.
 */
public interface BagDataService
{
    /**
     * 获取箱包数据分页列表
     * @param clientId 设备编码
     * @param page 页码
     * @param size 每页记录数
     * @return
     */
    List<BagData> getLat(String clientId,int page,int size);

    /**
     * 获取箱包数据列表
     * @param param 查询条件
     * @return
     */
    List<BagData> getByDate(BagDataParam param);

    /**
     * 获取记录数
     * @param param 查询条件
     * @return
     */
    int count(BagDataParam param);
}
