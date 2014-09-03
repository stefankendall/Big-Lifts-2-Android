package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOBoringButBigActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Boring But Big");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOBoringButBigFragment();
    }
}
