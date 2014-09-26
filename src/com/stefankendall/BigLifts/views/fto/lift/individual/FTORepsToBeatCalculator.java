package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLifts.data.helpers.SetHelper;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;

import java.math.BigDecimal;
import java.util.List;

public class FTORepsToBeatCalculator {
    public static int repsToBeat(JFTOLift lift, BigDecimal weight) {
        BigDecimal max = lift.weight;
        BigDecimal logMax = FTORepsToBeatCalculator.findLogMax(lift);

        JFTOSettings ftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        if (ftoSettings.repsToBeatConfig == JFTOSettings.RepsToBeatConfig.kRepsToBeatLogOnly) {
            max = logMax;
        } else {
            max = logMax.compareTo(max) > 0 ? logMax : max;
        }

        if (max.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        } else {
            return FTORepsToBeatCalculator.findRepsToBeat(max, weight);
        }
    }

    public static int findRepsToBeat(BigDecimal targetWeight, BigDecimal weight) {
        int reps;
        for (reps = 1; reps < 30; reps++) {
            BigDecimal estimateMax = OneRepEstimator.estimate(weight, reps);
            if (estimateMax.compareTo(targetWeight) > 0) {
                break;
            }
        }

        return reps;
    }

    public static List<JWorkoutLog> logsForLift(final JFTOLift lift) {
        List<JWorkoutLog> logs = (List<JWorkoutLog>) JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
        return Lists.newArrayList(
                Iterables.filter(logs, new Predicate<JWorkoutLog>() {
                    @Override
                    public boolean apply(JWorkoutLog jWorkoutLog) {
                        JSetLog set = jWorkoutLog.workSets().get(jWorkoutLog.workSets().size() - 1);
                        return set.name.equals(lift.name);
                    }
                })
        );
    }

    public static BigDecimal findLogMax(final JFTOLift lift) {
        List<JWorkoutLog> ftoLogsForLift = FTORepsToBeatCalculator.logsForLift(lift);

        BigDecimal logMax = BigDecimal.ZERO;
        for (JWorkoutLog jworkoutLog : ftoLogsForLift) {
            JSetLog setLog = SetHelper.heaviestAmrapSetLog(jworkoutLog.workSets());
            BigDecimal logEstimate = OneRepEstimator.estimate(setLog.weight, setLog.reps);
            if (logEstimate.compareTo(logMax) > 0) {
                logMax = logEstimate;
            }
        }
        return logMax;
    }
}
