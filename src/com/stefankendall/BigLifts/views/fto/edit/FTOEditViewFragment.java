package com.stefankendall.BigLifts.views.fto.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.edit.change.FTOEditLiftsViewActivity;

public class FTOEditViewFragment extends ListFragmentWithControls {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOEditViewListAdapter(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Change Lifts");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivityForResult(new Intent(this.getActivity(), FTOEditLiftsViewActivity.class), 0);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.setListAdapter(this.getListAdapterForControls());
    }
}
