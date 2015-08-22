package com.stefankendall.BigLifts.views.fto.lift;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOWorkoutListActivity extends FTOSingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Lift & Week");

        shouldSaveDataOnPause = false;
    }

    @Override
    protected Fragment createFragment() {
        return new FTOWorkoutListFragment();
    }
}
