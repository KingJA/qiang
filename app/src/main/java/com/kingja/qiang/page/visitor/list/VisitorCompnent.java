package com.kingja.qiang.page.visitor.list;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface VisitorCompnent {
    void inject(VisitorListActivity activity);
}
