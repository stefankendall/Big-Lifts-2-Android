package com.stefankendall.BigLifts.views.fto.onerep;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class OneRepMaxActivity extends FTOSingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Estimate One Rep Max");
    }

    @Override
    protected Fragment createFragment() {
        return new OneRepMaxFragment();
    }
}
