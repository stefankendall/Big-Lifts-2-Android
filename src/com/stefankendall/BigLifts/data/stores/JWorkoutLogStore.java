package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class JWorkoutLogStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JWorkoutLog.class;
    }

    public static JWorkoutLogStore instance() {
        return (JWorkoutLogStore) BLJStore.instance(JWorkoutLogStore.class);
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JSetLog.class);
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        JWorkoutLog log = (JWorkoutLog) object;
        log.sets = Lists.newArrayList();
        log.deload = false;
    }

    @Override
    public synchronized List<? extends JModel> findAll() {
        return ImmutableList.copyOf(Ordering.from(new Comparator<JModel>() {
            @Override
            public int compare(JModel model1, JModel model2) {
                JWorkoutLog log1 = (JWorkoutLog) model1;
                JWorkoutLog log2 = (JWorkoutLog) model2;
                return log1.date.compareTo(log2.date);
            }
        }).sortedCopy(this.data));
    }

    public JWorkoutLog create(String name, Date date) {
        JWorkoutLog workoutLog = (JWorkoutLog) this.create();
        workoutLog.name = name;
        workoutLog.date = date;
        return workoutLog;
    }
}