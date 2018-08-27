
package com.zhskg.bag.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.entity.Account;
import com.zhskg.bag.entity.AccountRole;
import com.zhskg.bag.mapper.AccountMapper;
import com.zhskg.bag.mapper.AccountRoleMapper;
import com.zhskg.bag.model.AccountEntry;
import com.zhskg.bag.model.AccountRoleEntry;
import com.zhskg.bag.model.Password;
import com.zhskg.bag.param.AccountParam;
import com.zhskg.bag.param.AccountRoleParam;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.util.EncrypUtil;
import com.zhskg.bag.util.ValidatorUtil;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
@Service(interfaceName = "com.zhskg.bag.service.AccountService",version = "1.0")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRoleMapper accountRoleMapper;

    public Long addAndId(AccountEntry data) {
        try {
            data.setPassword(EncrypUtil.encrypMD5(data.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Account entity = getEntity(data);
        Account entity = new Account();
        BeanUtils.copyProperties(data, entity);
        accountMapper.addAndId(entity);
        return entity.getAccountId();
    }

    public Integer batchAdd(List<AccountEntry> accountList) {
        List<Account> list = new ArrayList<Account>();
        //accountList.forEach((data) -> list.add(getEntity(data)));
        Account entity;
        for (AccountEntry item : accountList) {
            entity = new Account();
            BeanUtils.copyProperties(item, entity);
            list.add(entity);
        }
        return accountMapper.batchAdd(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer removeById(Long accountId) {
        try {
            AccountRoleParam condition = new AccountRoleParam();
            condition.setAccountId(accountId);
            accountRoleMapper.realRemove(condition);
            return accountMapper.removeById(accountId);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    public Integer remove(AccountParam condition) {
        return accountMapper.remove(condition);
    }

    public Integer realRemoveById(Long accountId) {
        return accountMapper.realRemoveById(accountId);
    }

    public Integer realRemove(AccountParam condition) {
        return accountMapper.realRemove(condition);
    }

    public AccountEntry get(Long accountId) {
        AccountEntry entry = new AccountEntry();
        Account account = accountMapper.get(accountId);
        BeanUtils.copyProperties(account, entry);
        return entry;
    }

    public AccountEntry getFirst(AccountParam condition) {
        AccountEntry entry = new AccountEntry();
        Account account = accountMapper.getFirst(condition);
        if(account==null){
            return null;
        }
        BeanUtils.copyProperties(account, entry);
        return entry;
    }

    public List<AccountEntry> getList(AccountParam condition) {
        List<AccountEntry> list = new ArrayList<AccountEntry>();
        List<Account> accountList = accountMapper.getList(condition);
        AccountEntry entry;
        for (Account item : accountList) {
            entry = new AccountEntry();
            BeanUtils.copyProperties(item, entry);
            list.add(entry);
        }
        //accountList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public List<AccountEntry> getPageList(Integer pageIndex, Integer pageSize, AccountParam condition) {
        pageIndex = (pageIndex - 1) * pageSize;
        List<AccountEntry> list = new ArrayList<AccountEntry>();
        List<Account> accountList = accountMapper.getPageList(pageIndex, pageSize, condition);
        AccountEntry entry;
        for (Account item : accountList) {
            entry = new AccountEntry();
            BeanUtils.copyProperties(item, entry);
            list.add(entry);
        }
        //accountList.forEach((entity) -> list.add(getEntry(entity)));
        return list;
    }

    public Integer getCount(AccountParam condition) {
        return accountMapper.getCount(condition);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long save(AccountEntry accountEntry) throws NoSuchAlgorithmException {
        try {
            AccountParam accountParam = new AccountParam();
            accountParam.setAccountId(accountEntry.getAccountId());
            accountParam.setMobileNumber(accountEntry.getMobileNumber());
            accountParam.setAccount(accountEntry.getAccount());
            accountParam.setEmail(accountEntry.getEmail());
            accountParam.setIdentityCardNo(accountEntry.getIdentityCardNo());
            Account oldAccount = accountMapper.getFirst(accountParam);
            Account entity;
            if (oldAccount != null) {
                accountEntry.setAccountId(oldAccount.getAccountId());
                //entity = getEntity(accountEntry);
                entity = new Account();
                BeanUtils.copyProperties(accountEntry, entity);
                accountMapper.updateById(entity);
            } else {
                accountEntry.setPassword(EncrypUtil.encrypMD5(accountEntry.getPassword()));
                //entity = getEntity(accountData);
                entity = new Account();
                BeanUtils.copyProperties(accountEntry, entity);
                accountMapper.addAndId(entity);
            }
            List<AccountRole> accountRoles = new ArrayList<>();
            if (accountEntry.getRoles() != null) {
                accountRoleMapper.realRemoveByAccountId(entity.getAccountId());
                if (accountEntry.getRoles().length > 0) {
                    for (AccountRoleEntry ur : accountEntry.getRoles()) {
                        AccountRole accountRole = new AccountRole();
                        accountRole.setAccountId(entity.getAccountId());
                        accountRole.setRoleId(ur.getRoleId());
                        accountRole.setDefaultFlag(ur.getDefaultFlag());
                        accountRoles.add(accountRole);
                    }
                    accountRoleMapper.batchAdd(accountRoles);
                }
            }
            return entity.getAccountId();
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    public Integer update(AccountEntry accountEntry, AccountParam condition) {
        Account entity = new Account();
        BeanUtils.copyProperties(entity, accountEntry);
        Integer num = accountMapper.update(entity, condition);
        return num;
    }

    public AccountEntry signIn(String username, String password) throws NoSuchAlgorithmException {
        username = username.trim();
        Pattern usernamePattern = Pattern.compile(ValidatorUtil.REGEX_USERNAME);
        Pattern mobilePattern = Pattern.compile(ValidatorUtil.REGEX_MOBILE);
        Pattern emailPattern = Pattern.compile(ValidatorUtil.REGEX_EMAIL);
        Pattern idCardPattern = Pattern.compile(ValidatorUtil.REGEX_ID_CARD);
        Map<String, Object> _map = new HashMap<>();
        AccountParam condition;
        AccountEntry accountEntry;
        if (usernamePattern.matcher(username).matches()) {
            _map.put("account", username);
        } else if (mobilePattern.matcher(username).matches()) {
            _map.put("mobileNumber", username);
        } else if (emailPattern.matcher(username).matches()) {
            _map.put("email", username);
        } else if (idCardPattern.matcher(username).matches()) {
            _map.put("identityCardNo", username);
        } else {
            return null;
        }
        condition = JSON.parseObject(JSON.toJSONString(_map), AccountParam.class);
        Account entity = accountMapper.getFirst(condition);
        if(entity==null) return null;
        accountEntry = new AccountEntry();
        BeanUtils.copyProperties(entity,accountEntry);
        //accountEntry = getData();
        if ( password != null && password != "") {
            if (accountEntry.getPassword() != null && accountEntry.getPassword().equals(EncrypUtil.encrypMD5(password))) {
                return accountEntry;
            }
        }
        return null;
    }
    public Integer changePassword(Password password) throws NoSuchAlgorithmException {
        Account account = accountMapper.get(password.getAccountId());
        if (account != null && (account.getPassword().equals(EncrypUtil.encrypMD5(password.getOldPassword())) || account.getPassword().equals(password.getOldPassword()))) {
            return accountMapper.changePassword(EncrypUtil.encrypMD5(password.getNewPassword()), password.getAccountId());
        } else {
            return 0;
        }
    }

    public String resetPassword(Long accountId) throws NoSuchAlgorithmException {
        Account account = accountMapper.get(accountId);
        String newPassWord;
        if (account != null) {
            if (account.getMobileNumber() != null && account.getMobileNumber().length() == 11) {
                newPassWord = "pwd"+account.getMobileNumber().substring(5);
            } else {
                newPassWord = "pwd"+Integer.toString(100000 + new Random().nextInt(899999));
            }
            accountMapper.resetPassword(EncrypUtil.encrypMD5(newPassWord), accountId);
            return newPassWord;
        }
        return null;
    }

    public Integer checkMobile(String mobileNumber) {
        return accountMapper.checkMobile(mobileNumber);
    }

    /**
     * 通过ID修改用户手机号
     * @param accountId  用户id
     * @param mobileNumber 手机号
     * @return Boolean
     * @author huchuan
     */
    public Boolean updatePhoneById(Long accountId, String mobileNumber) {
        Account account=new Account();
        account.setAccountId(accountId);
        account.setMobileNumber(mobileNumber);
       // return accountMapper.updatePhoneById(accountId, mobileNumber) > 0;
        return  accountMapper.updateById(account)>0;
    }
    @Override
    public AccountEntry checkUser(Long accountId, String account, String phone, String email, String identityCardNo) {
    	AccountParam param = new AccountParam();
    	param.setAccountId(accountId);
    	param.setAccount(account);
    	param.setEmail(email);
    	param.setIdentityCardNo(identityCardNo);
    	param.setMobileNumber(phone);
    	Account db = accountMapper.checkUser(param);
    	if(db == null){
    		return null;
    	}
    	AccountEntry entry = new AccountEntry();
    	BeanUtils.copyProperties(db, entry);
		return entry;
    }
    /**
     * 查询用户
     * @param accountId id
     * @param password password
     * @return boolean
     */
    public Boolean queryUserByIdAndPassword(Long accountId, String password) {
        try {
            password = EncrypUtil.encrypMD5(password);
            return accountMapper.queryUserByIdAndPassword(accountId, password) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}