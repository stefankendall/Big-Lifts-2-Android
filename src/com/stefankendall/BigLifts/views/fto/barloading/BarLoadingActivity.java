package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivityWithOverlay;

public class BarLoadingActivity extends FTOSingleFragmentActivityWithOverlay {

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

    @Override
    protected Fragment createOverlay() {
        return new IapOverlayFragment();
    }
}
