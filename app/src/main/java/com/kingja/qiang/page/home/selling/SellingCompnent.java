package com.kingja.qiang.page.home.selling;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SellingCompnent {
    void inject(SellingFragment fragment);
}
