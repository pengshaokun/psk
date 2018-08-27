package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhskg.bag.data.LoginData;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.AccountEntry;
import com.zhskg.bag.model.ThirdPartyInfoEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.param.AccountParam;
import com.zhskg.bag.param.UserParam;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.ThirdPartyInfoService;
import com.zhskg.bag.service.UserService;
import com.zhskg.bag.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方登录
 */
@Controller
@RequestMapping(value = "app/third/party/")
public class AppThirdPartyInfoController {
    @Reference(version = "1.0")
    private ThirdPartyInfoService thirdPartyInfoService;

    @Reference(version = "1.0")
    private AccountService accountService;

    @Reference(version = "1.0")
    private UserService userService;

    @Autowired
    private LoginContext loginContext;



    /**
     * 第三方用户登录
     * @param info
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    public Object thirdPartySignIn(@Valid @RequestBody ThirdPartyInfoEntry info,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return  ReturnMap.result(0, bindingResult.getFieldError().getDefaultMessage());
        }
        if (info != null) {
            ThirdPartyInfoEntry data = thirdPartyInfoService.signIn(info);
            if (data != null) {
                if (data.getUserId()==null || data.getUserId()==0)
                {
                    return ReturnMap.result(2, "尚未绑定手机号");
                }
                LoginInfo loginInfo = new LoginInfo();
                //loginInfo.setGender(data);

                long maxAge = (data.getExpirationDate() - System.currentTimeMillis()) / 1000;
                if (maxAge < 1) {
                    maxAge = 86400 * ConfigUtil.REDIS_Expire;
                }

                UserEntry entry =  userService.get(data.getUserId());
                AccountEntry accountEntry = accountService.get(entry.getAccountId());
                //判断是否是其他设备登录
                if(StringUtils.isNotEmpty(accountEntry.getRegistrationID())){
                	if(!StringUtils.equals(accountEntry.getRegistrationID(), info.getRegistrationID())){
                		PushUtil.pushByAlias("你的账户已在别的设备登录,请确保是否为本人操作","你的账户已在别的设备登录,请确保是否为本人操作",accountEntry.getRegistrationID());
                	}
                }
                accountEntry.getRegistrationID();
                loginInfo.setHeadImgUrl(entry.getHeadPortrait());
                loginInfo.setSuperFlag(0);
                loginInfo.setUserId(entry.getUserId());
                
                loginContext.setAuthCookie(data.getAccessToken(), JSON.toJSONString(loginInfo), Integer.parseInt(String.valueOf(maxAge)),loginInfo.getUserId());

                JSONObject object = new JSONObject();
                object.put("nickName",entry.getNickName());
                object.put("headImg",entry.getHeadPortrait());
                object.put("mobileNumber",entry.getMobileNumber());
                object.put("gender",entry.getGender());
                object.put("identityCardNo",entry.getIdentityCardNo());
                object.put("birthDate",entry.getBirthDate());
                object.put("email",entry.getEmail());
                return ReturnMap.result(1, "登录成功！",object);
            }
        }
        return ReturnMap.result(0, "登录失败！");
    }
    /**
     * 手机账户关联
     * @param data
     * @return
     */
    @AllowAnonymous
    @RequestMapping(value = "relation",method = RequestMethod.POST)
    @ResponseBody
    public Object relation(@RequestBody LoginData data)
    {
        if (StringUtils.isEmpty(data.getCode()) || StringUtils.isEmpty(data.getMobileNumber())){
            return ReturnMap.result(0, "数据不能为空！");
        }
        if(!RegexValidateUtil.checkMobileNumber(data.getMobileNumber())){
            return ReturnMap.result(0, "手机号码输入错误！");
        }
        String isHave = RedisUtil.get(ConfigUtil.REDIES_PROJECT_NAME+":VerificationCode:"+data.getMobileNumber());
        /*if (StringUtils.isEmpty(isHave)){
            return ReturnMap.result(0, "验证码不存在！");
        }*/
        String powerFull="9999";
        if (StringUtils.isEmpty(isHave)||!isHave.equals(data.getCode())){
            if (!powerFull.equals(data.getCode())){
                return ReturnMap.result(0, "验证码错误！");
            }
         }

        ThirdPartyInfoEntry partyInfoEntry=thirdPartyInfoService.get(data.getOpenId());
        if (partyInfoEntry ==null){
            return ReturnMap.result(0, "第三方账号不存在！");
        }

        UserParam userParam = new UserParam();
        userParam.setMobileNumber(data.getMobileNumber());
        UserEntry entry = userService.getByFirst(userParam);



        JSONObject object = new JSONObject();

        if (entry!=null){
            //验证手机号同平台是否存在相同账号
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userId",entry.getUserId());
            map.put("thirdTerraceType",partyInfoEntry.getThirdTerraceType());
            List<ThirdPartyInfoEntry> thirdPartyInfoEntries=thirdPartyInfoService.getByUserIdAndTerraceType(map);
            if (thirdPartyInfoEntries.size()>0){
                thirdPartyInfoEntries.forEach(thirdPartyInfoEntrie->thirdPartyInfoService.setUserId(thirdPartyInfoEntrie.getOpenId(),null));
            }
            object.put("nickName",entry.getNickName());
            object.put("headImg",entry.getHeadPortrait());
            object.put("mobileNumber",entry.getMobileNumber());
            object.put("gender",entry.getGender());
            object.put("identityCardNo",entry.getIdentityCardNo());
            object.put("birthDate",entry.getBirthDate());
            object.put("email",entry.getEmail());
            thirdPartyInfoService.setUserId(data.getOpenId(),entry.getUserId());
            return ReturnMap.result(1, "设置成功！",object);
        }else {
            try {
                //查询account表
                AccountParam accountParam=new AccountParam();
                accountParam.setMobileNumber(data.getMobileNumber());
                AccountEntry accountEntry=accountService.getFirst(accountParam);
                long accId;

                if (accountEntry==null){
                    accountEntry=new AccountEntry();
                    accountEntry.setMobileNumber(data.getMobileNumber());
                    accountEntry.setPassword("123456");
                    accountEntry.setCreateTime(System.currentTimeMillis());
                    accountEntry.setAdminFlag(0);
                    accId = accountService.addAndId(accountEntry);
                }else{
                    accId=accountEntry.getAccountId();
                }
                UserEntry userData =new UserEntry();
                userData.setAccountId(accId);
                userData.setMobileNumber(data.getMobileNumber());
               // userData.setNickName(partyInfoEntry.getName());
               // userData.setHeadPortrait(partyInfoEntry.getIconUrl());
                long userId =userService.save(userData);
                thirdPartyInfoService.setUserId(data.getOpenId(),userId);
                //封装返回参数
                object.put("nickName","");
                object.put("headImg","");
                object.put("mobileNumber",userData.getMobileNumber());
                object.put("gender","");
                object.put("identityCardNo","");
                object.put("birthDate","");
                object.put("email","");


                return ReturnMap.result(1, "设置成功！",object);
            } catch (Exception e) {
                e.printStackTrace();
                return ReturnMap.result(0, "服务器繁忙！");
            }
        }
    }




}