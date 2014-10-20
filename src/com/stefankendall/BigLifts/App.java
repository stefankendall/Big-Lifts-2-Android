package com.stefankendall.BigLifts;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.crashlytics.android.Crashlytics;
import com.stefankendall.BigLifts.billing.util.IabService;
import com.stefankendall.BigLifts.data.DataLoader;

public class App extends Application {
    private static Context context;
    private static String PREFERENCE_FILE_NAME = "biglifts";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Crashlytics.start(this);
        DataLoader.load();
        IabService.getInstance().start();
    }

    public static Context getContext() {
        return context;
    }

    public static SharedPreferences.Editor getSharedPreferencesEditor() {
        if (editor == null) {
            SharedPreferences sharedPreferences = App.getContext().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return editor;
    }

    public static void commitChanges() {
        editor.commit();
        editor = null;
    }

    public static SharedPreferences getSharedPreferences() {
        if (preferences == null) {
            preferences = getContext().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return preferences;
    }
}
