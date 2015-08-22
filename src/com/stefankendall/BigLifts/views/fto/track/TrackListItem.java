package com.stefankendall.BigLifts.views.fto.track;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.common.collect.Iterables;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLifts.data.helpers.SetHelper;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class TrackListItem implements CustomListItem {
    private JWorkoutLog workoutLog;

    public TrackListItem(JWorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.track_list_item, null);
        }

        TextView date = (TextView) view.findViewById(R.id.date);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyLocalizedPattern("M/d/y");
        date.setText(format.format(workoutLog.date));

        LinearLayout logs = (LinearLayout) view.findViewById(R.id.logs);
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        if (logs != null) {
            logs.removeAllViews();
            for (JSetLog setLog : Iterables.filter(workoutLog.sets, FTOLogFilterFactory.filterForLogState(this.workoutLog))) {
                View entry = inflater.inflate(R.layout.log_single_entry, null);
                TextView liftName = (TextView) entry.findViewById(R.id.lift_name);
                TextView weight = (TextView) entry.findViewById(R.id.weight);
                TextView reps = (TextView) entry.findViewById(R.id.reps);

                liftName.setText(setLog.name);

                weight.setText(BigDecimals.print(setLog.weight) + " " + settings.units);
                reps.setText(setLog.reps + "x");
                logs.addView(entry);
            }

            if (!workoutLog.deload && workoutLog.workSets().size() > 0) {
                View entry = inflater.inflate(R.layout.log_1rm_estimate, null);
                JSetLog logToShow = SetHelper.heaviestAmrapSetLog(this.workoutLog.sets);
                if (logToShow == null) {
                    List<JSetLog> workSets = this.workoutLog.workSets();
                    logToShow = workSets.get(workSets.size() - 1);
                }
                BigDecimal estimate = OneRepEstimator.estimate(logToShow.weight, logToShow.reps);
                TextView weight = (TextView) entry.findViewById(R.id.weight);
                weight.setText(BigDecimals.print(estimate) + " " + settings.units);
                logs.addView(entry);
            }
        }

        return view;
    }
}
