package com.stefankendall.BigLifts.views.fto.plan.assistance.fullcustom;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOFullCustomAssistanceActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Full Custom");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOFullCustomAssistanceFragment();
    }
}
