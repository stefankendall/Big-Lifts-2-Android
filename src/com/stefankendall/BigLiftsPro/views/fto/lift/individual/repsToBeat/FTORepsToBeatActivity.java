package com.stefankendall.BigLiftsPro.views.fto.lift.individual.repsToBeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.FTOIndividualWorkoutActivity;

public class FTORepsToBeatActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Reps to Beat");
    }

    @Override
    protected Fragment createFragment() {
        String uuid = getIntent().getStringExtra(FTOIndividualWorkoutActivity.FTO_WORKOUT_UUID);
        return FTORepsToBeatFragment.newInstance((JFTOWorkout) JFTOWorkoutStore.instance().find("uuid", uuid));
    }
}
