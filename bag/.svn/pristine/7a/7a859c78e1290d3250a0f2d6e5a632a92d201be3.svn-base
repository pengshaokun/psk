package com.zhskg.bag.controller.web;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import com.zhskg.bag.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.data.AdminUserData;
import com.zhskg.bag.data.LoginData;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.AccountEntry;
import com.zhskg.bag.model.Password;
import com.zhskg.bag.model.Permission;
import com.zhskg.bag.model.RoleEntry;
import com.zhskg.bag.model.TreeNode;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.AccountParam;
import com.zhskg.bag.param.UserParam;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.RoleService;
import com.zhskg.bag.service.UserService;

/**
 * Created by lwb on 2018/5/8.
 */
@Controller
@RequestMapping(value = "web/user/")
public class WebUserController {
    @Reference(version = "1.0")
    private AccountService accountService;
    @Reference(version = "1.0")
    private UserService userService;
    @Reference(version = "1.0")
    private RoleService roleService;

    @Autowired
    private LoginContext loginContext;

    /**
     * web端用户登录
     *
     * @param data{ userName 用户名
     *              passWord 密码
     *              }
     * @return 1：成功 0：失败
     */
    @AllowAnonymous
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    public Object signIn(@RequestBody LoginData data) {
        try {
            if (data != null) {
                String account = data.getUserName();
                String pwd = data.getPassWord();
                if (account != null && !"".equals(account) && pwd != null && !"".equals(pwd)) {
                    AccountEntry accountData = accountService.signIn(account, pwd);
                    if (accountData != null) {
                    	if(accountData.getEnableFlag() != 1){
                    		return ReturnMapByBack.result(0, "系统拒绝您登录");
                    	}
                        Integer adminFlag = accountData.getAdminFlag();
                        Integer superFlag = accountData.getSuperFlag();
                        //登录信息
                        Long accountId = accountData.getAccountId();
                        LoginInfo loginInfo = new LoginInfo();
                        loginInfo.setAccount(accountData.getAccount());
                        loginInfo.setAccountId(accountId);
                        loginInfo.setEmail(accountData.getEmail());
                        loginInfo.setMobileNumber(accountData.getMobileNumber());
                        loginInfo.setComment(accountData.getComment());
                        loginInfo.setIdentityCardNo(accountData.getIdentityCardNo());
                        loginInfo.setNextChangeFlag(accountData.getNextChangeFlag());
                        loginInfo.setSuperFlag(superFlag);
                        loginInfo.setAdminFlag(adminFlag);
                        loginInfo.setFullName(accountData.getName());
                        UserParam param = new UserParam();
                        param.setAccountId(accountId);
                        UserEntry userData = userService.getByFirst(param);
                        Long userId = null;
                        if (userData != null && userData.getUserId() != null) {
                            userId = userData.getUserId();
                            loginInfo.setFullName(userData.getFullName());
                            loginInfo.setUserId(userId);
                            loginInfo.setHeadImgUrl(userData.getHeadPortrait());
                            loginInfo.setBirthDate(userData.getBirthDate());
                            loginInfo.setNickName(userData.getNickName());
                            loginInfo.setGender(userData.getGender());
                        } else {
                            if (superFlag != 1 && adminFlag != 1) {
                                userData = new UserEntry();
                                userData.setAccountId(accountId);
                                userData.setMobileNumber(accountData.getMobileNumber());
                                userData.setCreateOn(accountId);
                                userData.setCreateTime(System.currentTimeMillis());
                                userData.setDeleteFlag(0);
                                userId = userService.addAndId(userData);
                                if (userId != null && userId > 0) {
                                    loginInfo.setFullName(accountData.getName());
                                    loginInfo.setUserId(userId);
                                }
                            }
                        }

                        Long roleId = null;
                        if (adminFlag != null && adminFlag == 0) {
                            //获取默认角色
                            RoleEntry roleData = roleService.getDefaultRoleByAccountId(accountId);
                            if(roleData != null){
                            	roleId = roleData.getRoleId();
                            	loginInfo.setRoleId(roleId);
                            	loginInfo.setRoleName(roleData.getRoleName());
                            }
                        }
                        //菜单信息
                        List<TreeNode> menuList = roleService.getRoleMenu(adminFlag,roleId);
                        loginInfo.setMenus(menuList);
                        //资源信息
                        List<Permission> resourceList = roleService.getRoleResource(adminFlag,roleId);
                        loginInfo.setResources(resourceList);
                        //设置缓存信息
                        String token = UUID.randomUUID().toString().replaceAll("-", "");
                        loginContext.setAuthCookieWeb(token, JSON.toJSONString(loginInfo), 0,loginInfo.getUserId());
                        return ReturnMapByBack.result(1, "登录成功！");
                    }else{
                        return ReturnMapByBack.result(0, "账号或密码错误！");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMapByBack.result(0, "登录失败，服务器异常！");
        }
        return ReturnMapByBack.result(0, "登录失败！");
    }

    /**
     * web端退出登录
     *
     * @return 1：成功 0：失败
     */
    @RequestMapping(value = "sign/out", method = RequestMethod.GET)
    @ResponseBody
    public Object signOut() {
        try {
            loginContext.signOut();
            return ReturnMapByBack.result(1, "退出成功！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "退出失败！");
        }
    }

    /**
     * 获取用户详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") Long id) {
        try {
            AccountEntry data = accountService.get(id);
            return ReturnMapByBack.result(1, "获取成功！", data);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 用户信息保存
     * @param data
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody @Validated AdminUserData data,BindingResult result) {
        try {
        	if (result.hasErrors()){
                FieldError fieldError = result.getFieldError();
                return ReturnMapByBack.result(0,fieldError.getDefaultMessage());
            }
            LoginInfo loginInfo = loginContext.getInfoWeb();

            /* 参数校验 */
            if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录！");
            if (data == null) return ReturnMapByBack.result(0, "参数不能为空!");

            // 必填项检测
            if (data.getFullName() == null) return ReturnMapByBack.result(0, "姓名不能为空！");
            if (data.getMobileNumber() == null) return ReturnMapByBack.result(0, "手机号码不能为空！");
            if (data.getIdentityCardNo() == null) return ReturnMapByBack.result(0, "身份证号码不能为空！");
            if (data.getEmail() == null) return ReturnMapByBack.result(0, "邮箱不能为空！");

            // 添加时 验证密码
            if (data.getAccountId() == null || data.getAccountId() == 0) {
                if (data.getPassword() == null) return ReturnMapByBack.result(0, "密码不能为空！");
                Pattern usernamePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
                if(!usernamePattern.matcher(data.getPassword()).matches()){
                	return ReturnMapByBack.result(0, "密码格式不对,6-18数字加字母!");
                }
            }
            // 数据合法性检测
            if (!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())) return ReturnMap.result(0, "输入的手机号码格式不对！");
            if (!RegexValidateUtil.checkEmail(data.getEmail())) return ReturnMap.result(0, "输入的邮箱格式不对！");
            if (!RegexValidateUtil.checkIdentityCard(data.getIdentityCardNo())) return ReturnMap.result(0, "输入的身份证号码格式不对！");
            //检查唯一性
            AccountEntry haverOther = accountService.checkUser(data.getAccountId(), data.getAccount(), data.getMobileNumber(), data.getEmail(), data.getIdentityCardNo());
            if(haverOther != null){
            	if(StringUtils.equals(haverOther.getMobileNumber(), data.getMobileNumber())){
            		return ReturnMapByBack.result(0, "手机号码已经存在！");
            	}
            	if(StringUtils.equals(haverOther.getAccount(), data.getAccount())){
            		return ReturnMapByBack.result(0, "账户已经存在！");
            	}
            	if(StringUtils.equals(haverOther.getIdentityCardNo(), data.getIdentityCardNo())){
            		return ReturnMapByBack.result(0, "身份证已经存在！");
            	}
            	if(StringUtils.equals(haverOther.getEmail(), data.getEmail())){
            		return ReturnMapByBack.result(0, "邮箱已经存在！");
            	}
            	return ReturnMapByBack.result(0, "账户信息不唯一,无法保存！"); 
            }
            AccountEntry accountData = new AccountEntry();
            BeanUtils.copyProperties(data, accountData);
            accountData.setName(data.getFullName());
            accountData.setEnableFlag(data.getEnableFlag());
            accountData.setMobileNumber(data.getMobileNumber());
            accountData.setIdentityCardNo(data.getIdentityCardNo());
            accountData.setEmail(data.getEmail());
            accountData.setName(data.getFullName());
            accountData.setComment(data.getComment());
            Long accountId = accountService.save(accountData);
            if (accountId != null && accountId > 0) {
                UserEntry userEntry = userService.getByAccountId(accountId);
                UserEntry entry = new UserEntry();
                entry.setAccountId(accountId);
                entry.setFullName(data.getFullName());
                entry.setMobileNumber(data.getMobileNumber());
                entry.setBirthDate(data.getBirthDate());
                entry.setIdentityCardNo(data.getIdentityCardNo());
                entry.setGender(data.getGender());
                entry.setEmail(data.getEmail());
                entry.setCreateOn(accountId);
                entry.setModifyOn(accountId);
                if (userEntry != null && userEntry.getUserId() > 0) {
                    entry.setUserId(userEntry.getUserId());
                }
                int type = userService.savePage(entry);
                if(type == 1){
                	return ReturnMapByBack.result(1, "保存成功！");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ReturnMapByBack.result(0, "保存失败！");
    }
    /**
     * 个人设置上修改信息
     * @param data
     * @param result
     * @return
     */
    @PostMapping("updateMyInfo")
    @ResponseBody
    public Object updateMyInfo(@RequestBody @Validated AdminUserData data,BindingResult result){
    	try {
	    	if(result.hasErrors()){
	    		return ReturnMapByBack.result(0, result.getFieldError().getDefaultMessage());
	    	}
	    	//检查唯一性
	        AccountEntry haverOther = accountService.checkUser(data.getAccountId(), data.getAccount(), data.getMobileNumber(), data.getEmail(), data.getIdentityCardNo());
	        if(haverOther != null){
	        	if(StringUtils.equals(haverOther.getMobileNumber(), data.getMobileNumber())){
	        		return ReturnMapByBack.result(0, "手机号码已经存在！");
	        	}
	        	if(StringUtils.equals(haverOther.getAccount(), data.getAccount())){
	        		return ReturnMapByBack.result(0, "账户已经存在！");
	        	}
	        	if(StringUtils.equals(haverOther.getIdentityCardNo(), data.getIdentityCardNo())){
	        		return ReturnMapByBack.result(0, "身份证已经存在！");
	        	}
	        	if(StringUtils.equals(haverOther.getEmail(), data.getEmail())){
	        		return ReturnMapByBack.result(0, "邮箱已经存在！");
	        	}
	        	return ReturnMapByBack.result(0, "账户信息不唯一,无法保存！"); 
	        }
	        AccountEntry accountEntry = accountService.get(data.getAccountId());
	        accountEntry.setMobileNumber(data.getMobileNumber());
	        accountEntry.setIdentityCardNo(data.getIdentityCardNo());
	        accountEntry.setEmail(data.getEmail());
	        accountEntry.setName(data.getFullName());
	        accountEntry.setComment(data.getComment());
			accountService.save(accountEntry);
	        UserEntry userEntry = userService.getByAccountId(data.getAccountId());
	        userEntry.setBirthDate(data.getBirthDate());
	        userEntry.setFullName(data.getFullName());
	        userEntry.setGender(data.getGender());
	        userEntry.setEmail(data.getEmail());
	        userEntry.setIdentityCardNo(data.getIdentityCardNo());
	        userEntry.setMobileNumber(data.getMobileNumber());
	        int type = userService.savePage(userEntry);
	        return ReturnMapByBack.result(type, type==1?"保存成功":"保存失败！");
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }
    	return ReturnMapByBack.result(0, "保存失败！");
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object removeById(@PathVariable("id") Long id) {
        Integer num = accountService.removeById(id);
        if (num > 0) {
            return ReturnMapByBack.result(1, "删除成功！");
        }
        return ReturnMapByBack.result(0, "删除失败！");
    }

    /**
     * 获取用户信息分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param params 条件参数对象
     * @return
     */
    @RequestMapping(value = "page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageList(Integer page, Integer rows, AccountParam params) {
        List<AccountEntry> list = new ArrayList<>();
        try {
            int total = accountService.getCount(params);
            if (total > 0) {
                list = accountService.getPageList(page, rows, params);
                return ReturnMapByBack.result(1, "获取成功！", list, total);
            }
            return ReturnMapByBack.result(1, "获取成功！", list, total);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", list, 0);
        }
    }

    /**
     * 获取登录信息
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "login/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getLoginInfo() {
        try {
            LoginInfo info = loginContext.getInfoWeb();
            if (info != null && info.getUserId() != 0) {
                UserEntry userData = userService.get(info.getUserId());
                if (userData != null) {
                    info.setGender(userData.getGender());
                    info.setIdentityCardNo(userData.getIdentityCardNo());
                    info.setBirthDate(userData.getBirthDate());
                }
            }
            return ReturnMapByBack.result(1, "获取成功！", info);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "获取失败！", null);
        }
    }

    /**
     * 重置密码
     * @param accountId
     * @return
     */
    @RequestMapping(value = "reset/pwd/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public Object resetPwd(@PathVariable("accountId") Long accountId) {
        try {
            String pwd = accountService.resetPassword(accountId);
            return ReturnMapByBack.result(1, "重置成功！", pwd);
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "重置失败！", null);
        }
    }

    /**
     * 修改密码
     * @param passWord
     * @return
     */
    @RequestMapping(value = "change/pwd", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(@RequestBody @Validated Password passWord,BindingResult result) {
        try {
        	if(result.hasErrors()){
        		return ReturnMapByBack.result(0, result.getFieldError().getDefaultMessage());
        	}
            LoginInfo loginInfo = loginContext.getInfoWeb();
            if (loginInfo != null) {
                passWord.setAccountId(loginInfo.getAccountId());
                Integer num = accountService.changePassword(passWord);
                if (num != null && num > 0) {
                    return ReturnMapByBack.result(1, "修改成功！");
                }
            }
            return ReturnMapByBack.result(0, "修改失败！");
        } catch (Exception ex) {
            return ReturnMapByBack.result(0, "系统异常:"+ex.getMessage());
        }
    }

    /**
     * 手机号验重
     * @param mobileNumber 手机号码
     * @return true：存在 false：不存在
     */
    @RequestMapping(value = "exist/mobile/{mobileNumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean existMobile(@PathVariable("mobileNumber") String mobileNumber) {
        Integer num = accountService.checkMobile(mobileNumber);
        if (num > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据手机号获取用户accountId
     *
     * @param mobileNumber 手机号
     * @return accountId
     */
    @RequestMapping(value = "id/{number}", method = RequestMethod.GET)
    @ResponseBody
    public Object getAccountIdByNumber(@PathVariable("number") String mobileNumber) {
        AccountParam param = new AccountParam();
        param.setMobileNumber(mobileNumber);
        AccountEntry data = accountService.getFirst(param);
        if (data != null && data.getAccountId() != null) {
            return ReturnMapByBack.result(1, "获取成功", data.getAccountId());
        }
        return ReturnMapByBack.result(0, "暂无记录", 0L);
    }

    /**
     * 获取平台用户分页列表
     * @param page 页码
     * @param rows 每页记录数
     * @param params 条件参数对象
     * @return
     */
    @RequestMapping(value = "platform/page/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPlatformPageList(Integer page, Integer rows, UserParam params) {
        List<UserEntry> list = new ArrayList<>();
        try {
            int count = userService.getCount(params);
            if (count > 0) {
                list = userService.getPageList(page, rows, params);
            }
            return ReturnMapByBack.result(1, "获取成功", list, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "暂无记录", list,0);
    }

    /**
     * 获取平台用户列表
     * @param params 条件参数对象
     * @return
     */
    @RequestMapping(value = "platform/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getPlatformList(UserParam params) {
        List<UserEntry> list = new ArrayList<>();
        try {
            list = userService.getList( params);
            return ReturnMapByBack.result(1, "获取成功", list, list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnMapByBack.result(0, "暂无记录", list,0);
    }

    /**
     * 校验密码
     * @param loginData loginData
     * @return 返回给客户端的数据
     */
    @RequestMapping(value = "validatorPassword", method = RequestMethod.POST)
    @ResponseBody
    public Object queryUserByIdAndPassword(@RequestBody LoginData loginData) {
        LoginInfo loginInfo = loginContext.getInfoWeb();
        if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录");
        if (loginData.getPassWord() == null || loginData.getPassWord().length() == 0) return ReturnMapByBack.result(0, "密码不能为空");

        Long accountId = loginInfo.getAccountId();
        String password = loginData.getPassWord();
        try {
            Boolean flag = accountService.queryUserByIdAndPassword(accountId, password);
            if (flag) return ReturnMapByBack.result(1, "验证通过");
            else return ReturnMapByBack.result(0, "验证未通过");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员");
        }
    }
}
