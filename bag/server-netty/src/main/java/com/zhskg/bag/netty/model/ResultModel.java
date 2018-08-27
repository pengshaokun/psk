package com.zhskg.bag.netty.model;

public class ResultModel {
    private String Code;//操作编码

    private String flag;//操作状态

    /** 请求返回数据对象(json || obj) */
    private Object data;



    public ResultModel(String code, String flag, Object data) {
        Code = code;
        this.flag = flag;
        this.data = data;
    }

    public String getCode() {
        return Code;
    }

    public ResultModel setCode(String code) {
        Code = code;
        return  this;
    }

    public String getFlag() {
        return flag;
    }

    public ResultModel setFlag(String flag) {
        this.flag = flag;
        return  this;
    }

    public Object getData() {
        return data;
    }

    public ResultModel setData(Object data) {
        this.data = data;
        return  this;
    }
}
