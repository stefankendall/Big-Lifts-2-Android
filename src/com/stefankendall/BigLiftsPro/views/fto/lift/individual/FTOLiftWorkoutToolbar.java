package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.helpers.SetHelper;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

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

        JSet heaviestAmrap = SetHelper.heaviestAmrapSet(this.jftoWorkout.workout.sets);

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat((JFTOLift) heaviestAmrap.lift, heaviestAmrap.roundedEffectiveWeight());
        TextView repsTextView = (TextView) view.findViewById(R.id.reps);
        if (repsTextView != null) {
            repsTextView.setText(repsToBeat + "");
        }

        return view;
    }
}
