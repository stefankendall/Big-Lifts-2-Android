package com.stefankendall.BigLifts.views.fto.lift;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.BLActivity;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.BLListFragment;
import com.stefankendall.BigLifts.views.fto.lift.individual.FTOIndividualWorkoutActivity;
import com.stefankendall.BigLifts.views.fto.lift.nextcycle.FTONextCycleActivity;

public class FTOWorkoutListFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.setListAdapter(new FTOWorkoutListAdapter(this.getActivity()));
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Next Cycle");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getActivity(), FTONextCycleActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FTOWorkoutListAdapter adapter = (FTOWorkoutListAdapter) this.getListAdapter();
        JFTOWorkout jftoWorkout = adapter.workoutForPosition(position);
        if (jftoWorkout != null) {
            Intent individualWorkoutIntent = new Intent(this.getActivity(), FTOIndividualWorkoutActivity.class);
            individualWorkoutIntent.putExtra(FTOIndividualWorkoutActivity.FTO_WORKOUT_UUID, jftoWorkout.uuid);
            startActivityForResult(individualWorkoutIntent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == BLActivity.RESULT_CLOSE_ALL) {
            getActivity().setResult(BLActivity.RESULT_CLOSE_ALL);
            this.getActivity().finish();
        } else {
            this.setListAdapter(new FTOWorkoutListAdapter(this.getActivity()));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
