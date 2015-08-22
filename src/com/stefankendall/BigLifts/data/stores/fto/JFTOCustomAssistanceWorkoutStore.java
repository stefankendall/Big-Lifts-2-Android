package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;

import java.util.List;

public class JFTOCustomAssistanceWorkoutStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOCustomAssistanceWorkout.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JFTOLift.class, JLift.class, JWorkout.class);
    }

    public static JFTOCustomAssistanceWorkoutStore instance() {
        return (JFTOCustomAssistanceWorkoutStore) BLJStore.instance(JFTOCustomAssistanceWorkoutStore.class);
    }

    public void setDefaultsForObject(JModel model) {
        super.setDefaultsForObject(model);
        JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) model;
        customAssistanceWorkout.workout = (JWorkout) JWorkoutStore.instance().create();
    }

    public void setupDefaults() {
        this.adjustToMainLifts();
    }

    public void adjustToMainLifts() {
        this.addMissingWorkouts();
        this.removeUnneededWorkouts();
    }

    public void removeUnneededWorkouts() {
        List<JFTOLift> allLifts = (List<JFTOLift>) JFTOLiftStore.instance().findAll();

        List<JFTOCustomAssistanceWorkout> toRemove = Lists.newArrayList();
        for (JModel model : this.findAll()) {
            JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) model;
            if (!allLifts.contains(customAssistanceWorkout.mainLift)) {
                toRemove.add(customAssistanceWorkout);
            }
        }

        for (JFTOCustomAssistanceWorkout customAssistanceWorkout : toRemove) {
            this.remove(customAssistanceWorkout);
        }
    }

    public void addMissingWorkouts() {
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift lift = (JFTOLift) model;
            if (this.find("mainLift", lift) == null) {
                JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) this.create();
                customAssistanceWorkout.mainLift = lift;
            }
        }
    }

    public void copyTemplate(String variant) {
    }

    public void removeSetsForMissingAssistanceLifts() {
        for (JModel model : this.findAll()) {
            JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) model;
            List<JSet> deadSets = Lists.newArrayList();
            for (JSet set : customAssistanceWorkout.workout.sets) {
                if (set.lift.isDead()) {
                    deadSets.add(set);
                }
            }
            customAssistanceWorkout.workout.removeSets(deadSets);
        }
    }
}
