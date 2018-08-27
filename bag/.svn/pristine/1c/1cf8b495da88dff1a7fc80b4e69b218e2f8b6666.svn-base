package com.zhskg.bag.service;

import com.zhskg.bag.model.ServerInfoEntry;
import com.zhskg.bag.param.ServerInfoParam;

import java.util.List;

public interface ServerInfoService {

    /**
     * 添加服务器信息
     * @param entry
     * @return
     */
    int add(ServerInfoEntry entry);

    /**
     * 修改服务器信息
     * @param entry
     * @return
     */
    int updateById(ServerInfoEntry entry);

    /**
     * 保存服务器信息
     * @param entry
     * @return
     */
    int save(ServerInfoEntry entry);

    /**
     * 根据id获取服务器信息
     * @param id
     * @return
     */
    ServerInfoEntry get(int id);

    /**
     * 根据条件获取服务器信息列表
     * @param param
     * @return
     */
    List<ServerInfoEntry> getList(ServerInfoParam param);

    /**
     * 根据条件删除服务器信息
     * @param param
     * @return
     */
    int remove(ServerInfoParam param);
}
