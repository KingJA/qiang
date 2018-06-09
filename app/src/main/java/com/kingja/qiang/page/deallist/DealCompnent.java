package com.kingja.qiang.page.deallist;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface DealCompnent {
    void inject(DealListActivity activity);
}
