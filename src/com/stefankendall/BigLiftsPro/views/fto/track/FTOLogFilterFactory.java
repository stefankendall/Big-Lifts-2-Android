package com.stefankendall.BigLiftsPro.views.fto.track;

import com.google.common.base.Predicate;
import com.stefankendall.BigLiftsPro.data.helpers.SetHelper;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;

public class FTOLogFilterFactory {
    public static Predicate<JSetLog> filterForLogState(JWorkoutLog workoutLog) {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        Predicate<JSetLog> filter;

        switch (jftoSettings.logState) {
            case kShowAmrap:
                final JSetLog heaviestAmrap = SetHelper.heaviestAmrapSetLog(workoutLog.workSets());
                filter = new Predicate<JSetLog>() {
                    @Override
                    public boolean apply(JSetLog jSetLog) {
                        return jSetLog == heaviestAmrap;
                    }
                };
                break;
            case kShowWorkSets:
                filter = new Predicate<JSetLog>() {
                    @Override
                    public boolean apply(JSetLog jSetLog) {
                        return !jSetLog.warmup && !jSetLog.assistance;
                    }
                };
                break;
            default:
                filter = new Predicate<JSetLog>() {
                    @Override
                    public boolean apply(JSetLog jSetLog) {
                        return true;
                    }
                };
        }
        return filter;
    }
}
