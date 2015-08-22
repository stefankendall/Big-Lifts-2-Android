package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit.FTOBoringButBigEditActivity;

public class FTOBoringButBigFragment extends ListFragmentWithControls {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOBoringButBigListAdapter(this.getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Edit");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getActivity(), FTOBoringButBigEditActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }
}
