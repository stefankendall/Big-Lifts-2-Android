package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.billing.util.IabService;
import com.stefankendall.BigLiftsPro.billing.util.Inventory;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JPurchase;

import java.util.List;

public class JPurchaseStore extends BLJStore {
    private Function<Void, Void> callback = Functions.identity();

    @Override
    public void setupDefaults() {
        this.create();
    }

    public static JPurchaseStore instance() {
        return (JPurchaseStore) BLJStore.instance(JPurchaseStore.class);
    }

    @Override
    protected void onLoad() {
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

    public void purchasedEverything(){
        JPurchase jPurchase = (JPurchase) this.first();
        jPurchase.hasPurchasedEverything = true;
        this.callback.apply(null);
    }

    public boolean hasPurchasedEverything() {
        return true;
    }

    public void whenPurchase(Function<Void, Void> callback) {
        this.callback = callback;
    }
}
