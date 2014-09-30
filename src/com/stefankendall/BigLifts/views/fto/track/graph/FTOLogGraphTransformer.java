package com.stefankendall.BigLifts.views.fto.track.graph;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLifts.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class FTOLogGraphTransformer {
    public List<Map> buildDataFromLog() {
        List<Map> chartData = Lists.newArrayList();

        List<JWorkoutLog> logs = (List<JWorkoutLog>) JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
        List<JWorkoutLog> noDeload = Lists.newArrayList(Iterables.filter(logs, new Predicate<JWorkoutLog>() {
            @Override
            public boolean apply(JWorkoutLog jWorkoutLog) {
                return !jWorkoutLog.deload;
            }
        }));

        for (JWorkoutLog workoutLog : Lists.reverse(noDeload)) {
            JSetLog bestSetFromWorkout = workoutLog.bestSet();
            List<Map> liftLogEntries = this.logEntriesFromChart(chartData, bestSetFromWorkout.name);
            liftLogEntries.add(this.logToChartEntry(workoutLog, bestSetFromWorkout));
        }
        return chartData;
    }

    protected List<Map> logEntriesFromChart(List<Map> chartData, final String name) {
        if (name == null) {
            return Lists.newArrayList();
        }

        Optional<Map> possibleExistingLiftData = Iterables.tryFind(chartData, new Predicate<Map>() {
            @Override
            public boolean apply(Map map) {
                return name.equals(map.get("name"));
            }
        });

        Map existingLiftData;
        if (possibleExistingLiftData.isPresent()) {
            existingLiftData = possibleExistingLiftData.get();
        } else {
            existingLiftData = Maps.newHashMap();
            existingLiftData.put("name", name);
            existingLiftData.put("data", Lists.newArrayList());
            chartData.add(existingLiftData);
        }

        return (List<Map>) existingLiftData.get("data");
    }

    protected Map logToChartEntry(JWorkoutLog log, JSetLog setLog) {
        Map chartEntry = Maps.newHashMap();
        Map dateMap = Maps.newHashMap();

        Calendar cal = Calendar.getInstance();
        cal.setTime(log.date);

        dateMap.put("year", cal.get(Calendar.YEAR));
        dateMap.put("month", cal.get(Calendar.MONTH) + 1);
        dateMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
        chartEntry.put("weight", OneRepEstimator.estimate(setLog.weight, setLog.reps));
        chartEntry.put("date", dateMap);
        return chartEntry;
    }
}
