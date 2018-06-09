package com.kingja.qiang.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/5/3 14:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Deal {

    private String money;
    private String type;
    private String content;
    private String created_at;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
