package com.stefankendall.BigLifts.views.fto.track;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

import java.util.Date;
import java.util.List;

public class FTOTrackListAdapterTests extends BLTestCase {
    public void testReturnsRowForEachLog() {
        JWorkoutLogStore.instance().create();
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.name = "5/3/1";

        FTOTrackListAdapter adapter = new FTOTrackListAdapter(new FTOTrackViewActivity());
        Assert.assertEquals(adapter.buildItems().size(), 2);
    }

    public void testSortsLogByDate() {
        JWorkoutLog log1 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(0));
        log1.addSet(JSetLogStore.instance().create("X", null, 0, false, false, false));

        JWorkoutLog log2 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-2));
        log2.addSet(JSetLogStore.instance().create("A", null, 0, false, false, false));

        JWorkoutLog log3 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-5));
        log3.addSet(JSetLogStore.instance().create("B", null, 0, false, false, false));

        FTOTrackListAdapter adapter = new FTOTrackListAdapter();
        Assert.assertEquals(this.comparableLog(adapter.getLog()), this.comparableLog(Lists.newArrayList(log1, log2, log3)));
    }

    public void testSortsLogByName() {
        JWorkoutLog log1 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(0));
        log1.addSet(JSetLogStore.instance().create("X", null, 0, false, false, false));

        JWorkoutLog log2 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-1));
        log2.addSet(JSetLogStore.instance().create("A", null, 0, false, false, false));

        JWorkoutLog log3 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-2));
        log3.addSet(JSetLogStore.instance().create("B", null, 0, false, false, false));

        FTOTrackListAdapter adapter = new FTOTrackListAdapter();
        adapter.sorting = TrackSort.NAME;
        Assert.assertEquals(this.comparableLog(adapter.getLog()), this.comparableLog(Lists.newArrayList(log2, log3, log1)));
    }

    public void testSortsLogByNameAndThenDate() {
        JWorkoutLog log1 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-1));
        log1.addSet(JSetLogStore.instance().create("A", null, 0, false, false, false));

        JWorkoutLog log2 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(0));
        log2.addSet(JSetLogStore.instance().create("A", null, 0, false, false, false));

        JWorkoutLog log3 = JWorkoutLogStore.instance().create("5/3/1", daysFromToday(-2));
        log3.addSet(JSetLogStore.instance().create("A", null, 0, false, false, false));

        FTOTrackListAdapter adapter = new FTOTrackListAdapter();
        adapter.sorting = TrackSort.NAME;
        Assert.assertEquals(this.comparableLog(adapter.getLog()), this.comparableLog(Lists.newArrayList(log2, log1, log3)));
    }

    private String comparableLog(List<JWorkoutLog> log) {
        List<String> uuids = Lists.transform(log, new Function<JWorkoutLog, String>() {
            @Override
            public String apply(JWorkoutLog jWorkoutLog) {
                return jWorkoutLog.toString();
            }
        });
        return Joiner.on(",").join(uuids);
    }

    private Date daysFromToday(int days) {
        Date date = new Date();
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
}
