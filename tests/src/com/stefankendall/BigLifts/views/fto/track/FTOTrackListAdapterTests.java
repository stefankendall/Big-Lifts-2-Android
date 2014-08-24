package com.stefankendall.BigLifts.views.fto.track;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

public class FTOTrackListAdapterTests extends BLTestCase {
    public void testReturnsRowForEachLog() {
        JWorkoutLogStore.instance().create();
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";

        FTOTrackListAdapter adapter = new FTOTrackListAdapter(new FTOTrackViewActivity());
        Assert.assertEquals(adapter.buildItems().size(), 1);
    }
}
