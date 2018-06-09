package com.kingja.qiang.page.login;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.injector.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface LoginCompnent {
    void inject(LoginActivity activity);
}
