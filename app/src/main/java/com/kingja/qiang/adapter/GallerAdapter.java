package com.kingja.qiang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.qiang.model.entiy.Item;
import com.kingja.qiang.util.ScreenUtil;
import com.kingja.supershapeview.core.SuperManager;
import com.kingja.supershapeview.view.SuperShapeImageView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Description:TODO
 * Create Time:2018/3/28 11:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GallerAdapter extends BaseRvAdaper<Item> {
    private int currentPosition;

    public GallerAdapter(Context context, List<Item> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(ViewGroup parent, View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        lp.width = parent.getMeasuredWidth() /4;
        lp.height = (int) (lp.width*1.43f);
        Log.e(TAG, "width: "+lp.width );
        itemView.setLayoutParams(lp);
        return new MyViewHolder(itemView);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_multi;
    }

    @Override
    public Item getItemAtPosition(int position) {
        return list.get(position);
    }

    public void selectPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Item item, int position) {
         MyViewHolder holder = (MyViewHolder) baseHolder;
        SuperManager superManager = holder.iv.getSuperManager();
        if (currentPosition != position) {
            holder.itemView.setScaleX(0.8f);
            holder.itemView.setScaleY(0.8f);
            superManager.setStrokeWidth(0);
        }else{
            holder.itemView.setScaleX(1f);
            holder.itemView.setScaleY(1f);
            superManager.setStrokeWidth(ScreenUtil.dip2px(context,1));
        }

        holder.iv.setImageResource(item.getResId());
        holder.tv.setText(item.getTitle());
    }

    class MyViewHolder extends ViewHolder {
        public SuperShapeImageView iv;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
