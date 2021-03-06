package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import junit.framework.Assert;

public class FTOWorkoutChangeCacheTests extends BLTestCase {
    public void setUp() throws Exception {
        super.setUp();
        FTOWorkoutChangeCache.instance().clear();
    }

    public void testCreatesNewCacheEntryWhenRequestingCacheItem() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        FTOWorkoutChange change = FTOWorkoutChangeCache.instance().changeForWorkout(jftoWorkout);
        Assert.assertNotNull(change);
        Assert.assertEquals(change.workout, jftoWorkout);
        Assert.assertEquals(change.changesBySet.size(), 0);
        Assert.assertEquals(FTOWorkoutChangeCache.instance().ftoWorkoutChanges.size(), 1);
    }

    public void testCreatesSetChangeWhenRequested() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        SetChange change = FTOWorkoutChangeCache.instance().changeForWorkout(jftoWorkout, 0);
        Assert.assertNotNull(change);
    }

    public void testCanClearCache() {
        FTOWorkoutChangeCache.instance().changeForWorkout((JFTOWorkout) JFTOWorkoutStore.instance().first());
        FTOWorkoutChangeCache.instance().clear();
        Assert.assertEquals(FTOWorkoutChangeCache.instance().ftoWorkoutChanges.size(), 0);
    }

    public void testCanMarkSetsCompleted(){
        FTOWorkoutChangeCache.instance().toggleComplete(3);
        FTOWorkoutChangeCache.instance().toggleComplete(5);
        FTOWorkoutChangeCache.instance().toggleComplete(3);
        Assert.assertTrue(FTOWorkoutChangeCache.instance().isComplete(5));
        Assert.assertFalse(FTOWorkoutChangeCache.instance().isComplete(3));
    }
}
