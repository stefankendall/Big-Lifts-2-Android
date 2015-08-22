package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOCustomAssistanceEditLiftActivity extends FTOSingleFragmentActivity {
    public static String EXTRA_CUSTOM_LIFT = "extraCustomLift";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Lift");
    }

    @Override
    protected Fragment createFragment() {
        String uuid = getIntent().getStringExtra(EXTRA_CUSTOM_LIFT);
        return FTOCustomAssistanceEditLiftFragment.newInstance(
                (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().find("uuid", uuid));
    }
}
