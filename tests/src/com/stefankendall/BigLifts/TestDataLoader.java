package com.stefankendall.BigLifts;

import com.stefankendall.BigLifts.data.DataLoader;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class TestDataLoader {
    public void reset() {
        new DataLoader().load();
        BLJStoreManager.instance().resetAllStores();
    }
}
