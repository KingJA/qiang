package com.kingja.qiang.injector.module;

import android.app.Application;


import com.kingja.qiang.util.SharedPreferencesIO;
import com.kingja.qiang.util.SpManager;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:48
 * 修改备注：
 */
@Module
public class SpModule {

    public SpModule() {
    }

    @Provides
    public SharedPreferencesIO provideSpIO(Application application) {
        return new SharedPreferencesIO(application);
    }

    @Provides
    public SpManager provideSpManager(SharedPreferencesIO sharedPreferencesIO) {
        return new SpManager(sharedPreferencesIO);
    }
}
