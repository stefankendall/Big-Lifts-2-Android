package com.stefankendall.BigLiftsPro.views.fto.track.graph;

import android.text.format.DateFormat;
import com.google.common.base.Strings;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;

public class FTOLogExporter {
    public static String csv() {
        String csv = "name,date,weight,reps,estimated max\n";
        java.text.DateFormat format = DateFormat.getDateFormat(App.getContext());
        for (JModel model : JWorkoutLogStore.instance().findAllWhere("name", "5/3/1")) {
            JWorkoutLog log = (JWorkoutLog) model;
            JSetLog bestSet = log.bestSet();
            csv += Strings.nullToEmpty(bestSet.name);
            csv += ",";
            csv += Strings.nullToEmpty(format.format(log.date));
            csv += ",";
            csv += BigDecimals.print(bestSet.weight);
            csv += ",";
            csv += bestSet.reps + "";
            csv += ",";
            BigDecimal oneRep = OneRepEstimator.estimate(bestSet.weight, bestSet.reps);
            csv += BigDecimals.print(oneRep);
            csv += "\n";
        }
        return csv;
    }
}
