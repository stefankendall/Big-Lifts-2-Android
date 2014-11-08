package com.stefankendall.BigLifts.views.fto.plan.assistance.fullcustom;

import android.os.Bundle;
import com.stefankendall.BigLifts.views.BLListFragment;

public class FTOFullCustomAssistanceFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOFullCustomAssistanceListAdapter(getActivity()));
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
