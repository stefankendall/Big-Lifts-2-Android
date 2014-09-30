package com.stefankendall.BigLifts.views.fto.track.graph;

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
}
