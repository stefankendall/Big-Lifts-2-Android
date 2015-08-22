package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.fto.*;
import junit.framework.Assert;

import java.util.List;

public class JFTOFullCustomAssistanceWorkoutStoreTests extends BLTestCase {
    public void testAddsMissingLifts() {
        List<JFTOFullCustomAssistanceWorkout> assistance = (List<JFTOFullCustomAssistanceWorkout>) JFTOFullCustomAssistanceWorkoutStore.instance().findAll();
        Assert.assertEquals(assistance.size(), 16);

        List<JFTOFullCustomAssistanceWorkout> bench = Lists.newArrayList(Iterables.filter(assistance, new Predicate<JFTOFullCustomAssistanceWorkout>() {
            @Override
            public boolean apply(JFTOFullCustomAssistanceWorkout jftoFullCustomAssistanceWorkout) {
                return jftoFullCustomAssistanceWorkout.mainLift.name.equals("Bench");
            }
        }));
        Assert.assertEquals(bench.size(), 4);
    }

    public void testRemovesMissingLifts() {
        JFTOLiftStore.instance().removeAtIndex(0);
        Assert.assertEquals(JFTOFullCustomAssistanceWorkoutStore.instance().count(), 12);
    }

    public void testAddsRemovesWeeksForSixWeek() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.sixWeekEnabled = true;
        JFTOWorkoutStore.instance().switchTemplate();

        Assert.assertEquals(JFTOFullCustomAssistanceWorkoutStore.instance().findAll().size(), 28);

        settings.sixWeekEnabled = false;
        JFTOWorkoutStore.instance().switchTemplate();

        Assert.assertEquals(JFTOFullCustomAssistanceWorkoutStore.instance().findAll().size(), 16);
    }

    public void testDoesNotRemoveFtoLiftSetsWhenCustomLiftsAreRemoved() {
        JFTOCustomAssistanceLift customLift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        customLift.name = "Lift";

        JFTOFullCustomAssistanceWorkout customWorkout = (JFTOFullCustomAssistanceWorkout) JFTOFullCustomAssistanceWorkoutStore.instance().first();

        JSet set1 = (JSet) JSetStore.instance().create();
        set1.lift = customLift;

        JSet set2 = (JSet) JSetStore.instance().create();
        set2.lift = (JLift) JFTOLiftStore.instance().first();

        JSet set3 = (JSet) JSetStore.instance().create();
        set3.lift = customLift;

        JSet set4 = (JSet) JSetStore.instance().create();
        set4.lift = (JLift) JFTOLiftStore.instance().last();

        customWorkout.workout.addSets(Lists.newArrayList(set1, set2, set3, set4));

        JFTOCustomAssistanceLiftStore.instance().removeAtIndex(0);
        Assert.assertEquals(customWorkout.workout.sets.size(), 2);
    }
}
