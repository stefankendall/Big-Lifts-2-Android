package com.stefankendall.BigLifts.views.fto.track.edit;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOEditLogActivity extends FTOSingleFragmentActivity {
    public static String EXTRA_WORKOUT_LOG_UUID = "workoutLogUuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit Log");
    }

    @Override
    protected Fragment createFragment() {
        return FTOEditLogFragment.newInstance(getWorkoutLogFromIntent());
    }

    private JWorkoutLog getWorkoutLogFromIntent() {
        String workoutLogUuid = getIntent().getStringExtra(EXTRA_WORKOUT_LOG_UUID);
        return (JWorkoutLog) JWorkoutLogStore.instance().find("uuid", workoutLogUuid);
    }
}
