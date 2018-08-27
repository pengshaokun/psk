package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 版本类型实体
 */
@Table
@Data
@Entity(name="tv_version_type")
public class VersionType {
    /**
     * 版本类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;
    
    /**
     * 版本类型value
     */
    @Column(name = "t_dictvalue")
    private Integer dictvalue;
    
    /**
     * 版本类型名称
     */
    @Column(name = "t_dictname")
    private String dictname;
    
    /**
     * 版本类型描述
     */
    @Column(name = "t_description")
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
