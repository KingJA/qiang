package com.kingja.qiang.page.visitor.add;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface VisitorAddCompnent {
    void inject(VisitorAddActivity activity);
}
