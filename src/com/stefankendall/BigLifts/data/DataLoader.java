package com.stefankendall.BigLifts.data;

import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class DataLoader {
    public void load() {
        BLJStoreManager.instance().loadStores();
    }
}
