
package com.zhskg.bag.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhskg.bag.entity.Account;
import com.zhskg.bag.param.AccountParam;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public interface AccountMapper {
    Integer add(Account account);
    
    Long addAndId(Account account);
    
    Integer batchAdd(List<Account> accountList);
    
    Integer removeById(@Param("accountId") Long accountId);
    
    Integer remove(AccountParam condition);
    
    Integer realRemoveById(@Param("accountId") Long accountId);
    
    Integer realRemove(AccountParam condition);
    
    Account get(@Param("accountId") Long accountId);
    
    Account getFirst(AccountParam condition);
    
    List<Account> getList(@Param("condition") AccountParam condition);
    
    List<Account> getPageList(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize, @Param("condition") AccountParam condition);
    
    Integer getCount(@Param("condition") AccountParam condition);

    Integer updateById(Account account);

    Integer update(@Param("account") Account account, @Param("condition") AccountParam condition);

    Integer changePassword(@Param("newPassword") String newPassword, @Param("accountId") Long accountId);

    Integer resetPassword(@Param("newPassword") String newPassword, @Param("accountId") Long accountId);

    Integer checkMobile(@Param("mobileNumber") String mobileNumber);

    /**
     * 通过ID修改用户手机号
     * @param accountId  用户id
     * @param mobileNumber 手机号
     * @return int
     * @author huchuan
     */
    Integer updatePhoneById(@Param("accountId") Long accountId, @Param("mobileNumber") String mobileNumber);
    /**
     * 检查account唯一性
     * @param param
     * @return
     */
	Account checkUser(AccountParam param);
    /**
     * 查询用户
     * @param accountId id
     * @param password password
     * @return account
     */
    Account queryUserByIdAndPassword(@Param("accountId") Long accountId, @Param("password") String password);
}