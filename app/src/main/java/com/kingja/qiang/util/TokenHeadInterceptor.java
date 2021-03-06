package com.kingja.qiang.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Headers;

/**
 * Description:TODO
 * Create Time:2018/4/17 17:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TokenHeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("token", SpSir.getInstance().getToken())
//                .addHeader("Content-Type", SpSir.getInstance().getToken())
                .build();
        return chain.proceed(request);
    }

//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
}
