package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceWorkout;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutStore;
import junit.framework.Assert;

public class JFTOCustomAssistanceWorkoutStoreTests extends BLTestCase {
    public void testSetsUpDefaultPlaceholders() {
        Assert.assertEquals(JFTOCustomAssistanceWorkoutStore.instance().count(), 4);
    }

    public void testAdjustsToMainLifts() {
        JFTOLiftStore.instance().removeAtIndex(0);
        Assert.assertEquals(JFTOCustomAssistanceWorkoutStore.instance().count(), 3);
        JFTOLiftStore.instance().create();
        Assert.assertEquals(JFTOCustomAssistanceWorkoutStore.instance().count(), 4);
    }

    public void testDoesNotLeakWorkouts() {
        int workoutCount = JWorkoutStore.instance().count();
        JFTOCustomAssistanceWorkoutStore.instance().removeAtIndex(0);
        Assert.assertEquals(workoutCount - 1, JWorkoutStore.instance().count());
    }

//    public void testCanEmptyAssistance() {
//        JFTOCustomAssistanceWorkout ftoCustomAssistanceWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().first();
//        ftoCustomAssistanceWorkout.workout.addSet((JSet) JSetStore.instance().create());
//
//        JFTOCustomAssistanceWorkoutStore.instance().copyTemplate(JFTOAssistance.NONE);
//        Assert.assertEquals(ftoCustomAssistanceWorkout.workout.sets.size(), 0);
//    }

//    - (void)testCanCopyBbb {
//        [[JFTOCustomAssistanceWorkoutStore instance] copyTemplate:FTO_ASSISTANCE_BORING_BUT_BIG];
//        JFTOCustomAssistanceWorkout *customAssistanceWorkout = [[JFTOCustomAssistanceWorkoutStore instance] first];
//        STAssertEquals((int) [[customAssistanceWorkout.workout sets] count], 5, @"");
//        JSet *firstSet = customAssistanceWorkout.workout.sets[0];
//        STAssertTrue([firstSet isKindOfClass:JFTOSet.class], @"");
//        STAssertEquals([firstSet.reps intValue], 10, @"");
//        STAssertEqualObjects(firstSet.percentage, N(50), @"");
//    }

    public void testRemovesSetsWhenAssistanceLiftsAreDeleted() {
        JFTOCustomAssistanceWorkout ftoCustomAssistanceWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().first();

        JSet setWithLift1 = (JSet) JSetStore.instance().create();
        setWithLift1.lift = (JLift) JFTOCustomAssistanceLiftStore.instance().create();

        JSet setWithLift2 = (JSet) JSetStore.instance().create();
        setWithLift2.lift = (JLift) JFTOCustomAssistanceLiftStore.instance().create();

        ftoCustomAssistanceWorkout.workout.addSet(setWithLift1);
        ftoCustomAssistanceWorkout.workout.addSet(setWithLift2);
        JFTOCustomAssistanceLiftStore.instance().remove(setWithLift1.lift);
        Assert.assertEquals(ftoCustomAssistanceWorkout.workout.sets.size(), 1);
    }
}
