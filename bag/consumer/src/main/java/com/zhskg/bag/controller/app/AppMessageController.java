package com.zhskg.bag.controller.app;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.intercepter.AllowAnonymous;
import com.zhskg.bag.model.MessageEntry;
import com.zhskg.bag.param.MessageParam;
import com.zhskg.bag.param.MessageRecordParam;
import com.zhskg.bag.service.MessageRecordService;
import com.zhskg.bag.service.MessageService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息 Controller
 */
@Controller
@RequestMapping("app/message/")
public class AppMessageController {

    @Autowired
    private LoginContext loginContext;

    @Reference(version = "1.0")
    private MessageService messageService;

    @Reference(version = "1.0")
    private MessageRecordService messageRecordService;

    /**
     * 添加一条消息
     * @param messageParam 页面参数
     * @return 响应客户端的数据
     * @ahtuor huchuan
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addMessage(@RequestBody MessageParam messageParam) {
        try {

            /* 登录验证 */
            LoginInfo loginInfo = loginContext.getInfoApp();

            /** 必填字段校验 */

            int flag = messageService.add(messageParam);
            if (flag > 0) return ReturnMap.result(1, "消息添加成功!");
            else return ReturnMap.result(0, "消息添加失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据id删除一条消息
     * @param id id
     * @return 响应客户端的数据
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteMessage(@PathVariable int id) {
        try {
            /* 登录验证 */
            LoginInfo loginInfo = loginContext.getInfoApp();
            // if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录!");

            int flag = messageService.delete(id);
            if (flag > 0) return ReturnMap.result(1, "消息删除成功!");
            else return ReturnMap.result(0, "消息删除失败!");

        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据id修改消息
     * @param messageParam 消息
     * @return 响应客户端的数据
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateMessage(@RequestBody MessageParam messageParam) {
        try {
            /* 登录验证 */
            LoginInfo loginInfo = loginContext.getInfoApp();
            // if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录!");

            int flag = messageService.update(messageParam);

            if (flag > 0) return ReturnMap.result(1, "消息修改成功!");
            else return ReturnMap.result(0, "消息修改失败!");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.result(0, "服务异常,请联系管理员!");
        }
    }
    
    /**
     * 根据id查看消息详情
     * @param id id
     * @return Object 返回给客户端的数据
     * @author huchuan
     */
    @RequestMapping("queryById/{id}")
    @ResponseBody
    public Object queryById(@PathVariable int id) {
        try {
            LoginInfo loginInfo = loginContext.getInfoApp();
            // if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录!");
            MessageEntry messageEntry = messageService.queryById(id); // 执行查询
            if (messageEntry != null) {
                return ReturnMap.result(1, "获取消息详情成功!", messageEntry);
            } else {
                return ReturnMap.result(0, "获取消息详情失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 根据参数查询消息列表
     * @param page 第几页
     * @param size 页大小
     * @param messageParam 查询参数
     * @return 返回客户端的数据
     * @author huchuan
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    @AllowAnonymous
    public Object query(Integer page, Integer size, MessageParam messageParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoApp();
            // if (loginInfo == null) return ReturnMap.result(0, "用户未登录,请先登录!");
            List<MessageEntry> list =  messageService.query(page, size, messageParam);
            // 查询列表数量
            int count = messageService.queryAllMessageCount(messageParam);
            return ReturnMap.result(1, "获取消息列表成功!", list, count);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.result(0, "服务异常,请联系管理员!");
        }
    }

    /**
     * 添加一条消息记录
     * @param messageRecordParam 形参
     * @return Boolean
     * @author huchuan
     */
    private Boolean addMessageRecord(MessageRecordParam messageRecordParam) {
        try {
            Boolean flag = messageRecordService.add(messageRecordParam);
            if (flag) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
