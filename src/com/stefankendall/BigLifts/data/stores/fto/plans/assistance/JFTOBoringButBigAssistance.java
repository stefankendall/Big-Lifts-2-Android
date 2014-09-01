package com.stefankendall.BigLifts.data.stores.fto.plans.assistance;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;

import java.math.BigDecimal;
import java.util.List;

public class JFTOBoringButBigAssistance implements JFTOAssistanceProtocol {
    @Override
    public void setup() {
        this.addBoringSets();
    }

    private void addBoringSets() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout ftoWorkout = (JFTOWorkout) model;
            if (ftoWorkout.workout.sets.isEmpty()) {
                continue;
            }

            JFTOSet set = (JFTOSet) ftoWorkout.workout.sets.get(0);
            BoringButBigHelper.addSetsToWorkout(ftoWorkout.workout, set.lift, ftoWorkout.deload);
        }
    }

    @Override
    public void cycleChange() {
        JFTOBoringButBig bbb = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        if (bbb.threeMonthChallenge) {
            List<BigDecimal> percentages = Lists.newArrayList(new BigDecimal(50), new BigDecimal(60), new BigDecimal(70));
            int index = percentages.indexOf(bbb.percentage);
            if (index != -1) {
                bbb.percentage = percentages.get((index + 1) % percentages.size());
            }
        }

        this.removeExistingAssistance();
        this.setup();
    }

    private void removeExistingAssistance() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout jftoWorkout = (JFTOWorkout) model;
            for (JSet set : jftoWorkout.workout.assistanceSets()) {
                jftoWorkout.workout.removeSet(set);
            }
        }
    }
}
