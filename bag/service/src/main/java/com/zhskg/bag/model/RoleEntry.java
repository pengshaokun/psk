package com.zhskg.bag.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiangshiquan on 2017/10/11.
 */
public class RoleEntry implements Serializable {
    private Long roleId;
    
    private String roleName;

    private String roleCode;
    
    private Integer enableFlag = 1;
    
    private String comment;
    
    private Integer deleteFlag = 0;
    
    private Long createOn;
    
    private Long createTime;
    
    private Long modifyOn;
    
    private Long modifyTime;

    public List<MenuEntry> menus;

    public List<ResourceEntry> resources;

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
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
    
    public Long getCreateOn() { return createOn; }
    public void setCreateOn(Long createOn) { this.createOn = createOn; }
    
    public Long getCreateTime() { return createTime; }
    public void setCreateTime(Long createTime) { this.createTime = createTime; }
    
    public Long getModifyOn() { return modifyOn; }
    public void setModifyOn(Long modifyOn) { this.modifyOn = modifyOn; }
    
    public Long getModifyTime() { return modifyTime; }
    public void setModifyTime(Long modifyTime) { this.modifyTime = modifyTime; }

    public List<MenuEntry> getMenus() { return menus; }
    public void setMenus(List<MenuEntry> menus) { this.menus = menus; }

    public List<ResourceEntry> getResources() { return resources; }
    public void setResources(List<ResourceEntry> resources) { this.resources = resources; }
}