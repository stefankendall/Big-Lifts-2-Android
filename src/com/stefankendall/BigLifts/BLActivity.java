package com.stefankendall.BigLifts;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

public class BLActivity extends Activity {
    public static int RESULT_CLOSE_ALL = -1;
    protected boolean shouldSaveDataOnPause = true;
    public static boolean isTestRun = false;

    @Override
    protected void onPause() {
        super.onPause();
        if (shouldSaveDataOnPause && !isTestRun) {
            BLJStoreManager.instance().writeStores();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        if (settings.screenAlwaysOn) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
