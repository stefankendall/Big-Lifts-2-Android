package com.stefankendall.BigLifts.data.models;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JWorkoutLogTests extends BLTestCase {
    public void testDoesNotExportAssistance() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        setLog1.name = "Bench";
        setLog1.weight = new BigDecimal("100");
        setLog1.reps = 5;
        setLog1.warmup = false;
        setLog1.assistance = false;
        setLog1.amrap = false;

        JSetLog setLog2 = (JSetLog) JSetLogStore.instance().create();
        setLog2.name = "Bench";
        setLog2.weight = new BigDecimal("100");
        setLog2.reps = 10;
        setLog2.warmup = true;
        setLog2.assistance = true;
        setLog2.amrap = false;

        workoutLog.addSet(setLog1);
        workoutLog.addSet(setLog2);

        Assert.assertEquals(workoutLog.bestSet(), setLog1);
    }

    public void testSetLogsCascadeDelete() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        workoutLog.addSet(setLog1);
        JWorkoutLogStore.instance().remove(workoutLog);
        Assert.assertEquals(JSetLogStore.instance().count(), 0);
    }

    public void testSerializesSetsAsUuids() {
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        workoutLog.addSet(setLog1);

        List<String> serialized = JWorkoutLogStore.instance().serialize();
        String workoutLogJson = serialized.get(0);

        Map deserialized = new Gson().fromJson(workoutLogJson, Map.class);
        List<String> setUuids = (List<String>) deserialized.get("sets");
        Assert.assertEquals(setUuids.get(0), setLog1.uuid);
    }

    public void testDeserializesUuidsAsSets() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        JSetLog setLog = (JSetLog) JSetLogStore.instance().create();
        setLog.name = "Bench";
        setLog.weight = new BigDecimal("100");
        setLog.reps = 5;
        workoutLog.addSet(setLog);

        String json = JWorkoutLogStore.instance().serializedAsJson();
        JWorkoutLog deserializedWorkoutLog = (JWorkoutLog) JWorkoutLogStore.instance().deserialize(json).get(0);
        Assert.assertEquals(deserializedWorkoutLog.sets.size(), 1);
        Assert.assertEquals(deserializedWorkoutLog.sets.get(0), setLog);
    }

    public void testSerializesAndDeserializesSets() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        setLog1.name = "Bench";
        setLog1.weight = new BigDecimal("100");
        JSetLog setLog2 = (JSetLog) JSetLogStore.instance().create();
        setLog2.name = "Bench";
        setLog2.weight = new BigDecimal("120");

        workoutLog.addSet(setLog1);
        workoutLog.addSet(setLog2);

        JWorkoutLogStore.instance().sync();
        App.commitChanges();

        JWorkoutLogStore.instance().load();

        JWorkoutLog syncedLog = (JWorkoutLog) JWorkoutLogStore.instance().first();
        Assert.assertEquals(syncedLog.sets.size(), 2);

        JSetLog syncedLog1 = syncedLog.sets.get(0);
        JSetLog syncedLog2 = syncedLog.sets.get(1);
        Assert.assertEquals(syncedLog1.name, "Bench");
        Assert.assertEquals(syncedLog1.weight, new BigDecimal("100"));

        Assert.assertEquals(syncedLog2.name, "Bench");
        Assert.assertEquals(syncedLog2.weight, new BigDecimal("120"));
    }

    public void testWorkSets() {
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        JSetLog warmup = (JSetLog) JSetLogStore.instance().create();
        warmup.warmup = true;
        JSetLog workout = (JSetLog) JSetLogStore.instance().create();
        workout.warmup = false;

        workoutLog.sets.addAll(Lists.newArrayList(warmup, workout));
        Assert.assertEquals(workoutLog.workSets().size(), 1);
    }

    public void testFindsBestSetFromWorkoutLog() {
        JSetLog set1 = (JSetLog) JSetLogStore.instance().create();
        set1.weight = new BigDecimal("200");
        set1.reps = 3;

        JSetLog set2 = (JSetLog) JSetLogStore.instance().create();
        set2.weight = new BigDecimal("220");
        set2.reps = 2;

        JSetLog set3 = (JSetLog) JSetLogStore.instance().create();
        set3.weight = new BigDecimal("200");
        set3.reps = 4;

        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.sets.addAll(Lists.newArrayList(set1, set2, set3));

        Assert.assertEquals(workoutLog.bestSet(), set2);
    }
}
