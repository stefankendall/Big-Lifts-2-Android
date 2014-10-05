package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOAssistanceStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import junit.framework.Assert;

public class FTOIndividualWorkoutListAdapterTests extends BLTestCase {
    public void testBuildsHeadersAndSetsForWarmupAndWorksets() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        FTOIndividualWorkoutListAdapter adapter = new FTOIndividualWorkoutListAdapter(null, jftoWorkout);
        int TOOLBAR = 1;
        int WARMUP_HEADER = 1;
        int WARMUP_SETS = 3;
        int WORK_SETS_HEADER = 1;
        int WORK_SETS = 3;

        Assert.assertEquals(adapter.getCount(), TOOLBAR + WARMUP_HEADER + WARMUP_SETS + WORK_SETS_HEADER + WORK_SETS);
    }

    public void testGetsSetRowFromPosition() {
        JFTOAssistanceStore.instance().changeTo(JFTOAssistance.BORING_BUT_BIG);
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        Assert.assertEquals(jftoWorkout.workout.assistanceSets().size(), 5);

        FTOIndividualWorkoutListAdapter adapter = new FTOIndividualWorkoutListAdapter(null, jftoWorkout);
        Assert.assertEquals(adapter.setNumberForPosition(0), -1);
        Assert.assertEquals(adapter.setNumberForPosition(1), -1);
        Assert.assertEquals(adapter.setNumberForPosition(2), 0);
        Assert.assertEquals(adapter.setNumberForPosition(3), 1);
        Assert.assertEquals(adapter.setNumberForPosition(4), 2);
        Assert.assertEquals(adapter.setNumberForPosition(5), -1);
        Assert.assertEquals(adapter.setNumberForPosition(6), 3);
        Assert.assertEquals(adapter.setNumberForPosition(7), 4);
        Assert.assertEquals(adapter.setNumberForPosition(8), 5);
        Assert.assertEquals(adapter.setNumberForPosition(9), -1);
        Assert.assertEquals(adapter.setNumberForPosition(10), 6);
    }
}
