package com.zhskg.bag.reqModel;

import com.zhskg.bag.enums.PushSendType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *推送消息请求模型
 * @author yanh
 * @date 2018/07/11
 */
@Data
public class MessagePushModel {

    /**
     * 标题
     */
    @NotEmpty(message = "标题不可为空")
    private  String title;

    /**
     * 消息
     */
    @NotEmpty(message = "消息不可为空")
    private  String message;

    /**
     * 推送平台
     */
    private PushSendType pushSendType;

    /**
     * 推送标识，目前为手机号
     */
    private String flag;
}
