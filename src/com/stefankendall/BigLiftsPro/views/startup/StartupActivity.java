package com.stefankendall.BigLiftsPro.views.startup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.SingleFragmentActivity;

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
