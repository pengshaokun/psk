package com.zhskg.bag.service;


import com.zhskg.bag.model.ProblemEntry;
import com.zhskg.bag.param.ProblemParam;

import java.util.List;

/**
 * Created by lwb on 2018/3/20.
 */
public interface ProblemService
{
    /**
     * 获取常见问题列表
     * @param param 查询条件
     * @return
     */
    List<ProblemEntry> getList(ProblemParam param);

    /**
     * 添加常见问题
     * @param model 常见问题信息
     * @return
     */
    int add(ProblemEntry model);

    /**
     * 修改常见问题
     * @param model 常见问题信息
     * @return
     */
    int update(ProblemEntry model);

    /**
     * 获取常见问题详细
     * @param id 主键id
     * @return
     */
    ProblemEntry get(int id);

    /**
     * 获取常见问题分页列表
     * @param page 页码
     * @param size 每页记录条数
     * @param param 查询条件
     * @return
     */
    List<ProblemEntry> getPageList(int page, int size, ProblemParam param);

    /**
     * 获取常见问题记录数
     * @param param 查询条件
     * @return
     */
    int count(ProblemParam param);

    /**
     * 删除常见问题
     * @param id 主键
     * @return
     */
    int remove(int id);
}
