package com.zhskg.bag.model;

import java.io.Serializable;

/**
 * 常见问题
 */
public class ProblemEntry implements Serializable
{
    private int id;

    private String title;

    private String contxt;

    private int cxtType;

    private String createName;

    private long createTime;

    private long createOn;

    public long getCreateOn() {
        return createOn;
    }

    public void setCreateOn(long createOn) {
        this.createOn = createOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContxt() {
        return contxt;
    }

    public void setContxt(String contxt) {
        this.contxt = contxt;
    }

    public int getCxtType() {
        return cxtType;
    }

    public void setCxtType(int cxtType) {
        this.cxtType = cxtType;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
