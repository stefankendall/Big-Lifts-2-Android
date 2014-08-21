package com.stefankendall.BigLifts.data.stores;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;

import java.util.List;

public class BLJStoreManager {
    private List<? extends BLJStore> allStores;

    public void loadStores() {
        for (BLJStore store : this.allStores) {
            if (store != JVersionStore.instance()) {
                store.load();
            }
        }
    }

    private static BLJStoreManager instance;

    public synchronized static BLJStoreManager instance() {
        if (instance == null) {
            instance = new BLJStoreManager();
            instance.allStores = Lists.newArrayList(
                    JVersionStore.instance(),
                    JCurrentProgramStore.instance(),

                    JSetLogStore.instance(),
                    JWorkoutLogStore.instance(),

                    JSettingsStore.instance(),
                    JFTOSettingsStore.instance(),
                    JBarStore.instance(),
                    JPlateStore.instance(),

                    JFTOVariantStore.instance(),

                    JLiftStore.instance(),
                    JFTOLiftStore.instance(),

                    JSetStore.instance(),
                    JFTOSetStore.instance(),
                    JWorkoutStore.instance(),
                    JFTOWorkoutStore.instance()
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

    public BLJStore storeForModel(final Class klass, String uuid) {
        List<BLJStore> matchingStores = ImmutableList.copyOf(Iterables.filter(this.allStores, new Predicate<BLJStore>() {
            @Override
            public boolean apply(BLJStore bljStore) {
                return klass.isAssignableFrom(bljStore.modelClass());
            }
        }));
        if (matchingStores.size() == 1) {
            return matchingStores.get(0);
        }

        for (BLJStore store : matchingStores) {
            if (store.find("uuid", uuid) != null) {
                return store;
            }
        }
        return null;
    }

    public void writeStores() {
        for (BLJStore store : this.allStores) {
            store.sync();
        }
    }
}
