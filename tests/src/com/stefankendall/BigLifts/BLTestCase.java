package com.stefankendall.BigLifts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.test.AndroidTestCase;
import com.stefankendall.BigLifts.data.DataLoader;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class BLTestCase extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();
    }
}
