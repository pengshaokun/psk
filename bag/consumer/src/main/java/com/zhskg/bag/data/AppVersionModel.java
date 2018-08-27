package com.zhskg.bag.data;

import com.zhskg.bag.model.AppVersionEntry;

/**
 * Created by luochaojun on 2017/12/13.
 */
public class AppVersionModel extends AppVersionEntry {
    private Integer sendFlag;

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }
}
