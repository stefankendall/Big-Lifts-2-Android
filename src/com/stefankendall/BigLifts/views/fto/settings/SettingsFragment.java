package com.stefankendall.BigLifts.views.fto.settings;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class SettingsFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new SettingsListAdapter(getActivity());
    }
}
