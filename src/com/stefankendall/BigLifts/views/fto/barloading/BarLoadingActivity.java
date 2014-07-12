package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class BarLoadingActivity extends FTOSingleFragmentActivity {

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
