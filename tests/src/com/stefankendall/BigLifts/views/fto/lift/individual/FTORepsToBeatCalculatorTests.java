package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;

public class FTORepsToBeatCalculatorTests extends BLTestCase {
    public void testGivesRepsToBeatBasedOnTheMax() {
        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        squat.weight = new BigDecimal(200);

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat(squat, new BigDecimal(180));
        Assert.assertEquals(repsToBeat, 4);
    }

    public void testGivesRepsToBeatBasedOnTheLog() {
        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        squat.weight = new BigDecimal(200);

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog setLog = (JSetLog) JSetLogStore.instance().create();
        setLog.name = "Squat";
        setLog.reps = 5;
        setLog.weight = new BigDecimal(190);
        setLog.amrap = true;
        workoutLog.addSet(setLog);

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat(squat, new BigDecimal(180));
        Assert.assertEquals(repsToBeat, 7);
    }

    public void testDoesNotUseEnteredMaxesWhenConfigIsLogOnly() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.repsToBeatConfig = JFTOSettings.RepsToBeatConfig.kRepsToBeatLogOnly;

        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        squat.weight = new BigDecimal(200);

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog setLog = (JSetLog) JSetLogStore.instance().create();
        setLog.name = "Squat";
        setLog.reps = 1;
        setLog.weight = new BigDecimal(190);
        setLog.amrap = true;
        workoutLog.addSet(setLog);

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat(squat, new BigDecimal(180));
        Assert.assertEquals(repsToBeat, 2);
    }

    public void testReturns0WhenConfigIsLogOnlyAndNoLog() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.repsToBeatConfig = JFTOSettings.RepsToBeatConfig.kRepsToBeatLogOnly;

        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        squat.weight = new BigDecimal(200);

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog setLog = (JSetLog) JSetLogStore.instance().create();
        setLog.name = "Deadlift";
        setLog.reps = 1;
        setLog.weight = new BigDecimal(190);
        workoutLog.addSet(setLog);

        int repsToBeat = FTORepsToBeatCalculator.repsToBeat(squat, new BigDecimal(180));
        Assert.assertEquals(repsToBeat, 0);
    }

    public void testFindRepsToBeat() {
        int reps = FTORepsToBeatCalculator.findRepsToBeat(new BigDecimal(200), new BigDecimal(180));
        Assert.assertEquals(reps, 4);
    }

    public void testUsesWorksetsForFindingMatchingLogs() {
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog workSetLog = (JSetLog) JSetLogStore.instance().create();
        workSetLog.name = "Deadlift";
        workoutLog.addSet(workSetLog);

        JSetLog assistance = JSetLogStore.instance().create("Press", new BigDecimal(100), 3, false, true, false);
        workoutLog.addSet(assistance);

        List<JWorkoutLog> logs = FTORepsToBeatCalculator.logsForLift((JFTOLift) JFTOLiftStore.instance().find("name", "Deadlift"));
        Assert.assertEquals(logs.size(), 1);
    }

    public void testUsesHeaviestAmrapInLog() {
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog heaviestAmrap = (JSetLog) JSetLogStore.instance().create();
        heaviestAmrap.name = "Deadlift";
        heaviestAmrap.weight = new BigDecimal(200);
        heaviestAmrap.reps = 1;
        heaviestAmrap.amrap = true;
        workoutLog.addSet(heaviestAmrap);

        JSetLog lastSet = (JSetLog) JSetLogStore.instance().create();
        lastSet.name = "Deadlift";
        lastSet.weight = new BigDecimal(100);
        lastSet.reps = 1;
        workoutLog.addSet(lastSet);
        BigDecimal max = FTORepsToBeatCalculator.findLogMax(JFTOLiftStore.instance().find("name", "Deadlift"));
        Assert.assertEquals(max, new BigDecimal(200));
    }

    public void testFindLogMaxForLiftUsesWorkSets() {
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        JSetLog workSetLog = (JSetLog) JSetLogStore.instance().create();
        workSetLog.reps = 1;
        workSetLog.name = "Deadlift";
        workSetLog.weight = new BigDecimal(150);
        workSetLog.amrap = true;
        workoutLog.addSet(workSetLog);

        JSetLog assistance = JSetLogStore.instance().create("Deadlift", new BigDecimal(100), 3, false, true, false);
        workoutLog.addSet(assistance);

        BigDecimal max = FTORepsToBeatCalculator.findLogMax(JFTOLiftStore.instance().find("name", "Deadlift"));
        Assert.assertEquals(max, new BigDecimal(150));
    }
}
