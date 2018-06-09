package com.kingja.qiang.page.setting;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SettingCompnent {
    void inject(SettingActivity activity);
}
