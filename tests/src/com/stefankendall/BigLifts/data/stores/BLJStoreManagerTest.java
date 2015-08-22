package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;
import junit.framework.Assert;

public class BLJStoreManagerTest extends BLTestCase {
    public void testFindsStoreForASubclassByUuid() {
        JLift lift = (JLift) JLiftStore.instance().create();
        JFTOLift ftoLift = (JFTOLift) JFTOLiftStore.instance().create();

        Assert.assertEquals(BLJStoreManager.instance().storeForModel(JLift.class, lift.uuid), JLiftStore.instance());
        Assert.assertEquals(BLJStoreManager.instance().storeForModel(JFTOLift.class, ftoLift.uuid), JFTOLiftStore.instance());

        JFTOSet ftoSet = (JFTOSet) JFTOSetStore.instance().create();
        Assert.assertEquals(BLJStoreManager.instance().storeForModel(JFTOSet.class, ftoSet.uuid), JFTOSetStore.instance());
    }
}
