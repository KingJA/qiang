package com.kingja.qiang.model.service;

import com.kingja.qiang.event.ScenicType;
import com.kingja.qiang.model.HttpResult;
import com.kingja.qiang.model.entiy.City;
import com.kingja.qiang.model.entiy.HotSearch;
import com.kingja.qiang.model.entiy.Login;
import com.kingja.qiang.model.entiy.Message;
import com.kingja.qiang.model.entiy.OrderResult;
import com.kingja.qiang.update.VersionInfo;
import com.kingja.qiang.model.entiy.WeixinPayResult;
import com.kingja.qiang.page.detail.TicketDetail;
import com.kingja.qiang.page.sell.Ticket;
import com.kingja.qiang.page.introduce.SceneryIntroduce;
import com.kingja.qiang.page.order.Order;
import com.kingja.qiang.page.order.orderdetail.OrderDetail;
import com.kingja.qiang.page.visitor.Visitor;

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
    Observable<HttpResult<String>> sms(@Field("mobile") String mobile, @Field("flag") int flag);

    /*注册OK*/
    @FormUrlEncoded
    @POST("/app/user/register")
    Observable<HttpResult<Object>> register(@Field("mobile") String mobile, @Field("passwd") String password,
                                            @Field("code") String code);

    /*修改密码*/
    @FormUrlEncoded
    @POST("/app/user/changepasswd")
    Observable<HttpResult<Object>> modifyPassword(@Field("oldpasswd") String oldpasswd, @Field("passwd") String
            password);


    /*修改昵称*/

    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
    @POST("/app/user/changeinfo")
    Observable<HttpResult<Object>> modifyNickname(@Field("nickname") String nickname);


    /*退出登录OK*/
    @FormUrlEncoded
    @POST("/app/user/logout")
    Observable<HttpResult<Object>> logout(@Field("userId") String userId, @Field("osName") String osName);

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
    Observable<HttpResult<Visitor>> addVisitor(@Field("name") String name, @Field("mobile") String mobile,
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
    Observable<HttpResult<Visitor>> editVisitor(@Field("touristId") String touristId, @Field("name") String name, @Field
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


    /*获取产品列表*/
    @FormUrlEncoded
    @POST("/app/product/list")
    Observable<HttpResult<List<Ticket>>> getTickets(@Field("areaId") String areaId, @Field("productTypeId") String
            productTypeId, @Field("useDates") String useDates, @Field("discountRate") String discountRate, @Field
                                                            ("keyword") String keyword, @Field("page") Integer page,
                                                    @Field("pageSize") Integer pageSize, @Field("status") Integer
                                                            status);

    /*获取热搜*/
    @FormUrlEncoded
    @POST("/app/product/hotsearch")
    Observable<HttpResult<List<HotSearch>>> getHotSearch(@Field("limit") int areaId);

    /*获取产品详情*/
    @FormUrlEncoded
    @POST("/app/product/details")
    Observable<HttpResult<TicketDetail>> getTicketDetail(@Field("productId") String productId);

    /*获取景区介绍*/
    @FormUrlEncoded
    @POST("/app/product/scenic")
    Observable<HttpResult<SceneryIntroduce>> getSceneryIntroduce(@Field("scenicId") String scenicId);

    /*订单保存*/
    @FormUrlEncoded
    @POST("/app/order/submit")
    Observable<HttpResult<OrderResult>> sumbitOrder(@Field("productId") String productId, @Field("touristIds")
            String touristIds, @Field("quantity") int quantity, @Field("from") String from);

    /*支付宝支付*/
    @FormUrlEncoded
    @POST("/app/pay/alipay")
    Observable<HttpResult<String>> alipay(@Field("orderId") String orderId);

    /*微信支付*/
    @FormUrlEncoded
    @POST("/app/pay/weixinpay")
    Observable<HttpResult<WeixinPayResult>> weixinpay(@Field("orderId") String orderId);

    /*忘记密码*/
    @FormUrlEncoded
    @POST("/app/user/findpasswd")
    Observable<HttpResult<Object>> forgetPassword(@Field("mobile") String mobile, @Field("passwd") String passwd,
                                                  @Field("code") String code);

    /*获取景区类型*/
    @FormUrlEncoded
    @POST("/app/dict/data")
    Observable<HttpResult<List<ScenicType>>> getScenicType(@Field("dictCategoryId") String dictCategoryId);

    /*获取地区*/
    @POST("/app/area/list")
    Observable<HttpResult<List<City>>> getCity();


    /*版本检测*/
    @FormUrlEncoded
    @POST("/app/version/detail")
    Observable<HttpResult<VersionInfo>> checkUpdate(@Field("version") String version, @Field("flag") int flag);


    //=================================================================================
}
