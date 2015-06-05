package com.stefankendall.BigLiftsPro.data.stores.fto.plan.assistance;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSetStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.plans.assistance.CustomAssistanceHelper;
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
