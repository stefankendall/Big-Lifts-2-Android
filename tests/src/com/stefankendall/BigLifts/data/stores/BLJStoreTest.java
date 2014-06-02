package com.stefankendall.BigLifts.data.stores;

import android.test.AndroidTestCase;
import android.util.Log;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;

public class BLJStoreTest extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JFTOLiftStore.instance().empty();
    }

    public void testCreate() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        Assert.assertNotNull(lift);
    }

    public void testCount() {
        JFTOLiftStore.instance().create();
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

        List<JModel> all = JFTOLiftStore.instance().findAll();
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
}