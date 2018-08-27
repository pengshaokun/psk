package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.MessageSystem;
import com.zhskg.bag.model.MessageSystemEntry;
import com.zhskg.bag.param.MessageSystemParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统通知dao
 */
public interface MessageSystemMapper {
    /**
     * 添加系统通知
     * @param messageSystem MessageSystem
     * @return int
     */
    public int add(MessageSystem messageSystem);

    /**
     * 根据id删除系统通知
     * @param id id
     * @return int
     */
    public int delete(int id);

    /**
     * 根据id修改系统通知
     * @param messageSystem MessageSystem
     * @return int
     */
    public int update(MessageSystem messageSystem);

    /**
     * 根据参数查询系统通知列表
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageSystemParam 参数
     * @return List<MessageSystemEntry>
     */
    public List<MessageSystemEntry> query(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("messageSystemParam") MessageSystemParam messageSystemParam);

    /**
     * 根据id查看系统通知详情
     * @param id id
     * @return MessageSystemEntry
     */
    public MessageSystemEntry queryById(int id);

    /**
     * 根据参数查询系统通知列表数量
     * @param messageSystemParam MessageSystemParam
     * @return int
     */
    public int queryAllMessageSystemCount(@Param("messageSystemParam") MessageSystemParam messageSystemParam);
}
