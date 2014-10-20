package com.stefankendall.BigLifts.views.startup;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.SingleFragmentActivity;

public class StartupActivity extends SingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);

        shouldSaveDataOnPause = false;
    }

    @Override
    protected Fragment createFragment() {
        return new StartupFragment();
    }
}
