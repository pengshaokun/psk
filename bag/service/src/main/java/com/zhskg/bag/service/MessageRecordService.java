package com.zhskg.bag.service;

import com.zhskg.bag.model.MessageRecordEntry;
import com.zhskg.bag.param.MessageRecordParam;
import java.util.List;

/**
 * 消息记录Dao
 */
public interface MessageRecordService {
    /**
     * 添加一条消息记录
     * @param messageRecordParam messageRecordParam
     * @return int
     */
    public Boolean add(MessageRecordParam messageRecordParam);

    /**
     * 根据id删除一条消息记录
     * @param id id
     * @return int
     */
    public Boolean delete(int id);

    /**
     * 根据id修改一条消息记录
     * @param messageRecordParam
     * @return int
     */
    public Boolean update(MessageRecordParam messageRecordParam);

    /**
     * 根据id查看消息记录详情
     * @param id
     * @return
     */
    public MessageRecordEntry queryById(int id);

    /**
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageRecordParam 消息记录
     * @return List<MessageRecordEntry>
     */
    public List<MessageRecordParam> query(Integer pageIndex, Integer pageSize, MessageRecordParam messageRecordParam);
}
