package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Fragment;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOCustomAssistanceEditLiftsActivity extends FTOSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FTOSimpleCustomAssistanceEditLiftsFragment();
    }
}
