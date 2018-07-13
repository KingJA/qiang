package com.kingja.qiang.page.visitor.prefect;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.visitor.edit.VisitorEditActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface VisitorPrefectCompnent {
    void inject(VisitorPrefectActivity activity);
}
