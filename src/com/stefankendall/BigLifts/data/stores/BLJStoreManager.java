package com.stefankendall.BigLifts.data.stores;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.data.stores.fto.*;

import java.util.List;

public class BLJStoreManager {
    private List<? extends BLJStore> allStores;

    public static boolean loaded = false;

    public void loadStores() {
        if (!BLJStoreManager.loaded) {
            BLJStoreManager.loaded = true;
            for (BLJStore store : this.allStores) {
                if (store != JVersionStore.instance()) {
                    store.load();
                }
            }
        }
    }

    private static BLJStoreManager instance;

    public synchronized static BLJStoreManager instance() {
        if (instance == null) {
            instance = new BLJStoreManager();
            instance.allStores = Lists.newArrayList(
                    JVersionStore.instance(),
                    JPurchaseStore.instance(),

                    JCurrentProgramStore.instance(),

                    JSetLogStore.instance(),
                    JWorkoutLogStore.instance(),

                    JSettingsStore.instance(),
                    JFTOSettingsStore.instance(),
                    JBarStore.instance(),
                    JPlateStore.instance(),

                    JFTOVariantStore.instance(),
                    JFTOAssistanceStore.instance(),
                    JFTOBoringButBigStore.instance(),

                    JLiftStore.instance(),
                    JFTOLiftStore.instance(),

                    JFTOBoringButBigLiftStore.instance(),

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
        App.commitChanges();
    }
}
