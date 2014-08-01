package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.ListFragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;

public class FTOIndividualWorkoutFragment extends ListFragment {
    private JFTOWorkout ftoWorkout;

    public static FTOIndividualWorkoutFragment newInstance(JFTOWorkout ftoWorkout) {
        FTOIndividualWorkoutFragment fragment = new FTOIndividualWorkoutFragment();
        fragment.ftoWorkout = ftoWorkout;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
    }
}
