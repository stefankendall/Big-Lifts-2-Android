package com.stefankendall.BigLifts.views.fto.barloading;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class BarLoadingFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new BarLoadingListAdapter(this.getActivity());
    }
}
