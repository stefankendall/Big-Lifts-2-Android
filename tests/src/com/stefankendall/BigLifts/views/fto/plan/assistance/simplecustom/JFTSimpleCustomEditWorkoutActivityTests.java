package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

public class JFTSimpleCustomEditWorkoutActivityTests extends BLTestCase {
    public void testFindsCustomWorkoutByLift() {
        FTOSimpleCustomEditWorkoutActivity activity = new FTOSimpleCustomEditWorkoutActivity();
        Assert.assertNotNull(activity.getWorkoutForLift((JFTOLift) JFTOLiftStore.instance().first()));
    }
}
