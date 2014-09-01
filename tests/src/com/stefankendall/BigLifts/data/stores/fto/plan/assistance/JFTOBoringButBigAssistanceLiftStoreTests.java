package com.stefankendall.BigLifts.data.stores.fto.plan.assistance;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

import java.util.List;

public class JFTOBoringButBigAssistanceLiftStoreTests extends BLTestCase {
    public void testSetsUpDefaultLifts() {
        Assert.assertEquals(JFTOBoringButBigLiftStore.instance().count(), 4);
    }

    public void testAddsLiftsWhenFtoLiftsAreAdded() {
        JFTOLift newLift = (JFTOLift) JFTOLiftStore.instance().create();
        List<JFTOBoringButBigLift> allLifts = (List<JFTOBoringButBigLift>) JFTOBoringButBigLiftStore.instance().findAll();
        JFTOBoringButBigLift bbbLift = allLifts.get(allLifts.size() - 1);
        Assert.assertEquals(bbbLift.mainLift, newLift);
        Assert.assertEquals(bbbLift.boringLift, newLift);
    }

    public void testRemovesLiftsWhenFtoLiftsAreRemoved() {
        JFTOLiftStore.instance().removeAtIndex(0);
        Assert.assertEquals(JFTOBoringButBigLiftStore.instance().count(), 3);
    }

    public void testAdjustsExistingBoringLiftsWhenFtoLiftsRemoved() {
        JFTOBoringButBigLift lift = (JFTOBoringButBigLift) JFTOBoringButBigLiftStore.instance().first();
        JFTOLift mainLift = (JFTOLift) JFTOLiftStore.instance().first();
        JFTOLift boringLift = (JFTOLift) JFTOLiftStore.instance().last();
        lift.mainLift = mainLift;
        lift.boringLift = boringLift;
        JFTOLiftStore.instance().remove(boringLift);
        Assert.assertEquals(lift.boringLift, lift.mainLift);
    }
}
