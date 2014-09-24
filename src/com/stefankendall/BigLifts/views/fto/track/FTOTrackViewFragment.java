package com.stefankendall.BigLifts.views.fto.track;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import com.google.common.collect.ImmutableMap;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.views.fto.track.graph.FTOGraphActivity;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;
import java.util.Map;

public class FTOTrackViewFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.setListAdapter(new FTOTrackListAdapter(this.getActivity()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(this.getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if (info.position != 0) {
            MenuInflater inflater = this.getActivity().getMenuInflater();
            inflater.inflate(R.menu.one_button_context_menu, menu);
            MenuItem item1 = menu.findItem(R.id.context_button_1);
            item1.setTitle("Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        List<JWorkoutLog> workoutLogs = (List<JWorkoutLog>) JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
        int ONE_FOR_HEADER = 1;
        JWorkoutLogStore.instance().remove(workoutLogs.get(info.position - ONE_FOR_HEADER));
        SimpleListAdapter adapter = (SimpleListAdapter) this.getListAdapter();
        adapter.reload();
        return true;
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
            case R.id.menu_best_set:
            case R.id.menu_work_sets:
            case R.id.menu_all_sets:
                Map<Integer, JFTOSettings.LogState> idToNewState = ImmutableMap
                        .<Integer, JFTOSettings.LogState>builder()
                        .put(R.id.menu_best_set, JFTOSettings.LogState.kShowAmrap)
                        .put(R.id.menu_work_sets, JFTOSettings.LogState.kShowWorkSets)
                        .put(R.id.menu_all_sets, JFTOSettings.LogState.kShowAll)
                        .build();
                JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
                settings.logState = idToNewState.get(item.getItemId());
                ((FTOTrackListAdapter) this.getListAdapter()).reload();
                return true;
            case R.id.graph_button:
                startActivity(new Intent(this.getActivity(), FTOGraphActivity.class));
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