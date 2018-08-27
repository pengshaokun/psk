package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.ServerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ServerInfoMapper {
    int addAndId(ServerInfo serverInfo);
    int update(ServerInfo serverInfo);
    int remove(Map<String,Object> map);
    ServerInfo get(@Param("id")int id);
    List<ServerInfo> getList(Map<String,Object> map);
}
