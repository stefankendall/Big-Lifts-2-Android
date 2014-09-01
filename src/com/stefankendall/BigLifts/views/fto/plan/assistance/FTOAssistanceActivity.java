package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOAssistanceActivity extends FTOSingleFragmentActivity {
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
