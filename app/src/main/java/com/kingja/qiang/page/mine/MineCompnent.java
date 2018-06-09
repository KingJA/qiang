package com.kingja.qiang.page.mine;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface MineCompnent {
    void inject(MineFragment fragment);
}
