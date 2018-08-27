package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.mapper.MessageTypeMapper;
import com.zhskg.bag.model.MessageTypeEntry;
import com.zhskg.bag.param.MessageTypeParam;
import com.zhskg.bag.service.MessageTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 消息类型实现
 */
@Service(version = "1.0")
public class MessageTypeServiceImpl implements MessageTypeService {

    @Autowired
    private MessageTypeMapper messageTypeMapper;

    /**
     * 根据参数获取消息类型列表
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageTypeParam 参数
     * @return List<MessageTypeEntry>
     */
    @Override
    public List<MessageTypeEntry> query(Integer pageIndex, Integer pageSize, MessageTypeParam messageTypeParam) {
        // 分页默认设置
        if (pageIndex == null || pageIndex <= 0) pageIndex = 1;
        if (pageSize == null || pageSize <= 0) pageSize = 10;
        pageIndex = (pageIndex - 1) * pageSize; // 计算分页起始条数
        return messageTypeMapper.query(pageIndex, pageSize, messageTypeParam);
    }

    /**
     * 根据参数获取消息类型列表数量
     * @param messageTypeParam
     * @return int 消息类型数量
     */
    @Override
    public int queryAllMessageTypeCount(MessageTypeParam messageTypeParam) {
        return messageTypeMapper.queryAllMessageTypeCount(messageTypeParam);
    }
}
