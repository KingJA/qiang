package com.kingja.qiang.page.visitor;

/**
 * Description:TODO
 * Create Time:2018/7/3 16:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Visitor {
    private String id;
    private String name;
    private String mobile;
    private String idcard;
    /**
     * 1默认 0非默认
     */
    private Integer isdefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}
