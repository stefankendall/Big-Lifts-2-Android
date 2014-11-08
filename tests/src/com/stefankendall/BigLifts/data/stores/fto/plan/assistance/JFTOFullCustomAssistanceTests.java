package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOFullCustomAssistanceWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.assistance.JFTOFullCustomAssistance;
import junit.framework.Assert;

public class JFTOFullCustomAssistanceTests extends BLTestCase {
    public void testSetsUpFullCustom() {
        JFTOFullCustomAssistanceWorkout workout1 = (JFTOFullCustomAssistanceWorkout) JFTOFullCustomAssistanceWorkoutStore.instance().create();
        workout1.week = 1;
        workout1.mainLift = (JFTOLift) JFTOLiftStore.instance().find("name", "Bench");

        JSet set1 = (JSet) JSetStore.instance().create();
        set1.reps = 1;
        workout1.workout.addSet(set1);

        JFTOFullCustomAssistanceWorkout workout2 = (JFTOFullCustomAssistanceWorkout) JFTOFullCustomAssistanceWorkoutStore.instance().create();
        workout2.week = 2;
        workout2.mainLift = (JFTOLift) JFTOLiftStore.instance().find("name", "Bench");
        JSet set2 = (JSet) JSetStore.instance().create();
        set2.reps = 2;
        workout2.workout.addSet(set2);

        new JFTOFullCustomAssistance().setup();
        JFTOWorkout bench1 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(0);
        JFTOWorkout notBench1 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(1);
        JFTOWorkout bench2 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 2).get(0);

        Assert.assertEquals(bench1.workout.assistanceSets().size(), 1);
        Assert.assertEquals(notBench1.workout.assistanceSets().size(), 0);
        Assert.assertEquals(bench2.workout.assistanceSets().size(), 1);

        JSet bench1LastSet = bench1.workout.sets.get(bench1.workout.sets.size() - 1);
        JSet bench2LastSet = bench2.workout.sets.get(bench2.workout.sets.size() - 1);

        Assert.assertEquals((int) bench1LastSet.reps, 1);
        Assert.assertEquals((int) bench2LastSet.reps, 2);
    }
}
