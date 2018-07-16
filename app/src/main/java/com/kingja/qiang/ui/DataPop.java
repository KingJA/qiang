package com.kingja.qiang.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kingja.qiang.R;
import com.kingja.popwindowsir.BasePop;
import com.kingja.popwindowsir.PopConfig;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/22 13:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataPop extends BasePop {

    public DataPop(Context context) {
        super(context);
    }

    public DataPop(Context context, PopConfig popConfig) {
        super(context, popConfig);
    }

    @Override
    protected void initPop() {

    }

    @Override
    protected void initView(View contentView) {
        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        CalendarPickerView calendar = contentView.findViewById(R.id.calendar_view);
        TextView tv_confirm = contentView.findViewById(R.id.tv_confirm);
        calendar.setCustomDayView(new DefaultDayViewAdapter());
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(new Date(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Date> selectedDates = calendar.getSelectedDates();
                for (Date selectedDate : selectedDates) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String result = format.format(selectedDate);
                    Log.e("DataPop", "result: "+result );
                }

            }
        });
    }

    @Override
    protected View getLayoutView() {
        return View.inflate(context, R.layout.pop_date, null);
    }
}
