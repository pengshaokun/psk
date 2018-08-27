package com.zhskg.bag.service;

import java.util.List;

import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.UserParam;

/**
 * Created by lwb on 2018/5/8.
 */
public interface UserService
{
    /**
     * 根据平台用户id获取平台用户对象
     *
     * @param userId 平台用户id
     * @return 平台用户对象
     */
    UserEntry get(Long userId);

    /**
     * 根据用户id获取平台用户对象
     * @param accountId 用户id
     * @return 平台用户对象
     */
    UserEntry getByAccountId(Long accountId);

    /**
     * 根据平台用户id删除平台用户
     *
     * @param userId 平台用户id
     * @return 删除的记录条数
     */
    Integer removeById(Long userId);

    /**
     * 根据平台用户id删除平台用户
     *
     * @param userId 平台用户id
     * @return 删除的记录条数
     */
    Integer realRemoveById(Long userId);

    /**
     * 保存平台用户
     *
     * @param userData 传入的平台用户对象
     * @return 平台用户id
     */
    Long save(UserEntry userData);
    /**
     * 平台用户添加
     *
     * @param userData 传入的平台用户对象
     * @return 平台用户id
     */
    Long addAndId(UserEntry userData);

    /**
     * 根据条件获取平台用户列表
     *
     * @param param 获取平台用户列表的条件对象
     * @return 平台用户列表
     */
    List<UserEntry> getList(UserParam param);

    /***
     * 根据条件对象获取平台用户分页列表
     * @param pageIndex 页码
     * @param pageSize 每夜记录条数
     * @param param 获取平台用户分页列表的条件对象
     * @return
     */
    List<UserEntry> getPageList(Integer pageIndex, Integer pageSize, UserParam param);

    /**
     * 根据条件对象获取平台用户记录条数
     *
     * @param param 获取平台用户记录条数条件对象
     * @return 记录条数
     */
    Integer getCount(UserParam param);

    /**
     * 根据条件获取第一条用户信息
     *
     * @param params {}
     * @return 用户信息
     */
    UserEntry getByFirst(UserParam params);

    /**
     * 通过ID修改用户手机号
     * @param userId  用户id
     * @param mobileNumber 手机号
     * @return Boolean 是否修改成功
     * @author huchuan
     */
    Boolean updatePhoneById(Long userId, String mobileNumber);

    /**
     * 保存页面提交的信息
     * @param data
     * @return
     */
	int savePage(UserEntry data);

    /**
     * 手机重名查询
     * @param userParam 用户对象
     * @return boolean
     */
    Boolean mobileNumberisExist(UserParam userParam);

    /**
     * 根据用户id获取账号id
     * @param userId 用户id
     * @return accountId
     */
    Long queryAccountId(Long userId);

}
