package com.kingja.qiang.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/5/3 16:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RegisteredFriend {
    private int id;
    private String nickname;
    private String avatar;
    private String mobile;
    /*状态 0未添加1已添加*/
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
