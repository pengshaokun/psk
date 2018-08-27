package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * 常见问题
 */
public class ProblemParam implements Serializable
{
    private int id;

    private String title;

    private String contxt;

    private int cxtType;

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
}
