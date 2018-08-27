package com.zhskg.bag.controller.app;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhskg.bag.util.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.LoginData;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.AccountEntry;
import com.zhskg.bag.model.FileTempEntry;
import com.zhskg.bag.model.Password;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.AccountParam;
import com.zhskg.bag.param.UserParam;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.FileService;
import com.zhskg.bag.service.UserService;

/**
 * Created by lwb on 2018/5/8.
 */
@Controller
@RequestMapping(value = "app/userinfo/")
public class AppUserController
{
    @Reference(version = "1.0")
    private AccountService accountService;

    @Reference(version = "1.0")
    private UserService userService;

    @Reference(version = "1.0")
    private FileService fileService;

    @Autowired
    private LoginContext loginContext;


    /**
     * 用户登录
     * @param info 56
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value="/signIn",method = RequestMethod.POST)
    @ResponseBody
    public Object signIn(@RequestBody LoginData info, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String userName= info.getUserName(),passWord = info.getPassWord();
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
                return ReturnMap.result(0,"数据不能为空!");
            }
//            if (userName.length()<6 ||passWord.length()<6){
//                return ReturnMap.result(0,"输入的数据不能小于6个字符大于20个字符！");
//            }
            if (userName.length()>20 ||passWord.length()>20){
                return ReturnMap.result(0,"输入的数据太长！");
            }
            if (StringUtils.isEmpty(info.getCode())){
                String vcode = RedisUtil.get("");
            }

            AccountEntry accountData = accountService.signIn(userName, passWord);
            if (accountData ==null) {
                return ReturnMap.result(0,"用户名或密码错误！");
            }
            if(accountData.getEnableFlag() != 1){
            	return ReturnMap.result(0,"系统拒绝您登录！");
            }
            //判断是否是其他设备登录
            if(StringUtils.isNotEmpty(accountData.getRegistrationID())){
            	if(!StringUtils.equals(info.getRegistrationID(),accountData.getRegistrationID())){
            		PushUtil.pushByAlias("你的账户已在别的设备登录,请确保是否为本人操作","你的账户已在别的设备登录,请确保是否为本人操作",accountData.getRegistrationID());
            	}
            }
            LoginInfo loginInfo = new LoginInfo();
            Long accountId = accountData.getAccountId();
            UserEntry userData=userService.getByAccountId(accountId);
            if (userData != null && userData.getUserId() !=null) {
                loginInfo.setAccount(accountData.getAccount());
                loginInfo.setAccountId(accountId);
                loginInfo.setAdminFlag(accountData.getAdminFlag());
                loginInfo.setEmail(accountData.getEmail());
                loginInfo.setNextChangeFlag(accountData.getNextChangeFlag());
                loginInfo.setMobileNumber(accountData.getMobileNumber());
                loginInfo.setUserId(userData.getUserId());
                loginInfo.setFullName(userData.getFullName());
            }else {
                if (accountData.getAdminFlag() != 1 && accountData.getSuperFlag() != 1) {
                    userData = new UserEntry();
                    userData.setAccountId(accountId);
                    userData.setMobileNumber(accountData.getMobileNumber());
                    userData.setCreateOn(accountId);
                    userData.setCreateTime(System.currentTimeMillis());
                    userData.setDeleteFlag(0);
                    Long userId = userService.addAndId(userData);
                    if (userId != null && userId > 0) {
                        loginInfo.setFullName(accountData.getName());
                        loginInfo.setUserId(userId);
                    }
                }
            }
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            loginContext.setAuthCookie(token, JSON.toJSONString(loginInfo), 0,loginInfo.getUserId());
            AccountParam ap = new AccountParam();
            ap.setRegistrationID(info.getRegistrationID());
            accountService.update(accountData, ap);
            JSONObject object = new JSONObject();
            object.put("nickName",userData.getNickName());
            object.put("headImg",userData.getHeadPortrait());
            object.put("mobileNumber",userData.getMobileNumber());
            object.put("gender",userData.getGender());
            object.put("identityCardNo",userData.getIdentityCardNo());
            object.put("birthDate",userData.getBirthDate());
            object.put("email",userData.getEmail());
            return ReturnMap.result(1,"success",object);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0,"服务繁忙！");
        }
    }

    /**
     * 用户注册
     * @param ldata 用户输入的手机号或者邮箱
     * @return ty
     * @author huchuan 改
     */
    @AllowAnonymous
    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody LoginData ldata) //AccountData data
    {
        long accId = 0;
        // 手机号验证
        if (!VerifyUtil.isPhone(ldata.getMobileNumber())) {
            return ReturnMap.result(0, "手机号非法！");
        }
        try
        {
            // 数据非法验证
            if (StringUtils.isEmpty(ldata.getCode()) || StringUtils.isEmpty(ldata.getMobileNumber()) || StringUtils.isEmpty(ldata.getPassWord())){
                return ReturnMap.result(0, "数据不能为空！");
            }
            if (ldata.getPassWord() != null){
                Pattern usernamePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
                if(!usernamePattern.matcher(ldata.getPassWord()).matches()){
                	return ReturnMapByBack.result(0, "密码格式不对,6-18数字加字母!");
                }
            }
            String isHave = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+ldata.getMobileNumber());
            if (!ldata.getCode().equals("9999")) {
                if (StringUtils.isEmpty(isHave)) {
                    return ReturnMap.result(0, "验证码不存在！");
                }
                if (!isHave.equals(ldata.getCode())) {
                    return ReturnMap.result(0, "验证码错误！");
                }
            }

            // 手机号验证
            if (!VerifyUtil.isPhone(ldata.getMobileNumber())) {
                return ReturnMap.result(0, "手机号非法！");
            }

            // 密码是否包含空格
            if (VerifyUtil.strIsHaveSpace(ldata.getPassWord())) {
                return ReturnMap.result(0, "密码不能包含空格！");
            }


            AccountEntry data = new AccountEntry();
            data.setMobileNumber(ldata.getMobileNumber());
            data.setPassword(ldata.getPassWord());
            data.setCreateTime(System.currentTimeMillis());
            data.setAdminFlag(0);

            int result =accountService.checkMobile(data.getMobileNumber()); // 检测手机号是否存在
            if(result > 0) {
                return ReturnMap.result(0, "该手机号已经存在！");
            }
            accId = accountService.addAndId(data);
            if (accId > 0) {
                try {
                    UserEntry userData =new UserEntry();
                    userData.setAccountId(accId);
                    userData.setCreateOn(System.currentTimeMillis());
                    userData.setMobileNumber(data.getMobileNumber());
                    userData.setGender(1);
                    long userId =userService.addAndId(userData);
                    return ReturnMap.result(1, "注册成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                    accountService.realRemoveById(accId);
                    return ReturnMap.result(0, "程序异常！");
                }
            }else {
                return ReturnMap.result(0, "注册失败！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0, "服务器繁忙!");
        }

    }

    /**
     * 手机号码验证，如果手机号存在则不允许注册：0是不可以，1是可以注册
     * @param data
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value="/checkMobile",method = RequestMethod.POST)
    @ResponseBody
    public Object checkMobile(@RequestBody LoginData data)
    {
        try
        {
            if (StringUtils.isEmpty(data.getMobileNumber())) {
                return ReturnMap.result(0, "手机号不能为空！");
            }
            if (!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())) {
                return ReturnMap.result(0, "请正确输入手机号！");
            }
            int result = accountService.checkMobile(data.getMobileNumber());
            if (result>0) {
                return ReturnMap.result(0, "该手机号已经存在，请重试其他手机号！");
            }else {
                return ReturnMap.result(1, "可以注册");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "程序异常！");
        }
    }

    /**
     * 发送手机验证码用以更换手机号码
     * 请求参数{"mobileNumber":"13619625274"}
     * @return json 字符串
     * @author huchuan
     */
    @AllowAnonymous
    @RequestMapping(value="/sendCodeUsedUpdateMobileNumber")
    @ResponseBody
    public Object sendCodeUsedUpdateMobileNumber(@RequestBody LoginData data)
    {
        // 参数校验
        if (StringUtils.isEmpty(data.getMobileNumber())) {
            return ReturnMap.result(0, "手机号不能空！");
        }
        if (!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())) {
            return ReturnMap.result(0, "输入的手机号码格式不对！");
        }

        // 重名验证
        UserParam userParam = new UserParam();
        userParam.setMobileNumber(data.getMobileNumber());
        Boolean existFlag = userService.mobileNumberisExist(userParam);
        if (existFlag)  return ReturnMap.result(0, "手机号码已存在!");

        String isHave = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
        if (StringUtils.isNotEmpty(isHave)){
            return ReturnMap.result(0,"请不要频繁发送！");
        }

        try {
                Boolean flag = send(data.getMobileNumber()); // 发送验证码
                if (flag) { // 发送成功
                    return ReturnMap.result(1,"发送成功！");
                } else { // 发送失败
                    return ReturnMap.result(0,"发送失败！");
                }
        } catch (Exception e) {
        	e.printStackTrace();
            return ReturnMap.result(0,"发送失败！");
        }
    }

    /**
     * 发送手机验证码
     * 请求参数{"mobileNumber":"13619625274"}
     * @return json 字符串
     */
    @AllowAnonymous
    @RequestMapping(value="/sendCode",method = RequestMethod.POST)
    @ResponseBody
    public Object sendCode(@RequestBody LoginData data)
    {
        if (StringUtils.isEmpty(data.getMobileNumber())) {
            return ReturnMap.result(0, "手机号不能空！");
        }
        if (!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())) {
            return ReturnMap.result(0, "输入的手机号码不对！");
        }
        String isHave = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
        if (StringUtils.isNotEmpty(isHave)){
            Long varificationCodeTime=RedisUtil.ddlKey(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
            if(varificationCodeTime>(4*60*1000)){
                return ReturnMap.result(0,"请不要频繁发送！");
            }
        }

        Boolean flag = send(data.getMobileNumber());
        if (flag) return ReturnMap.result(1,"发送成功！");
        else return ReturnMap.result(0,"发送失败！");

        /*String code = RandomStringUtils.randomNumeric(6);
        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("appKey", ConfigUtil.APP_KEY);
        mapParam.put("appSecret", ConfigUtil.APP_SECRET);
        mapParam.put("templateId", ConfigUtil.REGISTER_TEMPLATE_ID);
        mapParam.put("freeSign", ConfigUtil.REGISTER_SIGN);
        mapParam.put("mobile", data.getMobileNumber());
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("handleType", "身份");
        map.put("duration", Integer.toString(ConfigUtil.REGISTER_DURATION));
        mapParam.put("params", map);

        boolean flag = SmsPusher.doSend(ConfigUtil.APP_KEY,ConfigUtil.APP_SECRET,
                ConfigUtil.REGISTER_TEMPLATE_ID,JSON.toJSONString(map),ConfigUtil.REGISTER_SIGN,data.getMobileNumber());
        if (flag){
            System.out.println("验证码:"+code+"   "+new Date());
            RedisUtil.setex(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber(),ConfigUtil.REGISTER_DURATION*60,code);
            return ReturnMap.result(1,"发送成功！");
        }
        return ReturnMap.result(0,"发送失败！");*/
    }

    /**
     * 发送验证码
     * @param mobileNumber String 手机号
     * @return Boolean 是否发送成功
     * @author huchuan
     */
    private Boolean send(String mobileNumber) {
        String code = RandomStringUtils.randomNumeric(6);
        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("appKey", ConfigUtil.APP_KEY);
        mapParam.put("appSecret", ConfigUtil.APP_SECRET);
        mapParam.put("templateId", ConfigUtil.REGISTER_TEMPLATE_ID);
        mapParam.put("freeSign", ConfigUtil.REGISTER_SIGN);
        mapParam.put("mobile", mobileNumber);
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("handleType", "身份");
        map.put("duration", Integer.toString(ConfigUtil.REGISTER_DURATION));
        mapParam.put("params", map);

        boolean flag = SmsPusher.doSend(ConfigUtil.APP_KEY,ConfigUtil.APP_SECRET,
                ConfigUtil.REGISTER_TEMPLATE_ID,JSON.toJSONString(map),ConfigUtil.REGISTER_SIGN,mobileNumber);
        if (flag){
            System.out.println("验证码:"+code+"   "+new Date());
            RedisUtil.setex(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+mobileNumber,ConfigUtil.REGISTER_DURATION*60,code);
            return true;
        }
        return false;
    }

    /**
     * 忘记密码用get
     *
     * @param data
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "/forgetPassWord", method = RequestMethod.POST)
    @ResponseBody
    public Object forgetPassWord(@RequestBody LoginData data) {
        try {
            if (StringUtils.isEmpty(data.getCode())){
                return ReturnMap.result(0, "验证码不能为空！");
            }
            if (StringUtils.isEmpty(data.getPassWord())){
                return ReturnMap.result(0, "密码不能为空！");
            }
            if (data.getPassWord() != null){
                Pattern usernamePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
                if(!usernamePattern.matcher(data.getPassWord()).matches()){
                	return ReturnMapByBack.result(0, "密码格式不对,6-18数字加字母!");
                }
            }
            String isHave = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
           if(!data.getCode().equals("9999")){
               if (StringUtils.isEmpty(isHave)){
                   return ReturnMap.result(0, "验证码不存在或已过期！");
               }
               if (!isHave.equals(data.getCode())){
                   return ReturnMap.result(0, "验证码错误！");
               }
           }
            AccountParam param = new AccountParam();
            param.setMobileNumber(data.getMobileNumber());
            AccountEntry accountData = accountService.getFirst(param);
            if (accountData == null) {
                return ReturnMap.result(0, "没有此账号！！");
            }
            Password pword = new Password();
            pword.setAccountId(accountData.getAccountId());
            pword.setOldPassword(accountData.getPassword());
            pword.setNewPassword(data.getPassWord());
            int result = accountService.changePassword(pword);
            if (result > 0) {
                return ReturnMap.result(1, "修改成功！！");
            } else {
                return ReturnMap.result(0, "修改失败！！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0, "程序错误！");
        }
    }

    /**
     * 核对发送验的证码
     * {"code":"655000","mobileNumber":"13619625274"}
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value="/checkCode",method = RequestMethod.POST)
    @ResponseBody
    public Object checkCode(@RequestBody LoginData data)
    {
        if (StringUtils.isEmpty(data.getCode()) || StringUtils.isEmpty(data.getMobileNumber())) {
            return ReturnMap.result(0,"数据不能为空！");
        }
        String code = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
        if (code!=null && code.equals(data.getCode())) {
            return ReturnMap.result(1,"验证成功！");
        }
        return ReturnMap.result(0,"验证失败！");
    }


    /**
     * 验证验证码
     * @param mobileNumber 手机号码
     * @param code 验证码
     * @return boolean 验证码是否正确
     */
    public Boolean check(String mobileNumber, String code)
    {
        String realCode = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+mobileNumber);
        if (code.equals(realCode)) return true;
        else return false;
    }



    /**
     * 修改密码 {"mobileNumber":"13619625274","passWord":"123456"}
     * @return
     */
    @RequestMapping(value="/updatePasword",method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(@RequestBody LoginData data) {
        try {
            LoginInfo loginInfo = loginContext.getInfoApp();
            if (StringUtils.isEmpty(data.getCode())){
                return ReturnMap.result(0, "验证码不能为空！");
            }
            if (data.getPassWord() != null){
                Pattern usernamePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
                if(!usernamePattern.matcher(data.getPassWord()).matches()){
                	return ReturnMapByBack.result(0, "密码格式不对,6-18数字加字母!");
                }
            }
            String code = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+loginInfo.getMobileNumber());
            if(!data.getCode().equals("9999")){
                if (StringUtils.isEmpty(code)){
                    return ReturnMap.result(0, "验证码不存在或已过期！");
                }
                if (!code.equals(data.getCode())){
                    return ReturnMap.result(0, "验证码错误！");
                }
            }
           // AccountData accountData = accountService.signIn(loginInfo.getMobileNumber(), data.getOldPassword());
            AccountParam param = new AccountParam();
            param.setMobileNumber(loginInfo.getMobileNumber());
           AccountEntry accountData = accountService.getFirst(param);
           if(accountData==null) return ReturnMap.result(2,"数据异常");

            if (data.getPassWord().equals(accountData.getPassword())) {
                return ReturnMap.result(0, "修改失败，新密码与原密码相同！");
            } else {
            	if (accountData.getPassword() != null){
                    Pattern usernamePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
                    if(!usernamePattern.matcher(accountData.getPassword()).matches()){
                    	return ReturnMapByBack.result(0, "密码格式不对,6-18数字加字母!");
                    }
                }
                Password pwd = new Password();
                pwd.setAccountId(accountData.getAccountId());
                pwd.setOldPassword(accountData.getPassword());
                pwd.setNewPassword(data.getPassWord());
                int result = accountService.changePassword(pwd);
                if (result > 0) {
                    return ReturnMap.result(1, "修改成功！");
                }
            }
            return ReturnMap.result(0, "修改失败！！");
        } catch (Exception e) {
            return ReturnMap.result(0, "程序错误！");
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value="/loginOut",method = RequestMethod.GET)
    @ResponseBody
    public Object loginOut()
    {
        try {
            loginContext.signOut();
            return ReturnMap.result(1,"退出成功！");
        } catch (Exception e) {
            return ReturnMap.result(-1,"程序错误！");
        }
    }

    /**
     * 设置登录用户基本信息
     * @param data
     * @return
     */
    @RequestMapping(value="/setBasucInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object setBasucInfo(@RequestBody UserEntry data)
    {
        try{
            LoginInfo loginInfo=loginContext.getInfoApp();
            if (data.getEmail()!=null){
                boolean flag = RegexValidateUtil.checkEmail(data.getEmail());
                if (!flag){
                    return ReturnMap.result(0, "请输入正确的邮箱！");
                }
                AccountEntry accountEntry = accountService.get(loginInfo.getAccountId());
                accountEntry.setEmail(data.getEmail());
                accountService.save(accountEntry);
            }
            data.setUserId(loginInfo.getUserId());
            long result=userService.save(data);
            if (result>0) {
                return ReturnMap.result(1, "保存成功!");
            }
            return ReturnMap.result(0, "保存失败");
        }catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0, "保存失败");
        }
    }

    /**
     * 获取登录用户基本信息
     * @return
     */
    @RequestMapping(value="/getBasucInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getBasucInfo()
    {
        try{
            LoginInfo loginInfo=loginContext.getInfoApp();
            UserEntry userData=userService.get(loginInfo.getUserId());
            if (userData !=null){
                userData.setUserId(0l);
                userData.setAccountId(0l);
            }
            return ReturnMap.result(1,"success",userData);
        }catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(0, "获取失败");
        }
    }

    /**
     * 设置用户头像
     * @param file
     * @return
     */
    @RequestMapping(value = "setHeadImage", method = RequestMethod.POST)
    @ResponseBody
    public Object setHead(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file==null){
                return  ReturnMap.result(0,"头像为空");
            }
            LoginInfo info = loginContext.getInfoApp();
            UserEntry data = new UserEntry();
            long userId = info.getUserId();
            data.setUserId(userId);
            InputStream intputStream = file.getInputStream();
            String inFileName = file.getOriginalFilename();
            //获取文件类型
            String extName = inFileName.substring(inFileName.lastIndexOf(".")).substring(1);
            String path = FastDFSUtil.upload_file(intputStream, extName);
            if (StringUtils.isEmpty(path)) {
                return ReturnMap.result(0, "上传失败！");
            }
            FileTempEntry fileTempData = new FileTempEntry();
            fileTempData.setOverdueFlag(0);
            fileTempData.setFilePath(path);
            fileTempData.setCreateTime(System.currentTimeMillis());
            fileTempData.setFileType(extName);
            int num = fileService.add(fileTempData);
            fileService.updateOverdueFlag(path, 0);
            data.setHeadPortrait(path);
            long reuslt = userService.save(data);
            if (reuslt > 0) {
                return ReturnMap.result(1, "success",path);
            } else {
                FastDFSUtil.dealFilePath(path);
            }
            return ReturnMap.result(0, "保存失败");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ReturnMap.result(-1, "程序异常");
        }
    }

    /**
     * 判断当前用户是否已登陆
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "isLogin", method = RequestMethod.GET)
    @ResponseBody
    public Object isLogin() {
        LoginInfo loginInfo = loginContext.getInfoApp();
        if (loginInfo != null && loginInfo.getUserId() > 0) {
            /**
             * 这里可以加点逻辑，比如登陆就延长登陆时间
             */
            return ReturnMap.result(1, "已登录");
        }
        return ReturnMap.result(0, "未登录");
    }

    /**
     * 修改绑定的手机号码
     * @param loginData loginData
     * @return 返回给客户端的数据
     */
    @RequestMapping(value = "/updateMobileNumber", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePhone(@RequestBody LoginData loginData) {
        LoginInfo loginInfo=loginContext.getInfoApp(); // 获取登录信息
        if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录");
        if (loginData == null) return ReturnMap.result(0, "参数为空");

        Long userId = loginInfo.getUserId(); // 获取id

        // 参数校验
        String mobileNumber = loginData.getMobileNumber(); // 获取手机号
        String code = loginData.getCode(); // 获取验证码

        if (StringUtils.isEmpty(mobileNumber)) return ReturnMap.result(0, "手机号不能空！");
        if (StringUtils.isEmpty(code)) return ReturnMap.result(0, "验证码不能为空！");
        if (!RegexValidateUtil.checkMobileNumber(mobileNumber)) return ReturnMap.result(0, "输入的手机号码格式不对！");
        if (mobileNumber.equals(loginInfo.getMobileNumber())){
            return ReturnMap.result(0, "不可重复绑定！");
        }


        // 重名验证
        UserParam userParam = new UserParam();
        userParam.setMobileNumber(mobileNumber);
        Boolean existFlag = userService.mobileNumberisExist(userParam);
        if (existFlag)  return ReturnMap.result(0, "手机号码已存在!");

        // 验证验证码
        Boolean checkFlag = check(mobileNumber, code);
        if (checkFlag) {
            Long accountId = userService.queryAccountId(userId); // accountid
            if (accountId != null) {
                // 调方法执行修改
                Boolean flag = userService.updatePhoneById(userId, mobileNumber); // user
                Boolean flag2 = accountService.updatePhoneById(accountId, mobileNumber); // account

                if (flag && flag2) return ReturnMap.result(1, "手机号码修改成功!");
                else return ReturnMap.result(0, "手机号码修改失败!");
            } else {
                return ReturnMap.result(0, "手机号码修改失败!");
            }
        } else {
            return ReturnMap.result(0, "验证失败!");
        }
    }
}
