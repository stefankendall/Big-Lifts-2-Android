package com.stefankendall.BigLifts.views.fto.track.graph;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FTOLogGraphTransformerTests extends BLTestCase {
    public void testLogToChartEntry() throws ParseException {
        JSetLog set = (JSetLog) JSetLogStore.instance().create();
        set.weight = new BigDecimal(200);
        set.reps = 3;
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.sets.add(set);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        workoutLog.date = df.parse("2013-01-12");

        Map chartEntry = new FTOLogGraphTransformer().logToChartEntry(workoutLog, set);
        Map expected = Maps.newHashMap();
        expected.put("weight", new BigDecimal("220.0"));
        Map dateMap = Maps.newHashMap();
        dateMap.put("year", 2013);
        dateMap.put("month", 1);
        dateMap.put("day", 12);
        expected.put("date", dateMap);

        Gson gson = new Gson();
        Assert.assertEquals(gson.toJson(chartEntry), gson.toJson(expected));
    }

    public void testHandlesNilWeightAndReps() {
        JSetLog set = (JSetLog) JSetLogStore.instance().create();
        set.weight = null;
        set.reps = 0;
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.sets.add(set);
        workoutLog.date = new Date();

        Map chartEntry = new FTOLogGraphTransformer().logToChartEntry(workoutLog, set);
        Assert.assertEquals(BigDecimal.ZERO.compareTo((BigDecimal) chartEntry.get("weight")), 0);
    }

    public void testLogEntriesFromChartAddsObjectIfMissing() {
        List<Map> chartData = Lists.newArrayList();
        Map dataPoint = Maps.newHashMap();
        dataPoint.put("name", "Press");
        dataPoint.put("data", Lists.newArrayList(Maps.newHashMap()));
        chartData.add(dataPoint);

        List<Map> deadLiftData = new FTOLogGraphTransformer().logEntriesFromChart(chartData, "Deadlift");
        Assert.assertEquals(chartData.size(), 2);
        Assert.assertNotNull(deadLiftData);

        List<Map> pressData = new FTOLogGraphTransformer().logEntriesFromChart(chartData, "Press");
        Assert.assertEquals(pressData.size(), 1);
    }

    public void testGeneratesOneLogPerWorkout() throws ParseException {
        JSetLog set1 = (JSetLog) JSetLogStore.instance().create();
        set1.name = "Deadlift";
        set1.weight = new BigDecimal(200);
        set1.reps = 3;

        JSetLog set2 = (JSetLog) JSetLogStore.instance().create();
        set1.name = "Deadlift";
        set1.weight = new BigDecimal(210);
        set1.reps = 2;

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        workoutLog.date = df.parse("2013-01-12");
        workoutLog.sets.addAll(Lists.newArrayList(set1, set2));

        List<Map> chartData = new FTOLogGraphTransformer().buildDataFromLog();
        List expected = Lists.newArrayList(
                ImmutableMap.builder()
                        .put("data", Lists.newArrayList(
                                ImmutableMap.builder().put("date", ImmutableMap.builder().put("day", 12).put("month", 1).put("year", 2013).build())
                                        .put("weight", new BigDecimal("224.0"))
                                        .build()
                        ))
                        .put("name", "Deadlift")
                        .build()
        );
        Gson gson = new Gson();
        Assert.assertEquals(gson.toJson(chartData), gson.toJson(expected, List.class));
    }


    public void testDoesNotIncludeDeload() throws ParseException {
        JSetLog set1 = (JSetLog) JSetLogStore.instance().create();
        set1.name = "Deadlift";
        set1.weight = new BigDecimal(200);
        set1.reps = 3;

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.deload = true;
        workoutLog.name = "5/3/1";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        workoutLog.date = df.parse("2013-01-12");

        workoutLog.addSet(set1);
        List<Map> chartData = new FTOLogGraphTransformer().buildDataFromLog();
        Assert.assertTrue(chartData.isEmpty());
    }

    public void testDoesNotLeaveBlankEntriesInTheLog() throws ParseException {
        JSetLog set1 = (JSetLog) JSetLogStore.instance().create();
        set1.name = null;
        set1.weight = new BigDecimal(200);
        set1.reps = 1;

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        workoutLog.date = df.parse("2013-01-12");

        workoutLog.addSet(set1);
        List<Map> chartData = new FTOLogGraphTransformer().buildDataFromLog();
        Assert.assertTrue(chartData.isEmpty());
    }
}
