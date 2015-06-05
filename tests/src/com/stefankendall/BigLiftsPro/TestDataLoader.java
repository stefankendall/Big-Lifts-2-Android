package com.stefankendall.BigLiftsPro;

import com.stefankendall.BigLiftsPro.data.stores.BLJStoreManager;

public class TestDataLoader {
    public void reset() {
        BLJStoreManager.loaded = true;
        BLJStoreManager.instance().resetAllStores();
    }
}
