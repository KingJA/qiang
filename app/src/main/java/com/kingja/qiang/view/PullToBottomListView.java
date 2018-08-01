package com.kingja.qiang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Description:TODO
 * Create Time:2018/7/7 16:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PullToBottomListView extends ListView {
    private OnScrollToBottom onScrollToBottom;

    public PullToBottomListView(Context context) {
        super(context);
    }

    public PullToBottomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (onScrollToBottom != null) {
                            onScrollToBottom.onScrollToBottom();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public interface OnScrollToBottom {
        void onScrollToBottom();
    }

    public void setOnScrollToBottom(OnScrollToBottom onScrollToBottom) {
        this.onScrollToBottom = onScrollToBottom;
    }

}
