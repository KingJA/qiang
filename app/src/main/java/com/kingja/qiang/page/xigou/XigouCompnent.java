package com.kingja.qiang.page.xigou;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.wallet.WalletActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface XigouCompnent {
    void inject(XigouFragment target);
}
