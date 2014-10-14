package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FTOSimpleCustomEditWorkoutFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position == this.getListAdapter().getCount() - 1) {
            startActivityForResult(new Intent(getActivity(), FTOSimpleCustomEditSetActivity.class), 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity()));
    }
}
