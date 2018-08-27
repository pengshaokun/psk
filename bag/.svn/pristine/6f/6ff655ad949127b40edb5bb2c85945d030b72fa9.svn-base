package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.MessageType;
import com.zhskg.bag.model.MessageTypeEntry;
import com.zhskg.bag.param.MessageTypeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageTypeMapper {
    
    /**
     * 根据参数获取消息类型列表
     * @param messageTypeParam 参数
     * @param pageIndex 第几页
     * @param pageSize 页大小
     * @return List<MessageTypeEntry>
     */
    public List<MessageTypeEntry> query(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("messageTypeParam") MessageTypeParam messageTypeParam);

    /**
     * 根据参数获取消息类型列表数量
     * @param messageTypeParam
     * @return int
     */
    public int queryAllMessageTypeCount(@Param("messageTypeParam") MessageTypeParam messageTypeParam);
}
