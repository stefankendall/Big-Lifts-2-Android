package com.stefankendall.BigLifts.views.fto.track;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;

public class FTOTrackViewFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.setListAdapter(new FTOTrackListAdapter(this.getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fto_track_menu, menu);
        menu.findItem(R.id.menu_sort_date);
        menu.findItem(R.id.menu_sort_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_date:
                sortBy(TrackSort.DATE);
                return true;
            case R.id.menu_sort_name:
                sortBy(TrackSort.NAME);
                return true;
            case R.id.graph_button:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortBy(TrackSort type) {
        FTOTrackListAdapter adapter = (FTOTrackListAdapter) this.getListAdapter();
        adapter.sorting = type;
        adapter.reload();
    }
}