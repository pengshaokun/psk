package com.zhskg.bag.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.entity.ThirdPartyInfo;
import com.zhskg.bag.entity.User;
import com.zhskg.bag.mapper.ThirdPartyInfoMapper;
import com.zhskg.bag.mapper.UserMapper;
import com.zhskg.bag.model.ThirdPartyInfoEntry;
import com.zhskg.bag.model.UserDeviceEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.ThirdPartyInfoParam;
import com.zhskg.bag.service.ThirdPartyInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version = "1.0", interfaceName = "com.zhskg.bag.service.ThirdPartyInfoService")
public class ThirdPartyInfoServiceImpl  implements ThirdPartyInfoService {
    @Autowired
    protected ThirdPartyInfoMapper thirdPartyInfoMapper;

    @Autowired
    protected UserMapper userMapper;

    @Override
    public Integer updateById(ThirdPartyInfoEntry thirdPartyInfoEntry) {
        ThirdPartyInfo thirdPartyInfo=new ThirdPartyInfo();
        BeanUtils.copyProperties(thirdPartyInfo,thirdPartyInfoEntry);
        return thirdPartyInfoMapper.updateById(thirdPartyInfo);
    }

    @Override
    public Integer removeById(String openId) {
        return thirdPartyInfoMapper.removeById(openId);
    }

    @Override
    public List<ThirdPartyInfoEntry> getByUserIdAndTerraceType(Map<String, Object> map) {
        List<ThirdPartyInfo> thirdPartyInfos=thirdPartyInfoMapper.getByUserIdAndTerraceType(map);
        List<ThirdPartyInfoEntry> thirdPartyInfoEntries=new ArrayList<>();
        thirdPartyInfos.forEach(thirdPartyInfo -> {
            ThirdPartyInfoEntry thirdPartyInfoEntry=new ThirdPartyInfoEntry();
            BeanUtils.copyProperties(thirdPartyInfo,thirdPartyInfoEntry);
            thirdPartyInfoEntries.add(thirdPartyInfoEntry);
        });

        return thirdPartyInfoEntries;
    }

    @Override
    public int setUserId(String openId, Long userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        map.put("openId",openId);
        int result = thirdPartyInfoMapper.setUserId(map);
        return result;
    }

    @Override
    public ThirdPartyInfoEntry getByUserId(long userId)
    {
        List<ThirdPartyInfo> list = thirdPartyInfoMapper.getByUserId(userId);
        if (list!=null && list.size()>0){
            ThirdPartyInfo item = list.get(0);
            ThirdPartyInfoEntry entity = new ThirdPartyInfoEntry();
            BeanUtils.copyProperties(item, entity);
            return entity;
        }
        return null;
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean add(ThirdPartyInfoEntry data) {
        try {
            if (data != null) {
                User user = dataToUser(data, true);
                ThirdPartyInfo entity = new ThirdPartyInfo();
                BeanUtils.copyProperties(data, entity);
                // entity.setUserId(user.getUserId());
                entity.setDeleteFlag(0);
                entity.setCreateTime(System.currentTimeMillis());
                thirdPartyInfoMapper.add(entity);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    protected int genderDeal(String gender) {
        int _gender = 0;
        if (gender != null && !"".equals(gender)) {
            if (gender.equals("男")) {
                _gender = 1;
            } else if (gender.equals("女")) {
                _gender = 2;
            }
        }
        return _gender;
    }

    protected User dataToUser(ThirdPartyInfoEntry data, boolean isAdd) {
        User user = new User();
        if (isAdd) {
            user.setDeleteFlag(0);
            user.setCreateTime(System.currentTimeMillis());
        } else {
            user.setModifyTime(System.currentTimeMillis());
        }
        user.setNickName(data.getName());
        user.setHeadPortrait(data.getIconUrl());
        user.setGender(genderDeal(data.getGender()));
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean update(ThirdPartyInfoEntry data,ThirdPartyInfo entity) {
        try {
            if (data != null && data.getOpenId() != null && !"".equals(data.getOpenId())) {
                if (entity != null) {
                  /*  User user = dataToUser(data, false);
                    user.setUserId(entity.getUserId());
                    userMapper.updateById(user);*/
                   long userId=entity.getUserId();
                    BeanUtils.copyProperties(data, entity);
                   entity.setUserId(userId);
                    thirdPartyInfoMapper.updateById(entity);
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    public boolean save(ThirdPartyInfoEntry data) {
        if (data != null && data.getOpenId()!=null && !"".equals(data.getOpenId())) {
            ThirdPartyInfo info = thirdPartyInfoMapper.get(data.getOpenId());
            if (info != null ) {
                return update(data,info);
            } else {
                return add(data);
            }
        }
        return false;
    }


    @Override
    public synchronized ThirdPartyInfoEntry signIn(ThirdPartyInfoEntry data) {
        if (data != null) {
            boolean rslt;
            if (data.getOpenId() != null && !"".equals(data.getOpenId())) {
                ThirdPartyInfo thirdPartyInfo = thirdPartyInfoMapper.get(data.getOpenId());
                if (thirdPartyInfo != null) {
                    //防止未绑定手机号退出应用二次登录
                    if (thirdPartyInfo.getUserId()==null){
                        BeanUtils.copyProperties(thirdPartyInfo,data);
                        return data;
                    }
                    rslt = update(data,thirdPartyInfo);
                    data.setUserId(thirdPartyInfo.getUserId());
                } else {
                    rslt = add(data);
                    thirdPartyInfo = thirdPartyInfoMapper.get(data.getOpenId());
                    BeanUtils.copyProperties(thirdPartyInfo, data);
                }
                if (rslt) {
                    return data;
                }
            }
        }
        return null;
    }

    @Override
    public ThirdPartyInfoEntry get(String openId) {
        ThirdPartyInfoEntry data = null;
        if (openId != null && !"".equals(openId)) {
            ThirdPartyInfo entity = thirdPartyInfoMapper.get(openId);
            if (entity != null) {
                data = new ThirdPartyInfoEntry();
                BeanUtils.copyProperties(entity, data);
            }
        }
        return data;
    }

    @Override
    public ThirdPartyInfoEntry getFirst(String openId, String token) {
        ThirdPartyInfoEntry data = null;
        ThirdPartyInfo entity = thirdPartyInfoMapper.getFirst(openId, token);
        if (entity != null) {
            data = new ThirdPartyInfoEntry();
            BeanUtils.copyProperties(entity, data);
        }
        return data;
    }

    @Override
    public List<ThirdPartyInfoEntry> getList(ThirdPartyInfoParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        List<ThirdPartyInfoEntry> list = entityToEntry(thirdPartyInfoMapper.getList(map));
        return list;
    }

    @Override
    public List<ThirdPartyInfoEntry> getPageList(int pageIndex, int pageSize, ThirdPartyInfoParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        int start = pageIndex > 0 ? pageIndex - 1 : pageIndex;
        map.put("pageIndex", start);
        map.put("pageSize", pageSize);
        List<ThirdPartyInfoEntry> list = entityToEntry(thirdPartyInfoMapper.getPageList(map));
        return list;
    }

    @Override
    public int getCount(ThirdPartyInfoParam param) {
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(param));
        Integer num = thirdPartyInfoMapper.getCount(map);
        num = num == null ? 0 : num;
        return num;
    }

    protected List<ThirdPartyInfoEntry> entityToEntry(List<ThirdPartyInfo> list) {
        List<ThirdPartyInfoEntry> _list = new ArrayList<ThirdPartyInfoEntry>();
        if (list != null && list.size() > 0) {
            ThirdPartyInfoEntry entry;
            for (ThirdPartyInfo item : list) {
                entry = new ThirdPartyInfoEntry();
                BeanUtils.copyProperties(item, entry);
                _list.add(entry);
            }
        }
        return _list;
    }
}
