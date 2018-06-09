package com.kingja.qiang.page.forgetpassword;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface ForgetPasswordCompnent {
    void inject(ForgetPasswordActivity activity);
}
