package com.stefankendall.BigLifts.views.fto.plan;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOPlanFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOPlanListAdapter(getActivity());
    }

    protected void removeListSelection() {
    }
}
