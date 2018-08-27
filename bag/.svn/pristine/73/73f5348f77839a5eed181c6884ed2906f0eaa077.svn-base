package com.zhskg.bag.service;

import com.zhskg.bag.model.LostInfoEntry;
import com.zhskg.bag.param.LostInfoParam;

import java.util.List;
import java.util.Map;

public interface LostInfoService {
    /**
     * 添加丢失信息并返回主键id
     * @param entry
     * @return 主键id
     */
    int addAndId(LostInfoEntry entry);

    /**
     * 保存丢失信息
     * @param entry
     * @return
     */
    int save(LostInfoEntry entry);

    /**
     * 根据主键id获取丢失信息
     * @param id 主键id
     * @return 丢失信息
     */
    LostInfoEntry get(int id);

    /**
     * 修改丢失信息表数据
     * @param entry
     * @return 修改条数
     */
    int update(LostInfoEntry entry);

    /**
     * 根据条件删除丢失信息表数据
     * @param param 条件对象
     * @return 删除记录条数
     */
    int remove(LostInfoParam param);

    /**
     * 根据条件获取丢失信息数据列表
     * @param param 条件对象
     * @return 数据列表
     */
    List<LostInfoEntry> getList(LostInfoParam param);

    /**
     * 根据条件获取丢失信息分页数据列表
     * @param param 条件对象
     * @param page 页码
     * @param rows 每页记录条数
     * @return 分页数据列表
     */
    List<LostInfoEntry> getPageList(LostInfoParam param,int page,int rows);

    /**
     * 根据条件获取记录数
     * @param param 条件对象
     * @return 记录数
     */
    int getCount(LostInfoParam param);

    /**
     * 修改丢失信息标记
     * @param id 主键
     * @param flag 标记
     * @return
     */
    int setStatus(int id,int flag);
    /**
     * 修改丢失信息状态
     * @param id
     * @param lostFlag
     * @return
     */
	Map<String, Object> updateLostFlag(Integer id, Integer lostFlag);
}
