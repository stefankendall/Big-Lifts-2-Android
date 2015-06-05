package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.stores.JSetLogStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;
import java.util.Date;

public class FakeLogData {
    public static void createData() {
        for (int i = 0; i < 9; i++) {
            Date d = new Date();
            d.setTime(d.getTime() - i * 24 * 60 * 60 * 1000);
            JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", d);

            String liftName = "abc".charAt(i % 3) + "";
            for (int warmup = 0; warmup < 3; warmup++) {
                workoutLog.addSet(JSetLogStore.instance().create(liftName, new BigDecimal("8" + i), 3 + i % 2, true, false, true));
            }

            for (int workset = 0; workset < 3; workset++) {
                workoutLog.addSet(JSetLogStore.instance().create(liftName, new BigDecimal("9" + i), 3 + i % 2, false, false, false));
            }

            workoutLog.addSet(JSetLogStore.instance().create(liftName, new BigDecimal("10" + i), 3 + i % 2, false, false, true));

            for (int assistance = 0; assistance < 5; assistance++) {
                workoutLog.addSet(JSetLogStore.instance().create(liftName + " asst", new BigDecimal("4" + i), 3 + i % 2, false, true, false));
            }
        }
    }
}
