package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.fullcustom;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
