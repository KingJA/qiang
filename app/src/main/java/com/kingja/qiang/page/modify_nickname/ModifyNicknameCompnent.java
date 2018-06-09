package com.kingja.qiang.page.modify_nickname;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface ModifyNicknameCompnent {
    void inject(ModifyNicknameActivity activity);
}
