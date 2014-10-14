package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import android.app.Fragment;
import android.os.Bundle;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSimpleCustomEditSetActivity extends FTOSingleFragmentActivity {
    public static String EXTRA_WORKOUT_UUID = "ftoWorkoutUuid";
    public static String EXTRA_SET_UUID = "ftoSetUuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Edit Set");
    }

    @Override
    protected Fragment createFragment() {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().find("uuid", getIntent().getStringExtra(EXTRA_WORKOUT_UUID));
        final String setUuid = getIntent().getStringExtra(EXTRA_SET_UUID);

        JSet set = Iterables.find(workout.sets, new Predicate<JSet>() {
            @Override
            public boolean apply(JSet jSet) {
                return jSet.uuid.equals(setUuid);
            }
        });

        return FTOSimpleCustomEditSetFragment.newInstance(workout, set);
    }
}
