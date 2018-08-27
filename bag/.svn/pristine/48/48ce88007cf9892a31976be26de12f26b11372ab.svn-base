package com.zhskg.bag.mapper;

import com.zhskg.bag.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lwb on 2018/5/8.
 */
public interface UserMapper
{
    User get(@Param("userId") Long userId);
    Integer removeById(@Param("userId") Long userId);
    Integer realRemoveById(@Param("userId") Long userId);
    Integer addAndId(User user);
    Integer updateById(User user);
    List<User> getList(@Param("user") User user);
    List<User> getPageList(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize,@Param("user") User user);
    Integer getCount(@Param("user") User user);
    User getByAccountId(@Param("accountId") Long accountId);
    User getByFirst(Map<String, Object> map);
    List<User> checkMobileNumberByUserId(Map<String, Object> map);

    /**
     * 通过ID修改用户手机号
     * @param userId  用户id
     * @param mobileNumber 手机号
     * @return int
     * @author huchuan
     */
    Integer updatePhoneById(@Param("userId") Long userId, @Param("mobileNumber") String mobileNumber);

    /**
     * 根据用户id获取账号id
     * @param userId 用户id
     * @return accountId
     */
    Long queryAccountId(Long userId);

    /**
     * 手机重名查询
     * @param user
     * @return String
     */
    String mobileNumberisExist(User user);
}
