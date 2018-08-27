package com.zhskg.bag.enums;


/**
 * 极光推送类型
 *
 * @author yanh
 * @date 2018/07/11
 */
public enum PushSendType implements BaseEnum {

    /**
     *所有用户
     */
	all("所有用户"),

    /**
     *ios端
     */
    ios("ios端"),

    /**
     * 安卓端
     */
    android("安卓端"),

    /**
     * 单人
     */
    solo("单人");


    private String desc;

    private PushSendType(String desc) {
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
