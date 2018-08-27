package com.zhskg.bag.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.User;
import com.zhskg.bag.mapper.UserMapper;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.UserParam;
import com.zhskg.bag.service.UserService;

/**
 * Created by lwb on 2018/5/8.
 */
@Service(version = "1.0")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    public UserEntry getByFirst(UserParam params) {
        Map<String,Object> map = JSON.parseObject(JSON.toJSONString(params));
        User user = userMapper.getByFirst(map);
        if(user!=null){
            UserEntry userEntry = new UserEntry();
            BeanUtils.copyProperties(user,userEntry);
            return userEntry;
        }else{
            return null;
        }
    }

    public UserEntry get(Long userId) {
        try {
            UserEntry data = new UserEntry();
            BeanUtils.copyProperties(userMapper.get(userId),data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserEntry getByAccountId(Long accountId) {
        try {
            UserEntry data = new UserEntry();
            BeanUtils.copyProperties(userMapper.getByAccountId(accountId),data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer removeById(Long userId) {
        try{
            return userMapper.removeById(userId);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }


    public Integer realRemoveById(Long userId) {
        try{
            return userMapper.realRemoveById(userId);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public Long save(UserEntry userData) {
        try {
            Long id = userData.getUserId();
            User entity = new User();
            if (id != null && id != 0) {
                entity = userMapper.get(id);
                if (entity != null) {
                    BeanUtils.copyProperties(userData,entity);
                    entity.setModifyTime(System.currentTimeMillis());
                    entity.setMobileNumber(null);
                    userMapper.updateById(entity);
                }
            }else {
                BeanUtils.copyProperties(userData,entity);
                entity.setCreateTime(System.currentTimeMillis());
                userMapper.addAndId(entity);
                id = entity.getUserId();
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    private List<UserEntry> getEntryList(List<User> userPlatforms) {
        List<UserEntry> list = new ArrayList<>();
        if (userPlatforms!=null &&userPlatforms.size() > 0) {
            for (User model : userPlatforms){
                UserEntry entry = new UserEntry();
                BeanUtils.copyProperties(model,entry);
                list.add(entry);
            }
        }
        return list;
    }

    public List<UserEntry> getList(UserParam param) {
        List<UserEntry> list = new ArrayList<>();
        try {
            User model = new User();
            BeanUtils.copyProperties(param,model);
            List<User> sellerList = userMapper.getList(model);
            list = getEntryList(sellerList);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserEntry> getPageList(Integer pageIndex, Integer pageSize, UserParam param) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<UserEntry> list = new ArrayList<>();
        try {
            User model = new User();
            BeanUtils.copyProperties(param,model);
            List<User> userListList = userMapper.getPageList(pageIndex, pageSize,model);
            list = getEntryList(userListList);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getCount(UserParam param) {
        try {
            User model = new User();
            BeanUtils.copyProperties(param,model);
            Integer num = userMapper.getCount(model);
            return num;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public Long addAndId(UserEntry data) {
        try {
            User entity = new User();
            BeanUtils.copyProperties(data,entity);
            entity.setDeleteFlag(0);
            entity.setCreateTime(System.currentTimeMillis());
            userMapper.addAndId(entity);
            Long id = entity.getUserId();
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

    /**
     * 通过ID修改用户手机号
     * @param userId  用户id
     * @param mobileNumber 手机号
     * @return Boolean 是否修改成功
     * @author huchuan
     */
    public Boolean updatePhoneById(Long userId, String mobileNumber) {
        return userMapper.updatePhoneById(userId, mobileNumber) > 0;
    }

	@Override
	public int savePage(UserEntry data) {
		Long l = save(data);
		return l > 0 ? 1 : 0;
	}

    /**
     * 手机号重名验证
     * @param userParam 用户对象
     * @return Booelan
     */
    @Override
    public Boolean mobileNumberisExist(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        return userMapper.mobileNumberisExist(user) != null;
    }

    /**
     * 根据用户id查询accountId
     * @param userId 用户id
     * @return accountId
     */
    @Override
    public Long queryAccountId(Long userId) {
        return userMapper.queryAccountId(userId);
    }
}
