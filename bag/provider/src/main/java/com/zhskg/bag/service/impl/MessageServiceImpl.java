package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.Message;
import com.zhskg.bag.mapper.MessageMapper;
import com.zhskg.bag.model.MessageEntry;
import com.zhskg.bag.param.MessageParam;
import com.zhskg.bag.param.VersionParam;
import com.zhskg.bag.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 消息ServiceImpl
 */
@Service(version = "1.0")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper; // message dao

    /**
     * 添加消息
     * @param messageParam 消息
     * @return int 受影响行数
     */
    @Override
    public int add(MessageParam messageParam) {
        Message message = new Message();
        BeanUtils.copyProperties(messageParam, message); // 将前一个参数连的属性复制到后一个参数里面
        return messageMapper.add(message);
    }

    /**
     * 根据id删除消息
     * @param id id
     * @return id
     */
    @Override
    public int delete(int id) {
        return messageMapper.delete(id);
    }

    /**
     * 根据id修改消息
     * @param messageParam 消息
     * @return int
     */
    @Override
    public int update(MessageParam messageParam) {
        Message message = new Message();
        BeanUtils.copyProperties(messageParam, message); // 将前一个参数的属性复制到后一个参数里面
        return messageMapper.update(message);
    }

    /**
     * 根据id查看消息详情
     * @param id id
     * @return MessageEntry 扩展的message
     */
    @Override
    public MessageEntry queryById(int id) {
        return messageMapper.queryById(id);
    }

    /**
     * 根据参数查询消息列表
     * @return List<MessageParam>
     */
    @Override
    public List<MessageEntry> query(Integer pageIndex, Integer pageSize, MessageParam messageParam) {
        // 分页默认设置
        if (pageIndex == null || pageIndex <= 0) pageIndex = 1;
        if (pageSize == null || pageSize <= 0) pageSize = 10;
        pageIndex = (pageIndex - 1) * pageSize; // 计算分页起始条数
        List<MessageEntry> list = messageMapper.query(pageIndex, pageSize, messageParam);
        return list;
    }

    /**
     * 根据参数查询所有消息列表数量
     * @param messageParam messageParam
     * @return int
     */
    @Override
    public int queryAllMessageCount(MessageParam messageParam) {
        return messageMapper.queryAllMessageCount(messageParam);
    }
}
