package com.stefankendall.BigLifts.data.stores.fto.plans.assistance;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;

import java.math.BigDecimal;
import java.util.List;

public class BoringButBigHelper {
    public static void addSetsToWorkout(JWorkout workout, JLift lift, boolean deload) {
        int sets = deload ? 3 : 5;
        workout.addSets(BoringButBigHelper.createBoringSets(sets, lift));
    }

    private static List<JSet> createBoringSets(int numberOfSets, JLift mainLift) {
        JFTOBoringButBigLift bbbLift = (JFTOBoringButBigLift) JFTOBoringButBigLiftStore.instance().find("mainLift", mainLift);

        List<JSet> sets = Lists.newArrayList();
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        BigDecimal percentage = boringButBig.percentage;
        for (int set = 0; set < numberOfSets; set++) {
            JFTOSet ftoSet = (JFTOSet) JFTOSetStore.instance().create();
            ftoSet.lift = bbbLift.boringLift;
            ftoSet.percentage = percentage;
            ftoSet.reps = 10;
            ftoSet.assistance = true;
            sets.add(ftoSet);
        }
        return sets;
    }
}
