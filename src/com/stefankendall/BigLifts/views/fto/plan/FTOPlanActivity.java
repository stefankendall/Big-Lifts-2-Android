package com.stefankendall.BigLifts.views.fto.plan;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOPlanActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Plan");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOPlanFragment();
    }
}
