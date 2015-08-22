package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.helpers.SetHelper;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class FTOLiftWorkoutToolbar implements CustomListItem {
    private final JFTOWorkout jftoWorkout;

    public FTOLiftWorkoutToolbar(JFTOWorkout jftoWorkout) {
        this.jftoWorkout = jftoWorkout;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_toolbar_cell, null);
        }

        JSet heaviestAmrap = SetHelper.heaviestAmrapSet(this.jftoWorkout.workout.workSets());

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat((JFTOLift) heaviestAmrap.lift, heaviestAmrap.roundedEffectiveWeight());
        TextView repsTextView = (TextView) view.findViewById(R.id.reps);
        if (repsTextView != null) {
            repsTextView.setText(repsToBeat + "");
        }

        return view;
    }
}
