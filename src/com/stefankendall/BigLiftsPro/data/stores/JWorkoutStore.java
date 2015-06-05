package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSet;

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
        for (JModel model : JWorkoutStore.instance().findAll()) {
            JWorkout workout = (JWorkout) model;
            List<JSet> deadSets = Lists.newArrayList();
            for (JSet set : workout.sets) {
                if (set.isDead() || set.lift.isDead()) {
                    deadSets.add(set);
                }
            }
            workout.removeSets(deadSets);
        }
    }
}
