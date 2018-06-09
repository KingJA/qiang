package com.kingja.qiang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description：RecyclerView 通用适配器
 * Create Time：2016/8/16 10:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseRvAdaper<T> extends RecyclerView.Adapter<BaseRvAdaper.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    protected Context context;
    protected List<T> list;
    private BaseRvAdaper<T> baseRvAdaper;

    public interface OnItemClickListener {
        void onItemClick(BaseRvAdaper<?> baseRvAdaper, View itemView, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(BaseRvAdaper<?> baseRvAdaper, View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {

        this.onItemLongClickListener = onItemLongClickListener;
    }

    public BaseRvAdaper(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        this.baseRvAdaper = this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemView(), parent, false);
        return createViewHolder(parent,itemView);
    }

    protected abstract ViewHolder createViewHolder(ViewGroup parent, View itemView);

    protected abstract int getItemView();

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(baseRvAdaper, v, holder.getAdapterPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(baseRvAdaper, v, holder.getAdapterPosition());
                }
                return true;
            }
        });
        bindHolder(holder, getItemAtPosition(position), position);
    }

    public abstract T getItemAtPosition(int position);

    protected abstract void bindHolder(ViewHolder baseHolder, T t, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setData(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}
