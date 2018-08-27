package com.zhskg.bag.controller.web;

/**
 * 消息记录 controller
 */
import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.param.MessageRecordParam;
import com.zhskg.bag.service.MessageRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/messageRecord/")
public class WebMessageRecordController {
    @Reference(version = "1.0")
    private MessageRecordService messageRecordService;

    /**
     * 添加一条消息记录
     * @param messageRecordParam 形参
     * @return Object 返回给客户端的数据
     * @author huchuan
     */
   /* public Boolean addMessageRecord(MessageRecordParam messageRecordParam) {
        try {
            Boolean flag = messageRecordService.add(messageRecordParam);
            if (flag) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
}
