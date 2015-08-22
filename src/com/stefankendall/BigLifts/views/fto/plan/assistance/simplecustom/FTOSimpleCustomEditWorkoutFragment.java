package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.views.BLListFragment;
import com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset.FTOSimpleCustomEditSetActivity;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOSimpleCustomEditWorkoutFragment extends BLListFragment {
    private JWorkout workout;

    public static FTOSimpleCustomEditWorkoutFragment newInstance(JWorkout workout) {
        FTOSimpleCustomEditWorkoutFragment fragment = new FTOSimpleCustomEditWorkoutFragment();
        fragment.workout = workout;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity(), workout));
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        String uuid = savedInstanceState.getString("uuid");
        this.workout = (JWorkout) JWorkoutStore.instance().find("uuid", uuid);
    }

    @Override
    protected void save(Bundle outState) {
        outState.putString("uuid", this.workout.uuid);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(this.getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        JSet set = null;
        if (position == this.getListAdapter().getCount() - 1) {
            JSet newSet = (JSet) JSetStore.instance().create();
            this.workout.addSet(newSet);
            set = newSet;
        } else if (position > 0) {
            set = this.workout.sets.get(position - 1);
        }

        if (set != null) {
            Intent intent = new Intent(getActivity(), FTOSimpleCustomEditSetActivity.class);
            intent.putExtra(FTOSimpleCustomEditSetActivity.EXTRA_WORKOUT_UUID, this.workout.uuid);
            intent.putExtra(FTOSimpleCustomEditSetActivity.EXTRA_SET_UUID, set.uuid);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if (info.position != 0 && info.position != this.getListAdapter().getCount() - 1) {
            MenuInflater inflater = this.getActivity().getMenuInflater();
            inflater.inflate(R.menu.one_button_context_menu, menu);
            MenuItem item1 = menu.findItem(R.id.context_button_1);
            item1.setTitle("Remove");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int ONE_FOR_HEADER = 1;
        int position = info.position - ONE_FOR_HEADER;
        this.workout.removeSet(this.workout.sets.get(position));
        ((SimpleListAdapter) this.getListAdapter()).reload();
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity(), workout));
    }
}
