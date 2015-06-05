package com.stefankendall.BigLiftsPro.data;

import com.stefankendall.BigLiftsPro.data.models.JSetLog;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.stores.JSetLogStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFaker {
    public static void generateEmptyLogData() throws ParseException {
        JSetLog set1 = (JSetLog) JSetLogStore.instance().create();
        set1.name = null;
        set1.weight = new BigDecimal(200);
        set1.reps = 1;

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        workoutLog.date = df.parse("2014-01-12");

        workoutLog.addSet(set1);
    }

    public static void generateLog() {
        Date now = new Date();
        for (int i = 0; i < 30; i++) {
            Date date = new Date(now.getTime() + 1000 * 60 * 60 * 24 * i * 3);
            JWorkoutLog log = JWorkoutLogStore.instance().create("5/3/1", date);
            BigDecimal weight = new BigDecimal(200).add(new BigDecimal(10).multiply(new BigDecimal(i)));
            JSetLog setLog = JSetLogStore.instance().create("Bench", weight, 1, false, false, false);
            log.addSet(setLog);
        }
    }
}
