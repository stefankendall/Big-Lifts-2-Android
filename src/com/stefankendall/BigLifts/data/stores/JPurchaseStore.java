package com.stefankendall.BigLifts.data.stores;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.billing.util.IabService;
import com.stefankendall.BigLifts.billing.util.Inventory;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JPurchase;

import java.util.List;

public class JPurchaseStore extends BLJStore {
    @Override
    public void setupDefaults() {
        this.create();
    }

    public static JPurchaseStore instance() {
        return (JPurchaseStore) BLJStore.instance(JPurchaseStore.class);
    }

    @Override
    protected void onLoad() {
        IabService.getInstance().getInventory(new Function<Inventory, Void>() {
            @Override
            public Void apply(Inventory inventory) {
                JPurchaseStore.this.updateInventory(inventory);
                return null;
            }
        });
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JPurchase.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public void updateInventory(Inventory inventory) {
        JPurchase jPurchase = (JPurchase) this.first();
        jPurchase.hasPurchasedEverything = inventory.hasPurchase(IabService.EVERYTHING_SKU);
    }
}