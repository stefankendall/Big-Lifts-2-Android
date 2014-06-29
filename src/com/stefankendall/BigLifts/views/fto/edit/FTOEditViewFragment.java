package com.stefankendall.BigLifts.views.fto.edit;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOEditViewFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOEditViewListAdapter(getActivity());
    }
}
