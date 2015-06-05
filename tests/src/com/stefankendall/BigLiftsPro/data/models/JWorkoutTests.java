package com.stefankendall.BigLiftsPro.data.models;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutStore;
import junit.framework.Assert;

import java.util.List;

public class JWorkoutTests extends BLTestCase {

    public void testDoesNotExceptionForAmrapSets() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        List<JSet> sets = workout.amrapSets();

        Assert.assertEquals(sets.size(), 0);
    }
}
