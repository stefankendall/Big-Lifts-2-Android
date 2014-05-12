package com.stefankendall.BigLifts;

import android.app.Application;
import android.content.Context;
import com.stefankendall.BigLifts.data.DataLoader;

public class App extends Application {
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
}
