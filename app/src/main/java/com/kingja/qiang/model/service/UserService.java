package com.kingja.qiang.model.service;

import com.kingja.qiang.model.HttpResult;
import com.kingja.qiang.model.entiy.Deal;
import com.kingja.qiang.model.entiy.Discount;
import com.kingja.qiang.model.entiy.Friend;
import com.kingja.qiang.model.entiy.Login;
import com.kingja.qiang.model.entiy.Message;
import com.kingja.qiang.model.entiy.PersonalInfo;
import com.kingja.qiang.model.entiy.Wallet;
import com.kingja.qiang.page.order.Order;
import com.kingja.qiang.page.order.orderdetail.OrderDetail;
import com.kingja.qiang.page.visitor.list.Visitor;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface UserService {
    /*登录OK*/
    @FormUrlEncoded
    @POST("/app/user/login")
    Observable<HttpResult<Login>> login(@Field("mobile") String mobile, @Field("password") String password, @Field
            ("deviceId") String deviceId, @Field("deviceName") String deviceName, @Field("osName") String osName);

    /*发送验证码OK*/
    @FormUrlEncoded
    @POST("/app/user/smsmessage")
    Observable<HttpResult<String>> sms(@Field("mobile") String mobile, @Field("flag") String flag);

    /*注册OK*/
    @FormUrlEncoded
    @POST("/app/user/register")
    Observable<HttpResult<Object>> register(@Field("mobile") String mobile, @Field("passwd") String password,
                                            @Field("code") String code);

    /*修改密码*/
    @FormUrlEncoded
    @POST("/app/user/changepasswd")
    Observable<HttpResult<Object>> modifyPassword(@Field("passwd") String password);


    /*修改昵称*/

    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
    @POST("/app/user/changeinfo")
    Observable<HttpResult<Object>> modifyNickname(@Field("nickname") String nickname);


    /*退出登录OK*/
    @POST("/app/user/logout")
    Observable<HttpResult<Object>> logout();

    /*我的消息*/
    @FormUrlEncoded
    @POST("/app/message/list")
    Observable<HttpResult<List<Message>>> message(@Field("page") Integer page, @Field("pageSize") Integer pageSize);

    /*游客列表ERROR*/
    @FormUrlEncoded
    @POST("/app/tourist/list")
    Observable<HttpResult<List<Visitor>>> getVisitors(@Field("page") Integer page, @Field("pageSize") Integer pageSize);


    /*新增游客信息*/
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
    @POST("/app/tourist/add")
    Observable<HttpResult<Object>> addVisitor(@Field("name") String name, @Field("mobile") String mobile,
                                              @Field("idcode") String idcode);

    /*删除游客信息*/
    @FormUrlEncoded
    @POST("/app/tourist/delete")
    Observable<HttpResult<Object>> deleteVisitor(@Field("touristId") String touristId);

    /*设为默认游客*/
    @FormUrlEncoded
    @POST("/app/tourist/default")
    Observable<HttpResult<Object>> defaultVisitor(@Field("touristId") String touristId);

    /*编辑游客信息*/
    @FormUrlEncoded
    @POST("/app/tourist/change")
    Observable<HttpResult<Object>> editVisitor(@Field("touristId") String touristId, @Field("name") String name, @Field
            ("mobile") String mobile, @Field("idcode") String idcode);

    /*上传头像*/
    @Multipart
    @POST("/app/user/changeHeadimg")
    Observable<HttpResult<String>> uploadHeadImg(@Part MultipartBody.Part headImg);

    /*获取订单列表*/
    @FormUrlEncoded
    @POST("/app/order/list")
    Observable<HttpResult<List<Order>>> getOrders(@Field("page") Integer page, @Field("pageSize") Integer pageSize,
                                                  @Field("status") Integer status);

    /*获取订单详情*/
    @FormUrlEncoded
    @POST("/app/order/ticketcode")
    Observable<HttpResult<OrderDetail>> getOrderDetail(@Field("orderId") String orderId);

    /*删除/已读消息 1 已读2 删除*/
    @FormUrlEncoded
    @POST("/app/message/confirm")
    Observable<HttpResult<Object>> confirmMsg(@Field("messageId") String messageId, @Field("flag") Integer flag);

    //=================================================================================
    /*忘记密码*/
    @FormUrlEncoded
    @POST("forgetPw")
    Observable<HttpResult<Object>> setNewPassword(@Field("mobile") String mobile, @Field("password") String password,
                                                  @Field("code") String code);

    /*交易明细*/
    @GET("me/deal_list")
    Observable<HttpResult<List<Deal>>> getDealList();

    /*我的优惠券*/
    @GET("me/voucher")
    Observable<HttpResult<List<Discount>>> voucher();

    /*我的钱包*/
    @GET("me/wallet")
    Observable<HttpResult<Wallet>> wallet();


    /*个人信息*/
    @GET("u/{id}")
    Observable<HttpResult<PersonalInfo>> getPersonalInfo(@Path("id") String id);

    /*我的好友*/
    @GET("me/friends")
    Observable<HttpResult<List<Friend>>> getMineFriends();


}
