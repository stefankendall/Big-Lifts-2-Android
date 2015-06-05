package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListAdapter;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLiftsPro.views.ListFragmentWithControls;

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

    @Override
    protected void restore(Bundle savedInstanceState) {
        String uuid = savedInstanceState.getString("uuid");
        this.customAssistanceLift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().find("uuid", uuid);
    }

    @Override
    protected void save(Bundle outState) {
        outState.putString("uuid", this.customAssistanceLift.uuid);
    }
}
