package com.zhskg.bag.service;

import com.zhskg.bag.model.OpinionEntry;

import java.util.List;

/**
 * Created by heshuang on 2017-11-15.
 * 投诉意见服务层接口
 */
public interface OpinionService {
    /**
     * 获取反馈明细
     * @param opinionId 主键id
     * @return
     */
    OpinionEntry get(Integer opinionId);

    /**
     * 分页获取反馈信息列表
     * @param page 当前页码
     * @param rows 每页显示条数
     * @param consultStatus 查阅状态
     * @return 反馈信息列表
     */
    List<OpinionEntry> getPageList(Integer page, Integer rows, Integer consultStatus);

    /**
     * 获取反馈信息总数
     * @param consultStatus 查阅状态
     * @return Integer
     */
    Integer getCount(Integer consultStatus);

    /**
     * 提交反馈信息
     * @param data
     * @return 影响行数
     */
    Integer opinion(OpinionEntry data);

    /**
     *  查阅反馈信息
     * @param id
     * @param consultOn 查阅人
     * @return 影响行数
     */
    Integer consult(Integer id, Long consultOn);

    /**
     * 根据用户获取意见反馈列表
     * @param page 页码
     * @param rows 每页记录条数
     * @param consultStatus 查阅状态
     * @param userId 用户id
     * @return
     */
    List<OpinionEntry> getByUser(Integer page, Integer rows, Integer consultStatus, long userId);
}
