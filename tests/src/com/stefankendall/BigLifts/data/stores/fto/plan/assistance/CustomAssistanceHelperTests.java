package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.assistance.CustomAssistanceHelper;
import junit.framework.Assert;

public class CustomAssistanceHelperTests extends BLTestCase {
    public void testAddsBothRegularAndFtoSetsToWorkout() {
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        workout.addSet((JSet) JSetStore.instance().create());
        workout.addSet((JFTOSet) JFTOSetStore.instance().create());
        workout.addSet((JSet) JSetStore.instance().create());
        workout.addSet((JFTOSet) JFTOSetStore.instance().create());
        CustomAssistanceHelper.addAssistanceToWorkout(jftoWorkout, workout);
        Assert.assertEquals(jftoWorkout.workout.assistanceSets().size(), 4);
    }
}
