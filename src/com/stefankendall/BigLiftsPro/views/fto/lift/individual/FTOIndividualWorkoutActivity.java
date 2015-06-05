package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

public class FTOIndividualWorkoutActivity extends FTOSingleFragmentActivity {
    public static String FTO_WORKOUT_UUID = "ftoWorkoutUuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JFTOWorkout ftoWorkout = getFtoWorkoutFromIntent();
        setTitle(ftoWorkout.workout.sets.get(0).lift.name);
    }

    @Override
    protected Fragment createFragment() {
        return FTOIndividualWorkoutFragment.newInstance(getFtoWorkoutFromIntent());
    }

    private JFTOWorkout getFtoWorkoutFromIntent() {
        String uuid = getIntent().getStringExtra(FTO_WORKOUT_UUID);
        return (JFTOWorkout) JFTOWorkoutStore.instance().find("uuid", uuid);
    }
}
