package com.kingja.qiang.imgaeloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kingja.qiang.R;
import com.kingja.qiang.constant.Constants;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GlideLoader implements IImageLoader {
    @Override
    public void loadImage(Context context, String url, int resourceId, ImageView view) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .centerCrop()
                .placeholder(resourceId == -1 ? R.mipmap.ic_logo : resourceId)
                .crossFade()
                .into(view);
    }
}
