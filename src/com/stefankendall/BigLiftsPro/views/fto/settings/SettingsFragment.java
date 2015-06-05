package com.stefankendall.BigLiftsPro.views.fto.settings;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLiftsPro.views.ListFragmentWithControls;
import com.stefankendall.BigLiftsPro.views.fto.barloading.FieldWatcher;

public class SettingsFragment extends ListFragmentWithControls implements FieldWatcher {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new SettingsListAdapter(getActivity(), this);
    }

    @Override
    public void fieldChanged() {
        this.setListAdapter(this.getListAdapterForControls());
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
