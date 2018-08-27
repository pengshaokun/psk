package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public class RoleParam implements Serializable {
    private String keywords;
    
    private String roleName;

    private String roleCode;
    
    private Integer enableFlag;
    
    private String comment;
    
    private Integer deleteFlag;
    
    
    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }
    
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getRoleCode() { return roleCode; }

    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }

    public Integer getEnableFlag() { return enableFlag; }
    public void setEnableFlag(Integer enableFlag) { this.enableFlag = enableFlag; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    public Integer getDeleteFlag() { return deleteFlag; }
    public void setDeleteFlag(Integer deleteFlag) { this.deleteFlag = deleteFlag; }
}