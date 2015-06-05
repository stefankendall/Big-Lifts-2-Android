package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import junit.framework.Assert;

public class FTOCycleAdjusterTests extends BLTestCase {
    public void testDetectsCycleNeedingToAdjust() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout jftoWorkout = (JFTOWorkout) model;
            jftoWorkout.done = true;
        }

        Assert.assertTrue(new FTOCycleAdjustor().cycleNeedsToIncrement());
    }

    public void testDetectsCycleNotNeedingToAdjust() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout jftoWorkout = (JFTOWorkout) model;
            jftoWorkout.done = true;
        }
        JFTOWorkout jftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        jftoWorkout.done = false;
        Assert.assertFalse(new FTOCycleAdjustor().cycleNeedsToIncrement());
    }

    public void testMidCycleSixWeek() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.sixWeekEnabled = true;
        JFTOWorkoutStore.instance().switchTemplate();

        this.setupMidSixWeekLiftsDone();
        Assert.assertTrue(new FTOCycleAdjustor().shouldIncrementLifts());
    }

    public void testMidCycleSixWeekDoesntTriggerPastMid() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.sixWeekEnabled = true;
        JFTOWorkoutStore.instance().switchTemplate();

        this.setupMidSixWeekLiftsDone();
        JFTOWorkout firstWorkoutPastCycle = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 4).get(0);
        firstWorkoutPastCycle.done = true;
        Assert.assertFalse(new FTOCycleAdjustor().shouldIncrementLifts());
    }

    public void testMidCycleSixWeekDoesntTriggerForNonSixWeek() {
        this.setupMidSixWeekLiftsDone();
        Assert.assertFalse(new FTOCycleAdjustor().shouldIncrementLifts());
    }

    private void setupMidSixWeekLiftsDone() {
        for (int week = 1; week <= 3; week++) {
            for (JModel model : JFTOWorkoutStore.instance().findAllWhere("week", week)) {
                JFTOWorkout jftoWorkout = (JFTOWorkout) model;
                jftoWorkout.done = true;
            }
        }
    }
}
