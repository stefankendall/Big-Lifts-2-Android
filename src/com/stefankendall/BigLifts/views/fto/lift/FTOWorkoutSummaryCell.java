package com.stefankendall.BigLifts.views.fto.lift;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class FTOWorkoutSummaryCell implements CustomListItem {

    private final JFTOWorkout jftoWorkout;

    public FTOWorkoutSummaryCell(JFTOWorkout jftoWorkout) {
        this.jftoWorkout = jftoWorkout;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_workout_summary_cell, null);
        }

        TextView label = (TextView) view.findViewById(R.id.label);
        if (label != null) {
            if (jftoWorkout.workout.sets.size() > 0) {
                JSet set = jftoWorkout.workout.sets.get(0);
                label.setText(set.lift.name);
            } else {
                label.setText("Empty workout");
            }

            ImageView checkmark = (ImageView) view.findViewById(R.id.checkmark);
            checkmark.setVisibility(jftoWorkout.done ? View.VISIBLE : View.GONE);
        }

        return view;
    }
}
