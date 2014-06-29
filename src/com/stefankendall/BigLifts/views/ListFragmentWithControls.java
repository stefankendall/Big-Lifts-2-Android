package com.stefankendall.BigLifts.views;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.R;

public abstract class ListFragmentWithControls extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(this.getListAdapterForControls());
    }

    protected abstract ListAdapter getListAdapterForControls();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setSelector(R.drawable.not_selectable);
        getListView().setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
    }
}
