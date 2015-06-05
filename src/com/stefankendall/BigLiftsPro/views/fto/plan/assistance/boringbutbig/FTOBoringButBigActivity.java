package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
