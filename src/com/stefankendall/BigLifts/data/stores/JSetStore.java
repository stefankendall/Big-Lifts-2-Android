package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JLift.class, JFTOLift.class);
    }

    public JSet create(JLift lift, BigDecimal percentage) {
        JSet set = (JSet) this.create();
        set.lift = lift;
        set.percentage = percentage;
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

    public void adjustToLifts() {
        List<JSet> deadSets = Lists.newArrayList();
        for (JModel model : this.data) {
            JSet set = (JSet) model;
            if (set.lift != null && set.lift.isDead()) {
                deadSets.add(set);
            }
        }
        for (JSet deadSet : deadSets) {
            this.remove(deadSet);
        }
    }
}
