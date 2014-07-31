package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import junit.framework.Assert;

public class FTOIndividualWorkoutListAdapterTests extends BLTestCase {
    public void testBuildsHeadersAndSetsForWarmupAndWorksets() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().atIndex(0);
        FTOIndividualWorkoutListAdapter adapter = new FTOIndividualWorkoutListAdapter(null, jftoWorkout);
        int WARMUP_HEADER = 1;
        int WARMUP_SETS = 3;
        int WORK_SETS_HEADER = 1;
        int WORK_SETS = 3;

        Assert.assertEquals(adapter.getCount(), WARMUP_HEADER + WARMUP_SETS + WORK_SETS_HEADER + WORK_SETS);
    }
}
