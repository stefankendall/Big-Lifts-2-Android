package com.stefankendall.BigLiftsPro.views.fto.onerep;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivityIapOverlay;

public class OneRepMaxActivity extends FTOSingleFragmentActivityIapOverlay {

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
