package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.app.Fragment;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;
import com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.FTOBoringButBigFragment;

public class FTOBoringButBigEditActivity extends FTOSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FTOBoringButBigEditFragment();
    }
}
