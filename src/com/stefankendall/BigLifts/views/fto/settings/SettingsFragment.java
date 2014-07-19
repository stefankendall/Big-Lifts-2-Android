package com.stefankendall.BigLifts.views.fto.settings;

import android.util.Log;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;

public class SettingsFragment extends ListFragmentWithControls implements FieldWatcher {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new SettingsListAdapter(getActivity(), this);
    }

    @Override
    public void fieldChanged() {
        this.setListAdapter(this.getListAdapterForControls());
    }
}
