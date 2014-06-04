package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import junit.framework.Assert;

public class JFTOWorkoutStoreTest extends BLTestCase {
    public void testRemovesWorkoutsAndSetsOnRemove() {
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().create();
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        ftoWorkout.workout = workout;
        int workoutCount = JWorkoutStore.instance().count();
        ftoWorkout.workout.addSet((JSet) JFTOSetStore.instance().create());
        int setCount = JFTOSetStore.instance().count();

        JFTOWorkoutStore.instance().remove(ftoWorkout);

        Assert.assertEquals(workoutCount - 1, JWorkoutStore.instance().count());
        Assert.assertEquals(setCount - 1, JFTOSetStore.instance().count());
    }

}
