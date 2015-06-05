package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivityIapOverlay;

public class BarLoadingActivity extends FTOSingleFragmentActivityIapOverlay {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Bar Loading");
    }

    @Override
    protected Fragment createFragment() {
        return new BarLoadingFragment();
    }
}
