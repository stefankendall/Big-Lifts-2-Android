package com.stefankendall.BigLifts.data.stores;

import android.test.AndroidTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

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

    }
}