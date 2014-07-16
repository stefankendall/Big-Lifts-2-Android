package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class BarLoadingFragment extends ListFragmentWithControls implements RefreshingList {

    @Override
    public void removeListSelection() {
    }

    public void refresh() {
        this.setListAdapter(new BarLoadingListAdapter(this.getActivity(), this));
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new BarLoadingListAdapter(this.getActivity(), this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == this.getListAdapter().getCount() - 1) {
            this.addPlate();
        }
    }

    public void addPlate() {
        Intent intent = new Intent(this.getActivity(), AddPlateActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            this.setListAdapter(this.getListAdapterForControls());
        }
    }
}
