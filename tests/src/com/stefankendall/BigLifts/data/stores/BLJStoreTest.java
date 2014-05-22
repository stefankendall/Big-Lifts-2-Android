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
}
