package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.fullcustom;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

public class FTOFullCustomWorkoutSummaryLiftCell implements CustomListItem {
    public final JFTOFullCustomAssistanceWorkout jftoFullCustomAssistanceWorkout;

    public FTOFullCustomWorkoutSummaryLiftCell(JFTOFullCustomAssistanceWorkout jftoFullCustomAssistanceWorkout) {
        this.jftoFullCustomAssistanceWorkout = jftoFullCustomAssistanceWorkout;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.simple_list_item, null);
        }

        TextView label = (TextView) view.findViewById(R.id.textView);
        if (label != null) {
            label.setText(jftoFullCustomAssistanceWorkout.mainLift.name);
        }

        return view;
    }
}
