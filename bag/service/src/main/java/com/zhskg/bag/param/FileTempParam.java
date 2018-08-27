package com.zhskg.bag.param;

import java.io.Serializable;

/**
 * Created by luochaojun on 2017/11/4.
 */
public class FileTempParam implements Serializable {
    private String filePath;
    private Integer overdueFlag;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getOverdueFlag() {
        return overdueFlag;
    }

    public void setOverdueFlag(Integer overdueFlag) {
        this.overdueFlag = overdueFlag;
    }

}
