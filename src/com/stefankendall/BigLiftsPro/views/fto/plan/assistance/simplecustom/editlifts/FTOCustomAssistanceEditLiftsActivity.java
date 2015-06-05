package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
