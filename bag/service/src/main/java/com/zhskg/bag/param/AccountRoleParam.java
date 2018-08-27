package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public class AccountRoleParam implements Serializable {
    private String keywords;
    
    private Long id;
    
    private Long accountId;
    
    private Long roleId;
    
    private Integer defaultFlag;
    
    
    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
    public Integer getDefaultFlag() { return defaultFlag; }
    public void setDefaultFlag(Integer defaultFlag) { this.defaultFlag = defaultFlag; }
    
}