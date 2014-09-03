package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;
import java.util.Date;

public class FakeLogData {
    static void createData() {
        for (int i = 0; i < 9; i++) {
            Date d = new Date();
            d.setTime(d.getTime() - i * 24 * 60 * 60 * 1000);
            JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", d);

            workoutLog.addSet(JSetLogStore.instance().create("abc".charAt(i % 3) + "", new BigDecimal("10" + i), 3 + i % 2, false, false, false));
        }
    }
}
