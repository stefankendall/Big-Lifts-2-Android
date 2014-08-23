package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import junit.framework.Assert;

import java.util.List;

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

    public void testDoesNotLeakWorkouts() {
        int workoutCount = JWorkoutStore.instance().count();
        Assert.assertTrue(workoutCount > 0);
        JFTOWorkoutStore.instance().restoreTemplate();
        Assert.assertEquals(workoutCount, JWorkoutStore.instance().count());
    }

    public void testCreatesSixWeekDataProperly() {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        jftoSettings.sixWeekEnabled = true;
        JFTOWorkoutStore.instance().switchTemplate();
        Assert.assertEquals(JFTOWorkoutStore.instance().count(), 28);
        List<JFTOWorkout> week6Workouts = (List<JFTOWorkout>) JFTOWorkoutStore.instance().findAllWhere("week", 6);
        Assert.assertEquals(week6Workouts.size(), 4);
        Assert.assertTrue(JFTOWorkoutStore.instance().unique("week").contains(6));
        Assert.assertEquals(JFTOWorkoutStore.instance().unique("week").size(), 7);
    }

    public void testDefaultWorkoutsHaveWarmup() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        Assert.assertEquals(jftoWorkout.workout.sets.size(), 6);
    }

    public void testFindsWorkoutsByUuid() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        Assert.assertNotNull(JFTOWorkoutStore.instance().find("uuid", jftoWorkout.uuid));
    }
}
