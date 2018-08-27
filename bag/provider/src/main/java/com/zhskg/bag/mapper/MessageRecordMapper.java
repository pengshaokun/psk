package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.MessageRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息记录Dao
 */
public interface MessageRecordMapper {
    /**
     * 添加一条消息记录
     * @param messageRecord messageRecord
     * @return int
     */
    public int add(MessageRecord messageRecord);

    /**
     * 根据id删除一条消息记录
     * @param id id
     * @return int
     */
    public int delete(int id);

    /**
     * 根据id修改一条消息记录
     * @param messageRecord
     * @return int
     */
    public int update(MessageRecord messageRecord);

    /**
     * 根据id查看消息记录详情
     * @param id
     * @return
     */
    public MessageRecord queryById(int id);

    /**
     *
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageRecord 消息记录
     * @return List<MessageRecordEntry>
     */
    public List<MessageRecord> query(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("messageRecord") MessageRecord messageRecord);
}
