package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.Fragment;
import android.os.Bundle;
import com.google.common.base.Strings;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSimpleCustomEditWorkoutActivity extends FTOSingleFragmentActivity {
    public static String EXTRA_FTO_LIFT_UUID = "ftoLiftUuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JFTOLift lift = getLiftFromIntent();
        this.setTitle(Strings.nullToEmpty(lift.name) + " Assistance");
    }

    private JFTOLift getLiftFromIntent() {
        return (JFTOLift) JFTOLiftStore.instance().find("uuid", getIntent().getStringExtra(EXTRA_FTO_LIFT_UUID));
    }

    protected JWorkout getWorkoutForLift(JFTOLift liftFromIntent) {
        JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().find("mainLift", liftFromIntent);
        return customAssistanceWorkout.workout;
    }

    @Override
    protected Fragment createFragment() {
        return FTOSimpleCustomEditWorkoutFragment.newInstance(this.getWorkoutForLift(this.getLiftFromIntent()));
    }
}
