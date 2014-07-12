package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOIndividualWorkoutFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity()));
    }
}
