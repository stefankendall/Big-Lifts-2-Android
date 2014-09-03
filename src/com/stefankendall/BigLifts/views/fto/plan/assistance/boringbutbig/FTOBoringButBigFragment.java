package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOBoringButBigFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOBoringButBigListAdapter(this.getActivity());
    }
}
