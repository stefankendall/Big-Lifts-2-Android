package com.stefankendall.BigLifts.views.fto.track;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOTrackViewFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOTrackListAdapter(this.getActivity()));
    }
}