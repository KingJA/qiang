package com.kingja.qiang.page.home;

import com.kingja.qiang.util.DateUtil;

/**
 * Description:TODO
 * Create Time:2018/7/9 10:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Ticket {

    private String id;
    private int discount;
    private int isoneyuan;
    private int status;
    private Double marketPrice;
    private Double buyPrice;
    private int totalCount;
    private int sellCount;
    private String areaText;
    private String headImg;
    private String statusText;
    private String ticketName;
    private String startTime;
    private String endTime;
    private String useDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getIsoneyuan() {
        return isoneyuan;
    }

    public void setIsoneyuan(Integer isoneyuan) {
        this.isoneyuan = isoneyuan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public String getAreaText() {
        return areaText;
    }

    public void setAreaText(String areaText) {
        this.areaText = areaText;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public boolean isSellOut() {
        return sellCount == totalCount;
    }

    public boolean isOverDue() {
        return DateUtil.isOverDue(getEndTime());
    }
}
