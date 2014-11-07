package com.stefankendall.BigLifts.views.fto.track.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class LogDateEditCell implements CustomListItem {
    private final JWorkoutLog workoutLog;

    public LogDateEditCell(JWorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.date_cell, null);
        }

        DatePicker picker = (DatePicker) view.findViewById(R.id.date_picker);
        if (picker != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.workoutLog.date);
            picker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    LogDateEditCell.this.workoutLog.date = cal.getTime();
                }
            });
        }

        return view;
    }
}
