package com.stefankendall.BigLiftsPro;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.crashlytics.android.Crashlytics;
import com.stefankendall.BigLiftsPro.data.DataLoader;

import java.util.concurrent.atomic.AtomicInteger;

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

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
