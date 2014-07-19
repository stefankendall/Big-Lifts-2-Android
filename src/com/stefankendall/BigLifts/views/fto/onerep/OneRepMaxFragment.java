package com.stefankendall.BigLifts.views.fto.onerep;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class OneRepMaxFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new OneRepMaxListAdapter(getActivity());
    }
}
