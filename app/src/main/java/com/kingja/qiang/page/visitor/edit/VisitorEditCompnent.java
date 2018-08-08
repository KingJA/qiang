package com.kingja.qiang.page.visitor.edit;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface VisitorEditCompnent {
    void inject(VisitorEditActivity activity);
}
