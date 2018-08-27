package com.zhskg.bag.service;

import com.zhskg.bag.model.ThirdPartyInfoEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.ThirdPartyInfoParam;
import com.zhskg.bag.util.returnUtil.ResultJsonFormat;

import java.util.List;
import java.util.Map;

/**
 * Created by luochaojun on 2018/2/27.
 */
public interface ThirdPartyInfoService {
   /* *//**
     * 添加第三方用户信息
     * @param data 第三方用户信息
     * @return
     *//*
    boolean add(ThirdPartyInfoEntry data);

    *//**
     * 修改第三方用户信息
     * @return
     *//*
    boolean update(ThirdPartyInfoEntry data);*/

    Integer removeById(String openId);

    /**
     * 根据userId 以及 平台查询第三方登录信息
     * @param map
     * @return
     */
    List<ThirdPartyInfoEntry> getByUserIdAndTerraceType(Map<String,Object> map);

    /**
     * 保存第三方用户信息
     * @param data 第三方用户信息
     * @return
     */
    boolean save(ThirdPartyInfoEntry data);

    /**
     * 第三方用户登录
     * @param data 第三方用户信息
     * @return
     */
    ThirdPartyInfoEntry signIn(ThirdPartyInfoEntry data);

    /**
     * 获取第三方用户信息详细
     * @param openId
     * @return
     */
    ThirdPartyInfoEntry get(String openId);

    /**
     * 获取第三方用户信息详细
     * @param openId
     * @param token
     * @return
     */
    ThirdPartyInfoEntry getFirst(String openId, String token);

    /**
     * 获取第三方用户信息列表
     * @param param 查询条件
     * @return
     */
    List<ThirdPartyInfoEntry> getList(ThirdPartyInfoParam param);

    /**
     * 获取用户信息分页列表
     * @param pageIndex 页码
     * @param pageSize 每页记录数
     * @param param 查询条件
     * @return
     */
    List<ThirdPartyInfoEntry> getPageList(int pageIndex, int pageSize, ThirdPartyInfoParam param);

    /**
     * 获取记录数
     * @param param 查询条件
     * @return
     */
    int getCount(ThirdPartyInfoParam param);

    /**
     * 根据用户获取第三方用户信息详细
     * @param userId 用户id
     * @return
     */
    ThirdPartyInfoEntry getByUserId(long userId);

    /**
     * 设置用户id
     * @param openId
     * @param userId
     * @return
     */
    int setUserId(String openId,Long userId);

    /**
     * 更新第三方用户信息
     * @param thirdPartyInfoEntry
     * @return
     */
    Integer updateById(ThirdPartyInfoEntry thirdPartyInfoEntry);
}
