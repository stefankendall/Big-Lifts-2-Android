package com.stefankendall.BigLifts.views.fto.barloading;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class BarLoadingFragment extends ListFragmentWithControls implements RefreshingList {

    @Override
    public void removeListSelection() {
    }

    public void refresh() {
        this.setListAdapter(new BarLoadingListAdapter(this.getActivity(), this));
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new BarLoadingListAdapter(this.getActivity(), this);
    }
}
