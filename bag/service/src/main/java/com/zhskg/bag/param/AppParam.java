package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/10/10.
 */
public class AppParam implements Serializable{
    private String keywords;
    private String appName;
    private String comment;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
