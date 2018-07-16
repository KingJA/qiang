package com.kingja.qiang.constant;

/**
 * Description:TODO
 * Create Time:2018/7/13 15:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Status {
    public interface SellStatus {
        //在售
        int SELLING = 1;
        //待售
        int UNSELLING = 0;
        //售完
        int SELLOUT = 2;
    }

    public interface PayType {
        //支付宝
        int ALIPAI = 2;
        //微信支付
        int WEIXINPAI = 1;
        //未选择
        int NOPAI = 0;
    }

    public interface ResultCode {
        //成功
        int SUCCESS = 0;
        //服务器错误
        int ERROR_SERVER = 1;
        //登录失效
        int ERROR_LOGIN_FAIL = -1;
    }
}
