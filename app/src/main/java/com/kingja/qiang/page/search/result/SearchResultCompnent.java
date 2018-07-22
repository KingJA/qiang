package com.kingja.qiang.page.search.result;


import com.kingja.qiang.injector.annotation.PerActivity;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.page.sell.selling.SellingFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface SearchResultCompnent {
    void inject(SearchResultActivity target);
}
