package com.stefankendall.BigLifts.views.fto.edit;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.ViewGroup;
import com.stefankendall.BigLifts.R;

public class FTOEditViewFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOEditViewListAdapter(getActivity()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setSelector(R.drawable.not_selectable);
        getListView().setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
    }
}
