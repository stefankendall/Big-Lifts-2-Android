package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOAssistanceFragment extends ListFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOAssistanceListAdapter(this.getActivity()));
    }
}
