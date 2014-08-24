package com.stefankendall.BigLifts;

import android.util.Log;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class TestDataLoader {
    public void reset() {
        BLJStoreManager.loaded = true;
        BLJStoreManager.instance().resetAllStores();
    }
}
