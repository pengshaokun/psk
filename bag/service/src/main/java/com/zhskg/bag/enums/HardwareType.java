package com.zhskg.bag.enums;

/**
 * @Auther:jean
 * @Date:2018/8/13
 * @Descripsion
 */
public enum HardwareType {

    GENERA("一般箱包", 1),
    STUDENT("学生箱包", 2);

    private String name;
    private int index;

    private HardwareType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 通过index获取name
    public static String getName(int index) {
        for (HardwareType c : HardwareType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name){
        for (HardwareType c : HardwareType.values()) {
            if(c.getName().equals(name)){
                return c.getIndex();
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}


