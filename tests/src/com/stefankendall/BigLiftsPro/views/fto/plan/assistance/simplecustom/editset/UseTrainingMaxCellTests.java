package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSet;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutStore;
import com.stefankendall.BigLiftsPro.views.fto.barloading.FieldWatcher;
import junit.framework.Assert;

public class UseTrainingMaxCellTests extends BLTestCase implements FieldWatcher {
    public void testChangesJSetToJFTOSet() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        workout.addSet((JSet) JSetStore.instance().create());
        UseTrainingMaxCell cell = new UseTrainingMaxCell(this, workout, workout.sets.get(0));
        cell.valueChanged(true);

        Assert.assertTrue(workout.sets.get(0) instanceof JFTOSet);

        cell = new UseTrainingMaxCell(this, workout, workout.sets.get(0));
        cell.valueChanged(false);

        Assert.assertFalse(workout.sets.get(0) instanceof JFTOSet);
    }

    @Override
    public void fieldChanged() {
    }
}
