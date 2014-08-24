package com.stefankendall.BigLifts.views.fto.track;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOTrackViewActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Track");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOTrackViewFragment();
    }
}
