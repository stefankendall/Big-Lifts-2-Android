package com.stefankendall.BigLifts.views.fto.edit;

import android.app.ListFragment;
import android.os.Bundle;

public class FTOEditViewFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOEditViewListAdapter());
    }
}
