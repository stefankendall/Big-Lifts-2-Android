package com.stefankendall.BigLiftsPro.views.fto.plan;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
