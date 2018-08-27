package com.zhskg.bag.service;

import com.zhskg.bag.model.MessageTypeEntry;
import com.zhskg.bag.param.MessageTypeParam;

import java.util.List;

public interface MessageTypeService {
    /**
     * 根据参数获取消息类型列表
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @param messageTypeParam 参数
     * @return List<MessageTypeEntry>
     */
    public List<MessageTypeEntry> query(Integer pageIndex, Integer pageSize, MessageTypeParam messageTypeParam);

    /**
     * 根据参数获取消息类型列表数量
     * @param messageTypeParam
     * @return int 消息类型数量
     */
    public int queryAllMessageTypeCount(MessageTypeParam messageTypeParam);
}
