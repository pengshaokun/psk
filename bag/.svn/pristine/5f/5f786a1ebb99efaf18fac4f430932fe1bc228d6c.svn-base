package com.zhskg.bag.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.zhskg.bag.model.DeviceFaultEntry;
import com.zhskg.bag.model.UserEntry;
import com.zhskg.bag.netty.model.Commons;
import com.zhskg.bag.netty.model.ResultModel;
import com.zhskg.bag.param.MessageParam;
import com.zhskg.bag.service.AccountService;
import com.zhskg.bag.service.DeviceFaultService;
import com.zhskg.bag.service.MessageService;
import com.zhskg.bag.service.UserService;
import com.zhskg.bag.service.WebDeviceFaultService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.PushUtil;

@Service("webDeviceFaultService")
public class WebDeviceFaultServiceImpl implements WebDeviceFaultService{
	@Reference(version="1.0")
    private DeviceFaultService faultService;
	@Reference(version="1.0")
	private MessageService messageService;
	@Reference(version="1.0")
	private UserService userService;
	@Reference(version="1.0")
	private AccountService accountService;

    @Autowired
    private LoginContext loginContext;
    
    @Override
    public int update(DeviceFaultEntry model) {
    	boolean sendMsg = false;
    	if(model.getId()!=0 && model.getStatus() == 1){
    		DeviceFaultEntry db = faultService.get(model.getId());
    		if(db.getDisposeUserId() == null){
    			model.setDisposeUserId(loginContext.getInfoWeb().getUserId());
    			sendMsg = true;
    		}
    	}
    	int type = faultService.update(model);
    	if(sendMsg && type == 1){
    		UserEntry userEntry = userService.get(model.getUserId());
    		if(userEntry != null){
    			ResultModel res = new ResultModel(Commons.warning,"1",model);
//    			AccountEntry accountEntry = accountService.get(userEntry.getAccountId());
    			String title = "你的“"+model.getProductName()+"”箱包故障申报正被受理中！";
    			String msg= JSON.toJSONString(res);
    			String alias = userEntry.getMobileNumber();
    			//推送
    			PushUtil.pushByAlias(title, msg, alias);
    			//添加一个消息通知
    			MessageParam messageParam = new MessageParam();
//    			messageParam.setContent(msg);
    			messageParam.setContent(title);
    			messageParam.setCreateor(model.getDisposeUserId());
    			messageParam.setCreatetime(new Date());
    			messageParam.setPhone(alias);
    			messageParam.setTitle(title);
    			messageParam.setType(4);
				messageService.add(messageParam);
    		}
    	}
    	return type;
    }

}
