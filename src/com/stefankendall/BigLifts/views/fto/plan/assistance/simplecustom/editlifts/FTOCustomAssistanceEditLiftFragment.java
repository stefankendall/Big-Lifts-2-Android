package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Fragment;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOCustomAssistanceEditLiftFragment extends ListFragmentWithControls {
    private JFTOCustomAssistanceLift customAssistanceLift;

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOCustomAssistanceEditLiftAdapter(getActivity(), customAssistanceLift);
    }

    public static Fragment newInstance(JFTOCustomAssistanceLift lift) {
        FTOCustomAssistanceEditLiftFragment fragment = new FTOCustomAssistanceEditLiftFragment();
        fragment.customAssistanceLift = lift;
        return fragment;
    }
}
