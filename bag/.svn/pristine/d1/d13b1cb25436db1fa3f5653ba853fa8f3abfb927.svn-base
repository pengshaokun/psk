package com.zhskg.bag.controller.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.data.LoginInfo;
import com.zhskg.bag.model.MessageTypeEntry;
import com.zhskg.bag.param.MessageTypeParam;
import com.zhskg.bag.service.MessageTypeService;
import com.zhskg.bag.util.LoginContext;
import com.zhskg.bag.util.ReturnMapByBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 消息类型 Controller
 */
@Controller
@RequestMapping("/web/messageType/")
public class WebmessageTypeController {
    @Autowired
    private LoginContext loginContext;

    @Reference(version = "1.0")
    private MessageTypeService messageTypeService;

    /**
     * 根据参数查询消息类别
     * @param page 第几页
     * @param rows 页大小
     * @param messageTypeParam 参数
     * @return 返回给客户端的数据
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Object queryMessageType(Integer page, Integer rows, MessageTypeParam messageTypeParam) {
        try {
            LoginInfo loginInfo = loginContext.getInfoWeb();
            // if (loginInfo == null) return ReturnMapByBack.result(0, "用户未登录,请先登录!");

            List<MessageTypeEntry> list = messageTypeService.query(page, rows, messageTypeParam); // 执行查询操作

            if (list.size() > 0) {
                // 查询列表数量
                return ReturnMapByBack.result(1, "获取消息类别成功!", list);
            } else {
                return ReturnMapByBack.result(1, "获取消息类别失败!", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMapByBack.result(0, "服务异常,请联系管理员!");
        }
    }
}
