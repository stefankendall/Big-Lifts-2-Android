package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;

public class FTOSetChangeFormFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOSetChangeFormListAdapter(getActivity());
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        if (SetChange.instance().modifyingSet == null) {
            getActivity().finish();
        }
    }

    @Override
    protected void save(Bundle outState) {
    }
}
