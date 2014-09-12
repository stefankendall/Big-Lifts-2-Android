package com.stefankendall.BigLifts.billing.util;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JPurchase;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.views.SimpleDialog;

public class IabService {
    public static String EVERYTHING_SKU = "everything";

    private static IabService instance;
    private IabHelper iabHelper;
    private boolean successful;
    private Inventory inventory;

    public static synchronized IabService getInstance() {
        if (instance == null) {
            instance = new IabService();
        }
        return instance;
    }

    public void start() {
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2/QQ47bghojItDNBkt9ZVTQW+ZESzUlBLp8Ac7jcjBsEX1eUi5z9gvVVtM8COLXqgnLkY7hcMiVAF7HLaM5SKId5ID9DkKJ50icblK/jkD+f4g7t9GrUJVHBnl6c0WTwhWc8ktmgbYrB5PdMxen/l9IhdFU7ufA2ksIMBgr7Xu6H7Q2CzJt70eRNHzofmx2CrNbD65fM0M3xwlBJtJDHsM0IOczirdXJFWxt/l9U2Ohp4BKXzflG4WfqjO/slICuDDjsclPHtEMLwcdJMR7/4tqKv5hQXDE3hZt71lHE5taA3PNM6W9skr3lWuc3gt+UlGFj5886kdg5ZwqwkHhlBwIDAQAB";
        this.iabHelper = new IabHelper(App.getContext(), base64EncodedPublicKey);
        this.iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                successful = result.isSuccess();
                if (successful) {
                    IabService.this.queryForInventory();
                }
            }
        });
    }

    private void queryForInventory() {
        iabHelper.queryInventoryAsync(true, Lists.newArrayList("everything"), new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                IabService.this.inventory = inv;
            }
        });
    }

    public void getInventory(final Function<Inventory, Void> callback) {
        if (this.inventory != null) {
            callback.apply(inventory);
        } else {
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    IabService.getInstance().getInventory(callback);
                }
            }, 3000);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void purchaseEverything(final Activity fromActivity, final Function<Void, Void> successCallback) {
//        successfulPurchase(fromActivity);
        try {
            iabHelper.launchPurchaseFlow(fromActivity, IabService.EVERYTHING_SKU, 0, new IabHelper.OnIabPurchaseFinishedListener() {
                @Override
                public void onIabPurchaseFinished(IabResult result, Purchase info) {
                    if (result.isSuccess()) {
                        if (info.getSku().equals(EVERYTHING_SKU)) {
                            successfulPurchase(fromActivity);
                            successCallback.apply(null);
                        } else {
                        }
                    } else {
                        failureInPurchase(fromActivity);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void failureInPurchase(Activity fromActivity) {
        SimpleDialog.showDialog(fromActivity.getFragmentManager(), fromActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer),
                "Something went wrong trying to purchase...", "Send me feedback and try again later, maybe?");
    }

    private void successfulPurchase(Activity fromActivity) {
        JPurchaseStore.instance().purchasedEverything();
        SimpleDialog.showDialog(fromActivity.getFragmentManager(), fromActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer),
                "Thank you!", "I truly appreciate the support.");
    }
}
