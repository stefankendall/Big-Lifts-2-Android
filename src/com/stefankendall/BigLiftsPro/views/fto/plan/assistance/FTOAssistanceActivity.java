package com.stefankendall.BigLiftsPro.views.fto.plan.assistance;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivityIapOverlay;

public class FTOAssistanceActivity extends FTOSingleFragmentActivityIapOverlay {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Assistance");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOAssistanceFragment();
    }
}
