package com.stefankendall.BigLifts;

import android.app.Activity;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

public class BLActivity extends Activity {
    @Override
    protected void onPause() {
        super.onPause();
        BLJStoreManager.instance().writeStores();
    }
}
