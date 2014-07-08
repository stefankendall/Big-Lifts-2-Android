package com.stefankendall.BigLifts.views.fto.lift;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOWorkoutListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOWorkoutListAdapter(this.getActivity()));
    }
}
