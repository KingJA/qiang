package com.kingja.qiang.page.pay;


import android.app.Activity;

import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.injector.module.ActivityModule;
import com.kingja.qiang.page.modifypassword.ModifyPasswordActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface PayCompnent {
    void inject(PayActivity activity);
}
