package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.*;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.fto.*;
import junit.framework.Assert;

import java.math.BigDecimal;

public class JFTOCustomAssistanceTests extends BLTestCase {

    public void testSetsUpCustomAssistance() {
        JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().create();
        customAssistanceWorkout.mainLift = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        JFTOCustomAssistanceLift customAssistanceLift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        JSet set = (JSet) JSetStore.instance().create();
        set.lift = customAssistanceLift;
        set.reps = 5;
        set.percentage = new BigDecimal(100);
        customAssistanceWorkout.workout.addSet(set);

        JFTOAssistanceStore.instance().changeTo(JFTOAssistance.SIMPLE_CUSTOM);
        JFTOWorkout squatWorkout1 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(1);
        JFTOWorkout benchWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(0);
        JFTOWorkout squatWorkout2 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 2).get(1);

        Assert.assertEquals(squatWorkout1.workout.sets.size(), 7);
        Assert.assertEquals(squatWorkout2.workout.sets.size(), 7);
        Assert.assertEquals(benchWorkout.workout.sets.size(), 6);

        JSet lastSet = squatWorkout1.workout.sets.get(squatWorkout1.workout.sets.size() - 1);
        Assert.assertTrue(lastSet.assistance);
        Assert.assertEquals(lastSet.lift, customAssistanceLift);
    }

    public void testIncrementsLiftsOnCycleChange() {
        JFTOAssistanceStore.instance().changeTo(JFTOAssistance.SIMPLE_CUSTOM);

        JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        lift.increment = new BigDecimal(5);
        lift.weight = new BigDecimal(100);
        JFTOAssistanceStore.instance().cycleChange();
        Assert.assertEquals(lift.weight.compareTo(new BigDecimal(105)), 0);
    }

    public void testCreatesFtoSetOrSetBasedOnSetup(){
        JFTOCustomAssistanceWorkout customWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().create();
        customWorkout.mainLift = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        customWorkout.workout.addSet((JSet) JFTOSetStore.instance().create());
        customWorkout.workout.addSet((JSet) JSetStore.instance().create());

        JFTOAssistanceStore.instance().changeTo(JFTOAssistance.SIMPLE_CUSTOM);

        JFTOWorkout squat = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(1);
        Assert.assertEquals(squat.workout.assistanceSets().size(), 2);
        Assert.assertTrue(squat.workout.assistanceSets().get(0) instanceof JFTOSet);
        Assert.assertFalse(squat.workout.assistanceSets().get(1) instanceof JFTOSet);
    }
}
