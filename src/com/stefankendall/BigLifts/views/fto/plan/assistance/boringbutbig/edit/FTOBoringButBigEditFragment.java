package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.widget.ListAdapter;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOBoringButBigEditFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOBoringButBigEditListAdapter(getActivity());
    }
}
