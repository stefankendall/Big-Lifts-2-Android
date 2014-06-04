package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;

import java.math.BigDecimal;

public class JSetStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JSet.class;
    }

    public static JSetStore instance() {
        return (JSetStore) BLJStore.instance(JSetStore.class);
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        JSet set = (JSet) object;
        set.percentage = new BigDecimal("100");
    }

    public JSet create(JLift lift, BigDecimal percentage) {
        JSet set = (JSet) this.create();
        set.lift = lift;
        set.percentage = percentage;
        return set;
    }

    public JSet createWarmup(JLift lift, BigDecimal percentage, int reps) {
        JSet set = this.create(lift, percentage);
        set.warmup = true;
        set.reps = reps;
        return set;
    }

    public JSet createFromSet(JSet set) {
        JSet newSet = (JSet) this.create();
        newSet.reps = set.reps;
        newSet.maxReps = set.maxReps;
        newSet.percentage = set.percentage;
        newSet.lift = set.lift;
        newSet.warmup = set.warmup;
        newSet.amrap = set.amrap;
        newSet.optional = set.optional;
        newSet.assistance = set.assistance;
        return newSet;
    }
}
