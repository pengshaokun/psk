package com.zhskg.bag.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**文件存储表*/
@Table
@Data
@Entity(name="tp_file_temp")
public class FileTemp implements Serializable {

    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long fileId;

    /**文件路径*/
    @Column(name = "file_path")
    private String filePath;

    /**文件类型*/
    @Column(name = "file_type")
    private String fileType;

    /**过期标记*/
    @Column(name = "overdue_flag")
    private Integer overdueFlag;

    /**有效时长*/
    @Column(name = "term_validity")
    private Long termValidity;

    /**创建时间*/
    @Column(name = "create_time")
    private Long createTime;

    /**创建人*/
    @Column(name = "create_on")
    private Long createOn;


}