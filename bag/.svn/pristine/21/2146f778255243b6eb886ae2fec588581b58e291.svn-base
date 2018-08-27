
package com.zhskg.bag.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.zhskg.bag.model.AccountEntry;
import com.zhskg.bag.model.Password;
import com.zhskg.bag.param.AccountParam;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface AccountService {
    /**
     * 添加账号
     * @param accountEntry 账号信息
     * @return 添加的账号Id
     */
    Long addAndId(AccountEntry accountEntry);

    /**
     * 批量添加账号
     * @param accountList 账号信息列表
     * @return 添加的账号数
     */
    Integer batchAdd(List<AccountEntry> accountList);

    /**
     * 根据账号Id逻辑删除账号
     * @param accountId 账号Id
     * @return 逻辑删除的账号数
     */
    Integer removeById(Long accountId);

    /**
     * 根据查询条件逻辑删除账号
     * @param condition 查询条件
     * @return 逻辑删除的符合查询条件的账号数
     */
    Integer remove(AccountParam condition);

    /**
     * 根据账号Id物理删除账号
     * @param accountId 账号Id
     * @return 物理删除的账号数
     */
    Integer realRemoveById(Long accountId);

    /**
     * 根据查询条件物理删除账号
     * @param condition 查询条件
     * @return 物理删除的符合查询条件的账号数
     */
    Integer realRemove(AccountParam condition);

    /**
     * 根据账号Id获取账号
     * @param accountId 账号Id
     * @return 账号信息
     */
    AccountEntry get(Long accountId);

    /**
     * 根据查询条件和排序条件获取第一个账号
     * @param condition 查询条件
     * @return 符合查询条件和排序条件的第一个账号信息
     */
    AccountEntry getFirst(AccountParam condition);

    /**
     * 根据查询条件获取账号信息列表
     * @param condition 查询条件
     * @return 符合查询条件的账号信息列表
     */
    List<AccountEntry> getList(AccountParam condition);

    /**
     * 根据查询条件和排序条件获取第pageIndex页的账号信息列表（pageSize条）
     * @param pageIndex 页索引
     * @param pageSize 页大小
     * @param condition 查询条件
     * @return 符合查询条件和排序条件获取第pageIndex页的账号信息列表（pageSize条）
     */
    List<AccountEntry> getPageList(Integer pageIndex, Integer pageSize, AccountParam condition);

    /**
     * 根据查询条件获取账号数
     * @param condition 查询条件
     * @return 符合查询条件的账号数
     */
    Integer getCount(AccountParam condition);

    /**
     * 添加或修改账号
     * @param accountEntry 要添加或修改的账号信息,可同时保存账号的角色信息
     * @return 添加或修改的账号Id
     * @throws NoSuchAlgorithmException 无法找到所使用的加密算法
     */
    Long save(AccountEntry accountEntry) throws NoSuchAlgorithmException;

    /**
     * 根据查询条件修改账号
     * @param accountEntry 要修改的账号信息
     * @param condition 查询条件
     * @return 修改的账号数
     */
    Integer update(AccountEntry accountEntry, AccountParam condition);

    /**
     * 验证用户名和密码
     * @param username 用户名
     * @param password 密码 明文
     * @return 验证通过的用户信息
     * @throws NoSuchAlgorithmException 无法找到所使用的加密算法
     */
    AccountEntry signIn(String username, String password) throws NoSuchAlgorithmException;

    /**
     * 根据账号Id更改密码
     * @param password 包含新旧密码和账号Id
     * @return 更改的条数
     * @throws NoSuchAlgorithmException 无法找到所使用的加密算法
     */
    Integer changePassword(Password password) throws NoSuchAlgorithmException;

    /**
     * 根据账号Id重置密码
     * @param accountId 账号Id
     * @return 新密码 明文
     * @throws NoSuchAlgorithmException 无法找到所使用的加密算法
     */
    String resetPassword(Long accountId) throws NoSuchAlgorithmException;

    /**
     * 手机号验重
     * @param mobileNumber 手机号
     * @return 存在的手机号数量
     */
    Integer checkMobile(String mobileNumber);

    /**
     * 通过ID修改用户手机号
     * @param accountId  用户id
     * @param mobileNumber 手机号
     * @return Boolean
     * @author huchuan
     */
    Boolean updatePhoneById(Long accountId, String mobileNumber);
    /**
     * 检查account唯一性
     * @param accountId 需要忽略的id 新增时传null
     * @param account 账号
     * @param phone 电话
     * @param email 邮箱
     * @param identityCardNo 省份证号码
     * @return
     */
    AccountEntry checkUser (Long accountId,String account,String phone,String email,String identityCardNo);

    /**
     * 查询用户
     * @param accountId id
     * @param password password
     * @return boolean
     */
    Boolean queryUserByIdAndPassword(Long accountId, String password);
}