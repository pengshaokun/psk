package com.zhskg.bag.model;

/**
 * Created by luochaojun on 2018/2/27.
 */
public enum ThirdPartyCategoryEnum {
    QQ(0, "QQ"),
    WeChat(1, "微信");
    private int value;
    private String name;

    ThirdPartyCategoryEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
