package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOSimpleCustomEditWorkoutFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity()));
    }
}
