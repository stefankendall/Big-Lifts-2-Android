package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOPlan;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOWorkoutSetsGenerator;

class JFTOWorkoutStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOWorkout.class;
    }

    @Override
    public void setupDefaults() {
        this.switchTemplate();
    }

    private void switchTemplate() {
        this.restoreTemplate();
        //todo: add assistance
    }

    private void restoreTemplate() {
        ListMultimap<Integer, JLift> doneLiftsByWeek = this.getDoneLiftsByWeek();
        this.removeAll();
        this.createWorkoutsForEachLift();
    }

    private ListMultimap<Integer, JLift> getDoneLiftsByWeek() {
        ListMultimap<Integer, JLift> doneLiftsByWeek = ArrayListMultimap.create();
        for (JModel object : this.findAll()) {
            JFTOWorkout ftoWorkout = (JFTOWorkout) object;
            if (ftoWorkout.done) {
                JLift lift = ftoWorkout.workout.sets.get(0).lift;
                if (lift != null) {
                    doneLiftsByWeek.put(ftoWorkout.week, lift);
                }
            }
        }
        return doneLiftsByWeek;
    }

    private void createWorkoutsForEachLift() {
        JFTOVariant variant = (JFTOVariant) JFTOVariantStore.instance().first();
        JFTOPlan ftoPlan = new JFTOWorkoutSetsGenerator().planForVariant(variant.name);
    }
}