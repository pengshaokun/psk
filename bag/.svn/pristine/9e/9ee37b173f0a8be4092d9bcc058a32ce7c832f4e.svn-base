package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhskg.bag.entity.MessageSystem;
import com.zhskg.bag.mapper.MessageSystemMapper;
import com.zhskg.bag.model.MessageSystemEntry;
import com.zhskg.bag.param.MessageSystemParam;
import com.zhskg.bag.service.MessageSystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 系统通知service实现
 */
@Service(version = "1.0")
public class MessageSystemServiceImpl implements MessageSystemService {

    @Autowired
    private MessageSystemMapper messageSystemMapper;

    /**
     * 添加
     */
    @Override
    public Boolean add(MessageSystemParam messageSystemParam) {
        MessageSystem messageSystem = new MessageSystem();
        BeanUtils.copyProperties(messageSystemParam, messageSystem);
        messageSystem.setCreatetime(new Date()); // 创建时间
        return messageSystemMapper.add(messageSystem) > 0;
    }

    /**
     * 删除
     */
    @Override
    public Boolean delete(int id) {
        return messageSystemMapper.delete(id) > 0;
    }

    /**
     * 修改
     */
    @Override
    public Boolean update(MessageSystemParam messageSystemParam) {
        MessageSystem messageSystem = new MessageSystem();
        BeanUtils.copyProperties(messageSystemParam, messageSystem);
        return messageSystemMapper.update(messageSystem) > 0;
    }

    /**
     * 根据参数查询
     */
    @Override
    public List<MessageSystemEntry> query(Integer pageIndex, Integer pageSize, MessageSystemParam messageSystemParam) {
        // 分页默认设置
        if (pageIndex == null || pageIndex <= 0) pageIndex = 1;
        if (pageSize == null || pageSize <= 0) pageSize = 10;
        pageIndex = (pageIndex - 1) * pageSize; // 计算分页起始条数
        return messageSystemMapper.query(pageIndex, pageSize, messageSystemParam);
    }

    /**
     * 查询详情
     */
    @Override
    public MessageSystemEntry queryById(int id) {
        return messageSystemMapper.queryById(id);
    }

    /**
     * 根据擦系数查询列表数量
     */
    @Override
    public int queryAllMessageSystemCount(MessageSystemParam messageSystemParam) {
        return messageSystemMapper.queryAllMessageSystemCount(messageSystemParam);
    }
}
