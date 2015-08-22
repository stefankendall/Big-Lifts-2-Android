package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOBoringButBigEditActivity extends FTOSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FTOBoringButBigEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Lifts");
    }
}
