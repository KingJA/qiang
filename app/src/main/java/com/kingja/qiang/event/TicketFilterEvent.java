package com.kingja.qiang.event;

/**
 * Description:TODO
 * Create Time:2018/7/22 19:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TicketFilterEvent {
    private String areaId;
    private String productTypeId;
    private String useDates;
    private String discountRate;

    public TicketFilterEvent(String areaId, String productTypeId, String useDates, String discountRate) {
        this.areaId = areaId;
        this.productTypeId = productTypeId;
        this.useDates = useDates;
        this.discountRate = discountRate;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getUseDates() {
        return useDates;
    }

    public void setUseDates(String useDates) {
        this.useDates = useDates;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }
}
