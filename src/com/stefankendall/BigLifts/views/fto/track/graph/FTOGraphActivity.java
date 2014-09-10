package com.stefankendall.BigLifts.views.fto.track.graph;

import android.app.Fragment;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivityIapOverlay;

public class FTOGraphActivity extends FTOSingleFragmentActivityIapOverlay {
    @Override
    protected Fragment createFragment() {
        return new FTOGraphFragment();
    }
}
