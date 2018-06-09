package com.kingja.qiang.util;

import com.kingja.qiang.base.App;
import com.kingja.qiang.constant.VariableConstant;
import com.kingja.qiang.injector.component.DaggerSpComponent;
import com.kingja.qiang.injector.module.AppModule;
import com.kingja.qiang.injector.module.SpModule;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:TODO
 * Create Time:2018/4/17 17:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddTokenInterceptor implements Interceptor {
    @Inject
    SpManager mSpManager;

    public AddTokenInterceptor() {
        DaggerSpComponent.builder().appModule(new AppModule(App.getContext())).spModule(new SpModule()).build()
                .inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if ("POST".equals(request.method())) {
            if (request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                formBody = bodyBuilder
                        .addEncoded(VariableConstant.TOKEN, mSpManager.getToken())
                        .build();
                request = request.newBuilder().post(formBody).build();
            }
        } else if ("GET".equals(request.method())) {
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter(VariableConstant.TOKEN, mSpManager.getToken())
                    .build();
            request = request.newBuilder().url(httpUrl).build();
        }
        return chain.proceed(request);

    }
}
