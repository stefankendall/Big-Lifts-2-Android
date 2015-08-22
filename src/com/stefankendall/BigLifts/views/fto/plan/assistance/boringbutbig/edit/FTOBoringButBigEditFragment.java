package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOBoringButBigEditFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOBoringButBigEditListAdapter(getActivity());
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
