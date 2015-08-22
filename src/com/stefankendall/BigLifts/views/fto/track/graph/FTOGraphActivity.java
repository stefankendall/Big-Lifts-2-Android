package com.stefankendall.BigLifts.views.fto.track.graph;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivityIapOverlay;

public class FTOGraphActivity extends FTOSingleFragmentActivityIapOverlay {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Graph");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOGraphFragment();
    }
}
