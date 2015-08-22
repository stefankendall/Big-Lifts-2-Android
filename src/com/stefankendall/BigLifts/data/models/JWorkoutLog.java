package com.stefankendall.BigLifts.data.models;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.allprograms.formulas.OneRepEstimator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JWorkoutLog extends JModel {
    public String name;
    public List<JSetLog> sets;
    public Date date;
    public boolean deload;

    @Override
    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList("sets");
    }

    public List<JSetLog> workSets() {
        return Lists.newArrayList(Iterables.filter(this.sets, new Predicate<JSetLog>() {
            @Override
            public boolean apply(JSetLog jSetLog) {
                return !jSetLog.warmup && !jSetLog.assistance;
            }
        }));
    }

    public JSetLog bestSet() {
        JSetLog bestSet = this.workSets().get(0);
        for (JSetLog set : this.workSets()) {
            BigDecimal bestMaxEstimate = OneRepEstimator.estimate(bestSet.weight, bestSet.reps);
            BigDecimal maxEstimateForSet = OneRepEstimator.estimate(set.weight, set.reps);
            if (maxEstimateForSet.compareTo(bestMaxEstimate) > 0) {
                bestSet = set;
            }
        }
        return bestSet;
    }

    @Override
    public String toString() {
        JSetLog setLog = this.sets.get(0);
        return this.date.toString() + " " + setLog.name;
    }

    public void addSet(JSetLog log) {
        this.sets.add(log);
    }
}
