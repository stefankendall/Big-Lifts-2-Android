package com.stefankendall.BigLifts.views.fto.track.edit;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.text.SimpleDateFormat;

public class LogDateEditCell implements CustomListItem {
    private final JWorkoutLog workoutLog;
    private final FragmentActivity activity;

    public LogDateEditCell(JWorkoutLog workoutLog, FragmentActivity activity) {
        this.workoutLog = workoutLog;
        this.activity = activity;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.date_cell, null);
        }

        final Button dateButton = (Button) view.findViewById(R.id.date_button);
        if (dateButton != null) {
            final SimpleDateFormat format = new SimpleDateFormat();
            format.applyLocalizedPattern("M/d/y");
            dateButton.setText(format.format(this.workoutLog.date));
            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = FTOEditDateDialogFragment.newInstance(LogDateEditCell.this.workoutLog, new DialogInterface() {
                        @Override
                        public void cancel() {
                        }
                        @Override
                        public void dismiss() {
                            dateButton.setText(format.format(LogDateEditCell.this.workoutLog.date));
                        }
                    });
                    newFragment.show(LogDateEditCell.this.activity.getSupportFragmentManager(), "datePicker");
                }
            });
        }

        return view;
    }
}
