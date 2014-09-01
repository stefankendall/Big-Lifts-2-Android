package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;

import java.util.List;

public class JWorkoutStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JWorkout.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JSet.class, JFTOSet.class);
    }

    public static JWorkoutStore instance() {
        return (JWorkoutStore) BLJStore.instance(JWorkoutStore.class);
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        JWorkout workout = (JWorkout) object;
        workout.sets = Lists.newArrayList();
    }

    public void adjustToLifts() {

    }
}
