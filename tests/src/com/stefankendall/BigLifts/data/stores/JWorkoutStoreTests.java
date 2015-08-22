package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import junit.framework.Assert;

public class JWorkoutStoreTests extends BLTestCase {
    public void testCanInsertSetsAtStart() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        JSet set1 = (JSet) JSetStore.instance().create();
        JSet set2 = (JSet) JSetStore.instance().create();
        workout.addSets(Lists.newArrayList(set1, set2));

        JSet set3 = (JSet) JSetStore.instance().create();
        JSet set4 = (JSet) JSetStore.instance().create();
        workout.addSets(Lists.newArrayList(set3, set4), 0);
        Assert.assertEquals(workout.sets.get(0), set3);
        Assert.assertEquals(workout.sets.get(1), set4);
        Assert.assertEquals(workout.sets.get(2), set1);
        Assert.assertEquals(workout.sets.get(3), set2);
    }

    public void testWorkoutsAreRemovedWhenAssociatedLiftsAreRemoved() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        JSet set = (JSet) JSetStore.instance().create();
        JLift lift = (JLift) JLiftStore.instance().create();
        set.lift = lift;
        workout.sets.add(set);
        JLiftStore.instance().remove(lift);

        Assert.assertTrue(JWorkoutStore.instance().findAll().contains(workout));
        Assert.assertFalse(JSetStore.instance().findAll().contains(set));
        Assert.assertEquals(workout.sets.size(), 0);
    }

    public void testReturnsEmptyListForNoWarmup() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        Assert.assertEquals(workout.warmupSets().size(), 0);
        Assert.assertEquals(workout.workSets().size(), 0);
        Assert.assertEquals(workout.assistanceSets().size(), 0);
    }
}
