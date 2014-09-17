package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOSetChangeFormFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOSetChangeFormListAdapter(getActivity());
    }
}
