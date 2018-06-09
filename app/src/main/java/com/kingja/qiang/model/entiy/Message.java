package com.kingja.qiang.model.entiy;

/**
 * Description:我的消息
 * Create Time:2018/4/18 15:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Message {
    private String title;
    private Integer status;
    private String content;
    private String created_at;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
