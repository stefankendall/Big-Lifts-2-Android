package com.stefankendall.BigLiftsPro.views.fto.onerep;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLiftsPro.views.ListFragmentWithControls;

public class OneRepMaxFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new OneRepMaxListAdapter(getActivity());
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
