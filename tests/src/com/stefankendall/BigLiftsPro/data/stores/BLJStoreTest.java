package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.base.Predicate;
import com.google.gson.Gson;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeSet;

public class BLJStoreTest extends BLTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JFTOLiftStore.instance().empty();
    }

    public void testCreate() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        Assert.assertNotNull(lift);
    }

    public void testFindReturnsNullWhenNoMatches() {
        Assert.assertNull(JFTOLiftStore.instance().find("name", "notthere"));
    }

    public void testCount() {
        Assert.assertEquals(JFTOLiftStore.instance().count(), 0);
        JFTOLiftStore.instance().create();
        Assert.assertEquals(JFTOLiftStore.instance().count(), 1);
        JFTOLiftStore.instance().create();
        Assert.assertEquals(JFTOLiftStore.instance().count(), 2);
    }

    public void testEmpty() {
        JFTOLiftStore.instance().create();
        JFTOLiftStore.instance().empty();
        Assert.assertEquals(JFTOLiftStore.instance().count(), 0);
    }

    public void testFirstLast() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.order = 0;
        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.order = 2;
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.order = 1;

        Assert.assertEquals(JFTOLiftStore.instance().first(), lift1);
        Assert.assertEquals(JFTOLiftStore.instance().last(), lift3);
    }

    public void testFindByNameValue() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";

        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.name = "B";

        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.name = "C";

        Assert.assertEquals(JFTOLiftStore.instance().find("name", "B"), lift2);
    }

    public void testFindAll() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";

        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.name = "B";

        List<JFTOLift> all = (List<JFTOLift>) JFTOLiftStore.instance().findAll();
        Assert.assertEquals(all.size(), 2);
        Assert.assertEquals(all.get(0), lift1);
        Assert.assertEquals(all.get(1), lift2);
    }

    public void testSerialize() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";
        lift1.increment = new BigDecimal("5.5");
        lift1.usesBar = true;
        lift1.weight = new BigDecimal("100");
        lift1.order = 1;

        List<String> serialized = JFTOLiftStore.instance().serialize();
        Assert.assertEquals(serialized.size(), 1);
        Gson gson = new Gson();

        JFTOLift deserialized = gson.fromJson(serialized.get(0), JFTOLift.class);

        Assert.assertEquals(lift1.name, deserialized.name);
        Assert.assertEquals(lift1.increment, deserialized.increment);
        Assert.assertEquals(lift1.usesBar, deserialized.usesBar);
        Assert.assertEquals(lift1.weight, deserialized.weight);
        Assert.assertEquals(lift1.order, deserialized.order);
    }

    public void testRemoveAll() {
        JFTOLiftStore.instance().create();
        JFTOLiftStore.instance().create();
        JFTOLiftStore.instance().removeAll();

        Assert.assertEquals(JFTOLiftStore.instance().count(), 0);
    }

    public void testRemoveAtIndex() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        JFTOLiftStore.instance().removeAtIndex(1);

        Assert.assertEquals(JFTOLiftStore.instance().count(), 2);
        Assert.assertFalse(JFTOLiftStore.instance().findAll().contains(lift2));
    }

    public void testFindBy() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.name = "B";
        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.name = "C";

        JFTOLift found = (JFTOLift) JFTOLiftStore.instance().findBy(new Predicate<JModel>() {
            @Override
            public boolean apply(JModel model) {
                JFTOLift lift = (JFTOLift) model;
                return lift.name.equals("B");
            }
        });

        Assert.assertEquals(found, lift2);
    }

    public void testFindAllWhere() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.name = "B";
        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.name = "A";

        List<JFTOLift> found = (List<JFTOLift>) JFTOLiftStore.instance().findAllWhere("name", "A");
        Assert.assertEquals(found.size(), 2);
        Assert.assertEquals(found.get(0), lift1);
        Assert.assertEquals(found.get(1), lift3);
    }

    public void testFindAllWhereIntegers() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.order = 9;
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.order = 9;
        List<JFTOLift> found = (List<JFTOLift>) JFTOLiftStore.instance().findAllWhere("order", 9);
        Assert.assertEquals(found.size(), 2);
    }

    public void testMax() {
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.weight = new BigDecimal("100");
        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.weight = new BigDecimal("200");
        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.weight = new BigDecimal("150");

        Assert.assertEquals(JFTOLiftStore.instance().max("weight"), new BigDecimal("200"));
    }

    public void testUnique() {
        JFTOLiftStore.instance().empty();
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";

        JFTOLift lift2 = (JFTOLift) JFTOLiftStore.instance().create();
        lift2.name = "A";

        JFTOLift lift3 = (JFTOLift) JFTOLiftStore.instance().create();
        lift3.name = "B";

        TreeSet<? extends Comparable> unique = JFTOLiftStore.instance().unique("name");
        Assert.assertTrue(unique.contains("A"));
        Assert.assertTrue(unique.contains("B"));
    }

    public void testSync() {
        JFTOLiftStore.instance().empty();
        JFTOLift lift1 = (JFTOLift) JFTOLiftStore.instance().create();
        lift1.name = "A";
        lift1.increment = new BigDecimal("5.5");
        lift1.usesBar = true;
        lift1.weight = new BigDecimal("100");
        lift1.order = 1;
        JFTOLiftStore.instance().sync();
        App.commitChanges();

        JFTOLiftStore.instance().empty();
        JFTOLiftStore.instance().load();

        Assert.assertEquals(JFTOLiftStore.instance().count(), 1);
        JFTOLift syncedLift = (JFTOLift) JFTOLiftStore.instance().first();
        Assert.assertEquals(syncedLift.name, "A");
        Assert.assertEquals(syncedLift.increment, new BigDecimal("5.5"));
        Assert.assertEquals(syncedLift.usesBar, true);
        Assert.assertEquals(syncedLift.weight, new BigDecimal("100"));
    }

    public void testKeyNameForStore() {
        Assert.assertEquals(JFTOLiftStore.instance().keyNameForStore(), "JFTOLiftStore");
    }

    public void testDeserializeObjects() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        lift.order = 1;
        lift.name = "A";
        lift.usesBar = true;
        lift.weight = new BigDecimal("5.5");
        lift.increment = new BigDecimal("100");
        JFTOLift deserialized = (JFTOLift) JFTOLiftStore.instance().deserialize("[" + JFTOLiftStore.instance().serializeObject(lift) + "]").get(0);
        Assert.assertEquals(lift.order, deserialized.order);
        Assert.assertEquals(lift.name, deserialized.name);
        Assert.assertEquals(lift.usesBar, deserialized.usesBar);
        Assert.assertEquals(lift.weight, deserialized.weight);
        Assert.assertEquals(lift.increment, deserialized.increment);
    }

    public void testSerializesAssociations() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        JSet set = (JSet) JSetStore.instance().create();
        workout.addSet(set);

        String serialized = JWorkoutStore.instance().serializeObject(workout);
        Assert.assertTrue(serialized, serialized.contains(set.uuid));
        Assert.assertFalse(serialized, serialized.contains("optional"));
    }

    public void testDeserializesAssociations() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        JSet set = (JSet) JSetStore.instance().create();
        set.percentage = new BigDecimal("55");
        workout.addSet(set);

        String serialized = "[" + JWorkoutStore.instance().serializeObject(workout) + "]";
        JWorkout deserialized = (JWorkout) JWorkoutStore.instance().deserialize(serialized).get(0);
        Assert.assertEquals(deserialized.sets.size(), 1);
        JSet deserializedSet = deserialized.sets.get(0);
        Assert.assertEquals(deserializedSet.percentage, new BigDecimal("55"));
    }

}