package com.kingja.qiang.update;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.kingja.qiang.constant.Constants;
import com.kingja.qiang.model.entiy.HttpResult;
import com.kingja.qiang.model.service.UserService;
import com.kingja.qiang.util.DialogUtil;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.util.TokenHeadInterceptor;
import com.kingja.qiang.util.VersionUtil;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:TODO
 * Create Time:2018/8/1 0001 下午 4:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VersionUpdateSir {

    private static VersionUpdateSir mInstance;
    private Activity context;
    private UserService userService;

    private VersionUpdateSir(Activity context) {
        this.context = context;
        initService();
    }

    private void initService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new TokenHeadInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        userService = retrofit.create(UserService.class);
    }

    public static VersionUpdateSir getInstance(Activity context) {
        if (mInstance == null) {
            synchronized (VersionUpdateSir.class) {
                if (mInstance == null) {
                    mInstance = new VersionUpdateSir(context);
                }
            }
        }
        return mInstance;
    }

    public void checkUpdate() {
        Disposable disposable = userService.checkUpdate(VersionUtil.getVersionCode(context) + "", 1).subscribeOn
                (Schedulers
                        .io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe
                (new Consumer<HttpResult<VersionInfo>>() {
                    @Override
                    public void accept(HttpResult<VersionInfo> result) throws Exception {
                        Logger.d("检查更新");
                        Logger.json(new Gson().toJson(result));
                        if (result.getCode() == 0) {
                            //访问成功
                            VersionInfo versionInfo = result.getData();
                            if (versionInfo != null) {
                                int isLatest = versionInfo.getIsLatest();
                                if (isLatest == 0) {
                                    //需要更新
                                    String latestContent = versionInfo.getLatestContent();
                                    String downloadUrl = versionInfo.getLatestDownload();
                                    DialogUtil.showDoubleDialog(context, "有新版本更新", latestContent, new MaterialDialog
                                            .SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                                                which) {
                                            new DownloadTask(context, true).execute(downloadUrl);
                                        }
                                    }, new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                                                which) {
                                            ToastUtil.showText("取消更新");
                                        }
                                    });

                                }
                            }
                        }
                        //不做任何提示

                    }
                });
    }
}
