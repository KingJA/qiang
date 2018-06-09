package com.kingja.qiang.page.discount;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface DiscountCompnent {
    void inject(DiscountActivity activity);
}
