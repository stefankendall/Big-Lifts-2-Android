package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOCustomAssistanceEditLiftsActivity extends FTOSingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Custom Lifts");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOCustomAssistanceEditLiftsFragment();
    }
}
