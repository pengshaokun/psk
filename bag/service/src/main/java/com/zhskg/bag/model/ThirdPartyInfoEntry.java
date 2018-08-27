package com.zhskg.bag.model;

import com.zhskg.bag.enums.ThirdTerraceType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 第三方登录信息 请求实体
 *
 * @author luochaojun
 * @date 2018/2/27
 */
@Data
public class ThirdPartyInfoEntry implements Serializable {
    /**
     * 微信或支付宝唯一号
     */
    @NotEmpty(message = "openId不能为空")
    private String openId;
    /**
     * 联合id
     */
    private String unionId;
    /**
     * accessToken
     */
    @NotEmpty(message = "accessToken不能为空")
    private String accessToken;
    @NotNull
    private Long expirationDate;//expirationDate
    /**
     * 昵称或名称
     */
    @NotEmpty(message = "昵称或名称不能为空")
    private String name;
    private String city;//省
    private String prvinice;//市
    private String country;//县
    private String gender;//性别
    private String iconUrl;//头像
    private Long userId;
    private String createName;
    private Long createTime;
    private Integer empowerType = 0;
    private Long createOn;
    private Integer deleteFlag = 0;
    @NotNull(message = "登录平台不能为空")
    private ThirdTerraceType thirdTerraceType;
    @NotEmpty(message = "设备唯一识别码不能为空")
    private String registrationID;

}
