package com.kingja.qiang.page.mine.headimg;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.injector.module.ActivityModule;
import com.kingja.qiang.page.login.LoginActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface PersonalCompnent {
    void inject(PersonalActivity activity);
}
