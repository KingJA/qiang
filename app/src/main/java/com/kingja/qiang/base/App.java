package com.kingja.qiang.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.kingja.qiang.R;
import com.kingja.qiang.callback.EmptyCallback;
import com.kingja.qiang.callback.EmptyCartCallback;
import com.kingja.qiang.callback.EmptyTicketCallback;
import com.kingja.qiang.callback.EmptyVisitorCallback;
import com.kingja.qiang.callback.EmptyMsgCallback;
import com.kingja.qiang.callback.EmptyOrderCallback;
import com.kingja.qiang.callback.ErrorNetworkCallback;
import com.kingja.qiang.callback.LoadingCallback;
import com.kingja.qiang.callback.UnLoginCallback;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.injector.component.DaggerAppComponent;
import com.kingja.qiang.injector.module.ApiModule;
import com.kingja.qiang.injector.module.AppModule;
import com.kingja.qiang.injector.module.SpModule;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.qiang.util.SpSir;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;


/**
 * Description：App
 * Create Time：2016/10/14:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class App extends MultiDexApplication {
    private static App sInstance;
    private AppComponent appComponent;
    private static SharedPreferences mSharedPreferences;
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initJPush();
        initLoadSir();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        sInstance = this;
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE);
        setupComponent();
        initBugly();
        Logger.d("token:"+ SpSir.getInstance().getToken());

        SpSir.getInstance().clearMsgCount();
    }

    private void initBugly() {
        Beta.enableNotification = true;
        Bugly.init(getApplicationContext(), Constants.APP_ID_BUDLY, false);
    }

    private void initJPush() {
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        // 初始化 JPush
        JPushInterface.init(this);
    }

    public static SharedPreferences getSp() {
        return mSharedPreferences;
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorNetworkCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCartCallback())
                .addCallback(new EmptyOrderCallback())
                .addCallback(new EmptyMsgCallback())
                .addCallback(new EmptyVisitorCallback())
                .addCallback(new EmptyTicketCallback())
                .addCallback(new UnLoginCallback())
                .commit();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .spModule(new SpModule()).build();
        appModule = new AppModule(this);
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppModule getAppModule() {
        return appModule;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);/*64K说拜拜*/
    }
}
