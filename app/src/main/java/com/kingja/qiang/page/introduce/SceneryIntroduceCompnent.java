package com.kingja.qiang.page.introduce;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SceneryIntroduceCompnent {
    void inject(SceneryIntroduceActivity target);
}
