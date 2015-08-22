package com.stefankendall.BigLifts.views.startup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.SingleFragmentActivity;

public class StartupActivity extends SingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        shouldSaveDataOnPause = false;
    }

    @Override
    protected Fragment createFragment() {
        return new StartupFragment();
    }
}
