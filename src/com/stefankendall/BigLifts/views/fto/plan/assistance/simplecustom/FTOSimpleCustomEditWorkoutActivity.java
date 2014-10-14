package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSimpleCustomEditWorkoutActivity extends FTOSingleFragmentActivity {
    public static String EXTRA_FTO_LIFT_UUID = "ftoLiftUuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Workout");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOSimpleCustomEditWorkoutFragment();
    }
}
