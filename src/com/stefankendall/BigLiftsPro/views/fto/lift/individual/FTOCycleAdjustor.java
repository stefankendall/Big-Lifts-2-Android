package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOAssistanceStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.plans.JFTOPlan;
import com.stefankendall.BigLiftsPro.data.stores.fto.plans.JFTOWorkoutSetsGenerator;

import java.util.List;

public class FTOCycleAdjustor {
    public void checkForCycleChange() {
        if (this.shouldIncrementLifts()) {
            FTOLiftIncrementer.incrementLifts();
        }

        if (this.cycleNeedsToIncrement()) {
            this.nextCycle();
        }
    }

    public void nextCycle() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout jftoWorkout = (JFTOWorkout) model;
            jftoWorkout.done = false;
        }
        JFTOAssistanceStore.instance().cycleChange();
    }

    public boolean shouldIncrementLifts() {
        return this.cycleNeedsToIncrement() || this.reachedEndOfIncrementWeek();
    }

    private boolean reachedEndOfIncrementWeek() {
        JFTOPlan ftoPlan = new JFTOWorkoutSetsGenerator().planForCurrentVariant();
        int incrementWeek = ftoPlan.incrementMaxesWeeks().get(0);
        boolean upToIncrementDone = true;
        for (int week = 1; week <= incrementWeek; week++) {
            List<JFTOWorkout> workoutsForWeek = (List<JFTOWorkout>) JFTOWorkoutStore.instance().findAllWhere("week", week);
            upToIncrementDone &= Iterables.all(workoutsForWeek, new Predicate<JFTOWorkout>() {
                @Override
                public boolean apply(JFTOWorkout jftoWorkout) {
                    return jftoWorkout.done;
                }
            });
        }

        boolean anyInNextPartDone = false;
        int finalWeek = this.finalWeek(ftoPlan);
        for (int week = incrementWeek + 1; week <= finalWeek; week++) {
            List<JFTOWorkout> workoutsForWeek = (List<JFTOWorkout>) JFTOWorkoutStore.instance().findAllWhere("week", week);
            anyInNextPartDone |= Iterables.any(workoutsForWeek, new Predicate<JFTOWorkout>() {
                @Override
                public boolean apply(JFTOWorkout jftoWorkout) {
                    return jftoWorkout.done;
                }
            });
        }

        return upToIncrementDone && !anyInNextPartDone;
    }

    private int finalWeek(JFTOPlan ftoPlan) {
        int finalWeek = 0;
        for (int week : ftoPlan.generate(null).keySet()) {
            finalWeek = week > finalWeek ? week : finalWeek;
        }
        return finalWeek;
    }

    public boolean cycleNeedsToIncrement() {
        return JFTOWorkoutStore.instance().findAllWhere("done", false).size() == 0;
    }
}
