package com.stefankendall.BigLiftsPro;

import android.test.AndroidTestCase;

public class BLTestCase extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        BLActivity.isTestRun = true;
        new TestDataLoader().reset();
    }
}
