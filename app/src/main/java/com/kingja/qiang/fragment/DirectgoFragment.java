package com.kingja.qiang.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kingja.qiang.R;
import com.kingja.qiang.activity.XigoMultiDetailActivity;
import com.kingja.qiang.adapter.DirectgoAdapter;
import com.kingja.qiang.base.BaseFragment;
import com.kingja.qiang.injector.component.AppComponent;
import com.kingja.qiang.util.GoUtil;
import com.kingja.qiang.util.ToastUtil;
import com.kingja.qiang.view.LottieHeadView;

import java.util.ArrayList;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Description:TODO
 * Create Time:2018/1/22 13:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DirectgoFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.lv_direct)
    ListView lvDirect;
    @BindView(R.id.store_house_ptr_frame)
    PtrFrameLayout store_house_ptr_frame;


    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        DirectgoAdapter mDirectgoAdapter = new DirectgoAdapter(getActivity(), new ArrayList<String>());
        lvDirect.setAdapter(mDirectgoAdapter);
        lvDirect.setOnItemClickListener(this);
        LottieHeadView lottieHeadView = new LottieHeadView(getActivity());
        lottieHeadView.setUp(store_house_ptr_frame);
        store_house_ptr_frame.setHeaderView(lottieHeadView);
        store_house_ptr_frame.addPtrUIHandler(lottieHeadView);
        store_house_ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                Log.e(TAG, "checkCanDoRefresh: ");
                //检查在当前位置下拉是否可以触发刷新，如果ListView只能在顶部触发
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ToastUtil.showText("开始刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        store_house_ptr_frame.refreshComplete();
                    }
                }, 2000);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                store_house_ptr_frame.autoRefresh(true);
            }
        }, 150);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.frag_xigo_direct;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoUtil.goActivity(getActivity(), XigoMultiDetailActivity.class);
    }
}
