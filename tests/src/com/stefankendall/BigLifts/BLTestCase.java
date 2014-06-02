package com.stefankendall.BigLifts;

import android.test.AndroidTestCase;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class BLTestCase extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        BLJStoreManager.instance().resetAllStores();
    }
}
