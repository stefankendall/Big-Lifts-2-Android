package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

public class FTOSimpleCustomAssistanceActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Simple Custom");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOSimpleCustomAssistanceFragment();
    }
}
