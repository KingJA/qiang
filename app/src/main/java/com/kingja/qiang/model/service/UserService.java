package com.kingja.qiang.model.service;

import com.kingja.qiang.model.HttpResult;
import com.kingja.qiang.model.entiy.Deal;
import com.kingja.qiang.model.entiy.Discount;
import com.kingja.qiang.model.entiy.Friend;
import com.kingja.qiang.model.entiy.Login;
import com.kingja.qiang.model.entiy.Message;
import com.kingja.qiang.model.entiy.PersonalInfo;
import com.kingja.qiang.model.entiy.Wallet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface UserService {
    /*登录*/
    @FormUrlEncoded
    @POST("login")
    Observable<HttpResult<Login>> login(@Field("mobile") String mobile, @Field("password") String password);

    /*注册*/
    @FormUrlEncoded
    @POST("register")
    Observable<HttpResult<Object>> register(@Field("mobile") String mobile, @Field("password") String password,
                                            @Field("code") String code);

    /*忘记密码*/
    @FormUrlEncoded
    @POST("forgetPw")
    Observable<HttpResult<Object>> setNewPassword(@Field("mobile") String mobile, @Field("password") String password,
                                                  @Field("code") String code);

    /*修改密码*/
    @FormUrlEncoded
    @POST("me/modify_password")
    Observable<HttpResult<Object>> modifyPassword(@Field("password") String password, @Field("c_password") String
            c_password);

    /*我的消息*/
    @GET("me/message")
    Observable<HttpResult<List<Message>>> message();

    /*交易明细*/
    @GET("me/deal_list")
    Observable<HttpResult<List<Deal>>> getDealList();

    /*我的优惠券*/
    @GET("me/voucher")
    Observable<HttpResult<List<Discount>>> voucher();

    /*我的钱包*/
    @GET("me/wallet")
    Observable<HttpResult<Wallet>> wallet();

    /*发送验证码*/
    @FormUrlEncoded
    @POST("sms")
    Observable<HttpResult<Object>> sms(@Field("mobile") String mobile, @Field("type") String type);

    /*修改昵称*/
    @FormUrlEncoded
    @POST("me/modify_nickname")
    Observable<HttpResult<Object>> modifyNickname(@Field("nickname") String nickname);

    /*个人信息*/
    @GET("u/{id}")
    Observable<HttpResult<PersonalInfo>> getPersonalInfo(@Path("id") String id);

    /*我的好友*/
    @GET("me/friends")
    Observable<HttpResult<List<Friend>>> getMineFriends();

    /*退出登录*/
    @GET("me/getLogout")
    Observable<HttpResult<Object>> logout();

}
