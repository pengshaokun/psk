package com.zhskg.bag.service;

import com.zhskg.bag.model.MessageEntry;
import com.zhskg.bag.param.MessageParam;
import java.util.List;

/**
 * 消息Dao
 */
public interface MessageService {
    /**
     * 添加消息
     * @param messageParam 消息
     * @return int 受影响行数
     */
    public int add(MessageParam messageParam);

    /**
     * 根据id删除消息
     * @param id id
     * @return id
     */
    public int delete(int id);

    /**
     * 根据id修改消息
     * @param messageParam 消息
     * @return int
     */
    public int update(MessageParam messageParam);

    /**
     * 根据id查看消息详情
     */
    public MessageEntry queryById(int id);

    /**
     * 根据参数查询消息列表
     * @return List<MessageParam>
     */
    public List<MessageEntry> query(Integer pageIndex, Integer pageSize, MessageParam messageParam);

    /**
     * 根据参数查询所有消息列表数量
     * @param messageParam messageParam
     * @return int
     */
    public int queryAllMessageCount(MessageParam messageParam);
}
