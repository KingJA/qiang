package com.kingja.qiang.page.message;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface MessageCompnent {
    void inject(MessageActivity activity);
}
