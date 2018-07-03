package com.kingja.qiang.page.visitor;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.register.RegisterActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface VisitorCompnent {
    void inject(VisitorActivity activity);
}
