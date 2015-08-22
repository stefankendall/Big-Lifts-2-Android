package com.stefankendall.BigLifts.views.fto.track;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FTOLogFilterFactoryTests extends BLTestCase {
    public void testFiltersNoSetsForReturnAll() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.logState = JFTOSettings.LogState.kShowAll;

        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());

        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, true, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, true, true));

        List<JSetLog> filtered = Lists.newArrayList(Iterables.filter(workoutLog.sets, FTOLogFilterFactory.filterForLogState(workoutLog)));
        Assert.assertEquals(filtered.size(), 3);
    }

    public void testFiltersAssistanceAndWarmupForWorkSets() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.logState = JFTOSettings.LogState.kShowWorkSets;

        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());

        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, false, false));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, true, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, true, true));

        List<JSetLog> filtered = Lists.newArrayList(Iterables.filter(workoutLog.sets, FTOLogFilterFactory.filterForLogState(workoutLog)));
        Assert.assertEquals(filtered.size(), 2);
    }

    public void testFiltersAllButBestSet() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.logState = JFTOSettings.LogState.kShowAmrap;

        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, false, false));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, true, false, true));
        workoutLog.addSet(JSetLogStore.instance().create("A", new BigDecimal(100), 3, false, true, true));

        List<JSetLog> filtered = Lists.newArrayList(Iterables.filter(workoutLog.sets, FTOLogFilterFactory.filterForLogState(workoutLog)));
        Assert.assertEquals(filtered.size(), 1);
    }
}
