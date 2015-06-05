package com.stefankendall.BigLiftsPro.views.fto.lift;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

public class FTOWorkoutSummaryCell implements CustomListItem {

    protected final JFTOWorkout jftoWorkout;

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
