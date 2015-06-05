package com.stefankendall.BigLiftsPro.views.fto.settings;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

public class SettingsActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Settings");
    }

    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }
}
