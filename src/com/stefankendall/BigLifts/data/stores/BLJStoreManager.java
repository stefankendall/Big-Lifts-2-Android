package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;

import java.util.List;

public class BLJStoreManager {
    private List<BLJStore> allStores;

    public void loadStores() {
        for (BLJStore store : this.allStores) {
            store.load();
        }
    }

    private static BLJStoreManager instance;

    public static BLJStoreManager instance() {
        if (instance == null) {
            instance = new BLJStoreManager();
            instance.allStores = Lists.newArrayList(
                    JFTOLiftStore.instance()
            );
        }

        return instance;
    }

    public void resetAllStores() {
        for (BLJStore store : this.allStores) {
            store.empty();
            store.sync();
        }

        for (BLJStore store : this.allStores) {
            store.setupDefaults();
        }
    }
}
