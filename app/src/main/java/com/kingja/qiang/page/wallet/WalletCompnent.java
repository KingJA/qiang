package com.kingja.qiang.page.wallet;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface WalletCompnent {
    void inject(WalletActivity activity);
}
