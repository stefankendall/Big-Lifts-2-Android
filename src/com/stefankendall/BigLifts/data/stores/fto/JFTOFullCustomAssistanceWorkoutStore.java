package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;

import java.util.List;

public class JFTOFullCustomAssistanceWorkoutStore extends BLJStore {
    @Override
    public void setDefaultsForObject(JModel object) {
        super.setDefaultsForObject(object);
        JFTOFullCustomAssistanceWorkout customAssistanceWorkout = (JFTOFullCustomAssistanceWorkout) object;
        customAssistanceWorkout.workout = (JWorkout) JWorkoutStore.instance().create();
    }

    @Override
    public void setupDefaults() {
        this.adjustToFtoWorkouts();
    }

    public void adjustToFtoWorkouts() {
        this.addMissingWorkouts();
        this.removeUnneededWorkouts();
    }

    private void addMissingWorkouts() {
        this.addForLifts();
        this.addForWeeks();
    }

    private void addForWeeks() {
        List<Integer> allWeeks = Lists.newArrayList(Iterables.filter(
                JFTOWorkoutStore.instance().unique("week"), Integer.class));
        List<Integer> existingWeeks = Lists.newArrayList(Iterables.filter(
                JFTOFullCustomAssistanceWorkoutStore.instance().unique("week"), Integer.class));

        for (Integer week : allWeeks) {
            if (!existingWeeks.contains(week)) {
                for (JModel model : JFTOLiftStore.instance().findAll()) {
                    JFTOLift lift = (JFTOLift) model;
                    JFTOFullCustomAssistanceWorkout customAssistanceWorkout = (JFTOFullCustomAssistanceWorkout) this.create();
                    customAssistanceWorkout.mainLift = lift;
                    customAssistanceWorkout.week = week;
                }
            }
        }
    }

    private void addForLifts() {
        List<Integer> weeks = Lists.newArrayList(Iterables.filter(JFTOWorkoutStore.instance().unique("week"), Integer.class));
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift lift = (JFTOLift) model;
            if (this.find("mainLift", lift) == null) {
                for (Integer week : weeks) {
                    JFTOFullCustomAssistanceWorkout customAssistanceWorkout = (JFTOFullCustomAssistanceWorkout) this.create();
                    customAssistanceWorkout.mainLift = lift;
                    customAssistanceWorkout.week = week;
                }
            }
        }
    }

    private void removeUnneededWorkouts() {
        List<JFTOLift> lifts = (List<JFTOLift>) JFTOLiftStore.instance().findAll();
        List<Integer> weeks = Lists.newArrayList(Iterables.filter(
                JFTOWorkoutStore.instance().unique("week"), Integer.class));
        for (JModel model : this.findAll()) {
            JFTOFullCustomAssistanceWorkout customAssistanceWorkout = (JFTOFullCustomAssistanceWorkout) model;
            if (!lifts.contains(customAssistanceWorkout.mainLift) || !weeks.contains(customAssistanceWorkout.week)) {
                this.remove(customAssistanceWorkout);
            }
        }
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOFullCustomAssistanceWorkout.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JFTOLift.class, JLift.class, JWorkout.class);
    }

    public static JFTOFullCustomAssistanceWorkoutStore instance() {
        return (JFTOFullCustomAssistanceWorkoutStore) BLJStore.instance(JFTOFullCustomAssistanceWorkoutStore.class);
    }
}
