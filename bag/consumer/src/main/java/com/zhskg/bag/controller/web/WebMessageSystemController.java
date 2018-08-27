package com.zhskg.bag.controller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.MessageSystemEntry;
import com.zhskg.bag.param.MessageSystemParam;
import com.zhskg.bag.reqModel.MessagePushModel;
import com.zhskg.bag.service.MessageSystemService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.PushUtil;
import com.zhskg.bag.util.ReturnMapByBack;

/**
 * 系统通知 controller
 */
@RestController
@RequestMapping("web/messageSystem/")
public class WebMessageSystemController {
    @Autowired
    private LoginContext loginContext;

    @Reference(version = "1.0")
    private MessageSystemService messageSystemService;

    /**
     * 添加系统通知
     * @param messageSystemParam
     * @return 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping("add")
    public Object addMessageSystem(@RequestBody MessageSystemParam messageSystemParam) {
        try {
            Boolean flag = messageSystemService.add(messageSystemParam);
            if (flag) return ReturnMapByBack.result(1, "系统通知添加成功!");
            else return ReturnMapByBack.result(0, "系统通知添加失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据id删除系统通知
     * @param id id
     * @return 返回给客户端的数据
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Object deleteMessageSystem(@PathVariable int id) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

            Boolean flag = messageSystemService.delete(id);
            if (flag) return ReturnMapByBack.result(1, "系统通知删除成功!");
            else return ReturnMapByBack.result(0, "系统通知删除失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 修改系统通知
     * @param messageSystemParam 参数
     * @return 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object updateMessageSystem(@RequestBody MessageSystemParam messageSystemParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

            Boolean flag = messageSystemService.update(messageSystemParam);
            if (flag) return ReturnMapByBack.result(1, "系统通知修改成功!");
            else return ReturnMapByBack.result(0, "系统通知修改失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据参数查看系统通知详情
     * @param id id
     * @return 返回给客户端的数据
     * @autuor huchuan
     */
    @RequestMapping("queryById")
    public Object queryById(int id) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

            MessageSystemEntry messageSystemEntry = messageSystemService.queryById(id);
            if (messageSystemEntry != null) return ReturnMapByBack.result(1, "获取系统通知详情成功!", messageSystemEntry);
            else return ReturnMapByBack.result(0, "获取系统通知详情失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据参数查看系统通知详情 easyui方式
     * @param id id
     * @return 返回给客户端的数据
     * @autuor huchuan
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object queryById2(@PathVariable int id) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录!"); // 未登录

            MessageSystemEntry messageSystemEntry = messageSystemService.queryById(id);
            if (messageSystemEntry != null) return ReturnMapByBack.result(1, "获取系统通知详情成功!", messageSystemEntry);
            else return ReturnMapByBack.result(0, "获取系统通知详情失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据参数获取系统通知列表
     * @param page 第几页
     * @param rows 页大小
     * @param messageSystemParam 参数
     * @author huchuan
     */
    @RequestMapping("query")
    public Object queryMessageSystem(Integer page, Integer rows, MessageSystemParam messageSystemParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!"); // 未登录

            List<MessageSystemEntry> list = messageSystemService.query(page, rows, messageSystemParam);
            if (list.size() > 0) {
                int count = messageSystemService.queryAllMessageSystemCount(messageSystemParam);
                return ReturnMapByBack.result(1, "获取系统通知列表成功!", list, count);
            }
            else return ReturnMapByBack.result(1, "获取系统通知列表失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }

    @RequestMapping("send_jpush")
    public Object sendJpush(@Valid MessagePushModel messagePushModel,BindingResult result){
        if (result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            return ReturnMapByBack.result(0,fieldError.getDefaultMessage());
        }
        if (messagePushModel.getPushSendType()!=null){
            Map<String,String> map=new HashMap<String, String>();
            switch (messagePushModel.getPushSendType()){
                case ios:
                    PushUtil.pushByNotificationIos(messagePushModel.getMessage(),map);
                    break;
                case solo:
                    if (StringUtils.isEmpty(messagePushModel.getFlag())){
                        return ReturnMapByBack.result(0,"推送单独用户手机号不可为空");
                    }
                    PushUtil.pushByAlias(messagePushModel.getTitle(),messagePushModel.getMessage(),messagePushModel.getFlag());
                    break;
                case android:
                    PushUtil.pushByNotificationAndorid(messagePushModel.getTitle(),messagePushModel.getMessage(),map);
                    break;
                    default:
                        PushUtil.pushByNotificationIos(messagePushModel.getMessage(),map);
                        PushUtil.pushByNotificationAndorid(messagePushModel.getTitle(),messagePushModel.getMessage(),map);
                        break;
            }
        }else{
            return ReturnMapByBack.result(0,"发送类型不可为空");
        }
        return ReturnMapByBack.result(1,"推送成功");
    }


}
