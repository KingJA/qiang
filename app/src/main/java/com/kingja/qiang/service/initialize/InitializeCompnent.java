package com.kingja.qiang.service.initialize;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface InitializeCompnent {
    void inject(InitializeService service);
}
