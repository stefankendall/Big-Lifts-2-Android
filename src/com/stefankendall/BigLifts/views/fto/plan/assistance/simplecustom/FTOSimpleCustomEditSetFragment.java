package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOSimpleCustomEditSetFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOSimpleCustomEditSetListAdapter(getActivity());
    }
}
