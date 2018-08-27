package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.ServerInfo;
import com.zhskg.bag.mapper.ServerInfoMapper;
import com.zhskg.bag.model.ServerInfoEntry;
import com.zhskg.bag.param.ServerInfoParam;
import com.zhskg.bag.service.ServerInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(version = "1.0")
public class ServerInfoServiceImpl implements ServerInfoService {
    @Autowired
    private ServerInfoMapper serverInfoMapper;

    @Override
    public int add(ServerInfoEntry entry) {
        try {
            if (entry != null) {
                ServerInfo entity = new ServerInfo();
                BeanUtils.copyProperties(entry, entity);
                entity.setCreateTime(System.currentTimeMillis());
                serverInfoMapper.addAndId(entity);
                int id = entity.getId();
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateById(ServerInfoEntry entry) {
        try {
            if (entry != null && entry.getId() > 0) {
                ServerInfo entity = new ServerInfo();
                BeanUtils.copyProperties(entry, entity);
                entity.setModifyTime(System.currentTimeMillis());
                int num = serverInfoMapper.update(entity);
                return num;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int save(ServerInfoEntry entry) {
        try {
            int num = 0;
            if (entry != null) {
                if (entry.getId() > 0) {
                    num = updateById(entry);
                } else {
                    num = add(entry);
                }
                return num;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ServerInfoEntry get(int id) {
        try {
            ServerInfo serverInfo = serverInfoMapper.get(id);
            ServerInfoEntry entry = new ServerInfoEntry();
            BeanUtils.copyProperties(serverInfo, entry);
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ServerInfoEntry> getList(ServerInfoParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            List<ServerInfo> entityList = serverInfoMapper.getList(map);
            List<ServerInfoEntry> entryList = new ArrayList<>();
            ServerInfoEntry entry;
            for (ServerInfo item : entityList) {
                entry = new ServerInfoEntry();
                BeanUtils.copyProperties(item, entry);
                entryList.add(entry);
            }
            return entryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int remove(ServerInfoParam param) {
        try {
            Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
            int num = serverInfoMapper.remove(map);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
