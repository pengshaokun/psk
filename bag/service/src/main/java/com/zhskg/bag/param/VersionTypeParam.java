package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * 版本类型实体
 */
public class VersionTypeParam implements Serializable {
    /**
     * 版本类型ID
     */
    private Integer id;
    
    /**
     * 版本类型value
     */
    private Integer dictvalue;
    
    /**
     * 版本类型名称
     */
    private String dictname;
    
    /**
     * 版本类型描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictvalue() {
        return dictvalue;
    }

    public void setDictvalue(Integer dictvalue) {
        this.dictvalue = dictvalue;
    }

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
