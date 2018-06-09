package com.kingja.qiang.page.mine_friends;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface MineFriendsCompnent {
    void inject(MineFriendsActivity activity);
}
