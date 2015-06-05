package com.stefankendall.BigLiftsPro.views.fto.track;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
