package com.stefankendall.BigLifts;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.stefankendall.BigLifts.data.DataLoader;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        new DataLoader().load();
    }

    public static Context getContext() {
        return context;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
        BLJStoreManager.instance().writeStores();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
