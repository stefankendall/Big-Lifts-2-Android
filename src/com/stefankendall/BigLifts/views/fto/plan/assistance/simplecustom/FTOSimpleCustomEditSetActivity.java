package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSimpleCustomEditSetActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Edit Set");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOSimpleCustomEditSetFragment();
    }
}
