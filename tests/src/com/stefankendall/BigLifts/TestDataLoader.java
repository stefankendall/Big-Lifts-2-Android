package com.stefankendall.BigLifts;

import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class TestDataLoader {
    public void reset() {
        BLJStoreManager.instance().resetAllStores();
    }
}
