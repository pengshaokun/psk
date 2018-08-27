package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.MessageRecord;
import com.zhskg.bag.mapper.MessageRecordMapper;
import com.zhskg.bag.model.MessageRecordEntry;
import com.zhskg.bag.param.MessageRecordParam;
import com.zhskg.bag.service.MessageRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 消息记录ServiceRecordImpl
 */
@Service(version = "1.0")
public class MessageRecordServiceImpl implements MessageRecordService {
    @Autowired
    private MessageRecordMapper messageRecordMapper;

    /**
     * 添加一条消息记录
     * @param messageRecordParam messageRecordParam
     * @return int
     */
    @Override
    public Boolean add(MessageRecordParam messageRecordParam) {
        MessageRecord messageRecord = new MessageRecord();
        BeanUtils.copyProperties(messageRecordParam, messageRecord);// 形参转换
        return messageRecordMapper.add(messageRecord) > 0;
    }

    /**
     * 根据id删除一条消息记录
     * @param id id
     * @return int
     */
    @Override
    public Boolean delete(int id) {
        return false;
    }

    /**
     * 根据id修改一条消息记录
     * @param messageRecordParam
     * @return int
     */
    @Override
    public Boolean update(MessageRecordParam messageRecordParam) {
        return false;
    }

    /**
     * 根据id查看消息记录详情
     * @param id
     * @return
     */
    @Override
    public MessageRecordEntry queryById(int id) {
        return null;
    }

    /**
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageRecordParam 消息记录
     * @return List<MessageRecordEntry>
     */
    @Override
    public List<MessageRecordParam> query(Integer pageIndex, Integer pageSize, MessageRecordParam messageRecordParam) {
        return null;
    }
}
