package com.kingja.qiang.event;

import com.kingja.qiang.page.visitor.Visitor;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/7/3 16:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddVisitorEvent extends Visitor{

    public AddVisitorEvent(Visitor visitor) {
        this.id=visitor.getId();
        this.name=visitor.getName();
        this.mobile=visitor.getMobile();
        this.isdefault=visitor.getIsdefault();
        this.isSelected=true;
    }
}
