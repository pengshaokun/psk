package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * Created by lwb on 2018/4/10.
 */
public class DeviceFaultEntry implements Serializable
{
    private int id;

    private long userId;

    private String productName;

    private String clientId;

    private String reason;//故障原因

    private int status;//0是未处理，1是已处理

    private String tel;//申报人联系电话

    private long createTime;//申报时间

    private String remark;

    private String img;//上传的图片路径

    private String createName;//申报人姓名
    
    private Long disposeUserId;//用于储存处理人Id

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public List<String> getImgList(){
    	List<String> list = new ArrayList<String>();
    	if(StringUtils.isNotEmpty(img)){
    		list = Lists.newArrayList(StringUtils.split(img, ";"));
    	}
    	return list;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Long getDisposeUserId() {
		return disposeUserId;
	}

	public void setDisposeUserId(Long disposeUserId) {
		this.disposeUserId = disposeUserId;
	}
}
