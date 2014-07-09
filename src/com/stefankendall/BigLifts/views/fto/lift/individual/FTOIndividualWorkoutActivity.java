package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOIndividualWorkoutActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Workout");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOIndividualWorkoutFragment();
    }
}
