package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.assistance.JFTOBoringButBigAssistance;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;

public class JFTOBoringButBigAssistanceTests extends BLTestCase {
    public void testDoesNotCrashWhenAWorkoutIsEmpty() {
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        ftoWorkout.workout.sets = Lists.newArrayList();
        new JFTOBoringButBigAssistance().setup();
        Assert.assertEquals(ftoWorkout.workout.sets.size(), 0);
    }

    public void testAddsBoringButBigSets() {
        new JFTOBoringButBigAssistance().setup();
        JFTOWorkout workoutInWeek1 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1);
        Assert.assertEquals(workoutInWeek1.workout.sets.size(), 11);
        JSet boringSet = workoutInWeek1.workout.sets.get(6);
        Assert.assertEquals(boringSet.percentage.compareTo(new BigDecimal(50)), 0);
        Assert.assertEquals(boringSet.reps.intValue(), 10);

        JFTOWorkout workoutInWeek4 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 4).get(0);
        Assert.assertEquals(workoutInWeek4.workout.sets.size(), 6);
    }

    public void testCanUseDifferentLifts() {
        JFTOBoringButBigLift bbbLift = (JFTOBoringButBigLift) JFTOBoringButBigLiftStore.instance().first();
        JFTOLift deadlift = (JFTOLift) JFTOLiftStore.instance().find("name", "Deadlift");
        bbbLift.boringLift = deadlift;
        new JFTOBoringButBigAssistance().setup();

        JFTOWorkout workoutInWeek1 = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(0);
        JSet boringSet = workoutInWeek1.workout.sets.get(6);
        Assert.assertEquals(boringSet.lift, deadlift);
    }

    public void testUsesBbbPercentage() {
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        boringButBig.percentage = new BigDecimal(60);
        new JFTOBoringButBigAssistance().setup();
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(0);
        List<JSet> sets = ftoWorkout.workout.sets;
        Assert.assertEquals(sets.get(sets.size() - 1).percentage.compareTo(new BigDecimal(60)), 0);
    }

    public void testIncrementsBbbPercentageIfChallengeOn() {
        JFTOBoringButBig bbb = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        bbb.percentage = new BigDecimal(60);
        bbb.threeMonthChallenge = true;
        new JFTOBoringButBigAssistance().cycleChange();

        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        Assert.assertEquals(boringButBig.percentage.compareTo(new BigDecimal(70)), 0);
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().first();
        List<JSet> sets = ftoWorkout.workout.sets;
        Assert.assertEquals(sets.get(sets.size() - 1).percentage.compareTo(new BigDecimal(70)), 0);
    }

    public void testDoesNotIncrementUnknownPercentageIfChallengeOn() {
        JFTOBoringButBig bbb = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        bbb.percentage = new BigDecimal(65);
        bbb.threeMonthChallenge = true;
        new JFTOBoringButBigAssistance().cycleChange();

        Assert.assertEquals(bbb.percentage, new BigDecimal(65));
    }

    //    public void testDoesNotRemoveAmrapFromCustom(){
//        JFTOVariantStore.instance().changeTo(FTO_VARIANT_CUSTOM];
//        NSArray workoutsWithAmrap = [self findWorkoutsWithAmrap];
//        STAssertEquals((int) [workoutsWithAmrap count], 12, @"");
//
//        [[JFTOBoringButBigAssistance new] setup];
//
//        workoutsWithAmrap = [self findWorkoutsWithAmrap];
//        STAssertEquals((int) [workoutsWithAmrap count], 12, @"");
//    }
//
    public void testDoesNotDuplicateAssistanceOnCycleChange() {
        new JFTOBoringButBigAssistance().setup();
        new JFTOBoringButBigAssistance().cycleChange();
        JModel model = JFTOWorkoutStore.instance().first();
        JFTOWorkout jftoWorkout = (JFTOWorkout) model;
        Assert.assertEquals(jftoWorkout.workout.assistanceSets().size(), 5);
    }
//
//    - (NSArray )findWorkoutsWithAmrap {
//        NSArray workoutsWithAmrap = [[[JFTOWorkoutStore instance] findAll] select:^BOOL(JFTOWorkout jftoWorkout) {
//            return [jftoWorkout.workout.sets detect:^BOOL(JSet set) {
//                return set.amrap;
//            }] != nil;
//        }];
//        return workoutsWithAmrap;
//    }
}
