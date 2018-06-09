package com.kingja.qiang.injector.component;


import android.app.Application;

import com.kingja.qiang.imgaeloader.IImageLoader;
import com.kingja.qiang.injector.module.ApiModule;
import com.kingja.qiang.injector.module.AppModule;
import com.kingja.qiang.injector.module.ImageLoaderModule;
import com.kingja.qiang.injector.module.SpModule;
import com.kingja.qiang.model.api.UserApi;
import com.kingja.qiang.util.SpManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:42
 * 修改备注：
 */
@Singleton
@Component(modules = {ApiModule.class, AppModule.class, SpModule.class,ImageLoaderModule.class})
public interface AppComponent {
    UserApi getApi();
    SpManager getSpManager();
    Application getApplication();
    IImageLoader getImageLoader();
}
