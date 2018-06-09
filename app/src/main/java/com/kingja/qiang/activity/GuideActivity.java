package com.kingja.qiang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.qiang.MainActivity;
import com.kingja.qiang.R;
import com.kingja.qiang.util.GoUtil;

/**
 * Description:TODO
 * Create Time:2018/5/14 13:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GuideActivity extends AppCompatActivity {
    private int[] mGuideImgs = {R.mipmap.bg_guide_ticket, R.mipmap.bg_guide_recommend, R.mipmap.bg_guide_gift};
    private TextView tv_guide_goHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        ViewPager vp_guide = findViewById(R.id.vp_guide);
        tv_guide_goHome = findViewById(R.id.tv_guide_goHome);
        tv_guide_goHome.setOnClickListener(v -> {
            GoUtil.goActivityAndFinish(this, MainActivity.class);
        });
        vp_guide.setAdapter(pagerAdapter);
        vp_guide.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tv_guide_goHome.setVisibility(position==mGuideImgs.length-1?View.VISIBLE:View.GONE);

            }
        });
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return mGuideImgs.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView guideImageView = new ImageView(GuideActivity.this);
            guideImageView.setBackgroundResource(mGuideImgs[position]);
            container.addView(guideImageView);
            return guideImageView;
        }
    };

}
