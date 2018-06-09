package com.kingja.qiang.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/4/18 17:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Discount {

    private Integer id;
    private Integer status;
    private Integer price;
    private String content;
    private String title;
    private String end_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
