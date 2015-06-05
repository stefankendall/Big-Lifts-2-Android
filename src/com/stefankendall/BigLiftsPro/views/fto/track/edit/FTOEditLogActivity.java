package com.stefankendall.BigLiftsPro.views.fto.track.edit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
