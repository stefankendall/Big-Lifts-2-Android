package com.stefankendall.BigLifts.data.models;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import junit.framework.Assert;

import java.util.List;

public class JWorkoutTests extends BLTestCase {

    public void testDoesNotExceptionForAmrapSets() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        List<JSet> sets = workout.amrapSets();

        Assert.assertEquals(sets.size(), 0);
    }
}
