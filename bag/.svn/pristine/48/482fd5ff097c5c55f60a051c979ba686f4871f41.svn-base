package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.Message;
import com.zhskg.bag.model.MessageEntry;
import com.zhskg.bag.param.MessageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息Dao
 */
public interface MessageMapper {
    /**
     * 添加消息
     * @param message 消息
     * @return int 受影响行数
     */
    public int add(Message message);

    /**
     * 根据id删除消息
     * @param id id
     * @return id
     */
    public int delete(int id);

    /**
     * 根据id修改消息
     * @param message 消息
     * @return int
     */
    public int update(Message message);

    /**
     * 根据id查看消息详情
     */
    public MessageEntry queryById(int id);

    /**
     * 根据参数查询消息列表
     * @return List<MessageParam>
     */
    public List<MessageEntry> query(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("messageParam") MessageParam messageParam);

    /**
     * 根据参数查询所有消息列表数量
     * @param messageParam 参数
     * @return int
     */
    public int queryAllMessageCount(@Param("messageParam") MessageParam messageParam);
}
