package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOCustomAssistanceWorkoutStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLiftsPro.views.BLListFragment;
import com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts.FTOCustomAssistanceEditLiftsActivity;

public class FTOSimpleCustomAssistanceFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomAssistanceListAdapter(this.getActivity()));
        this.setHasOptionsMenu(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int index = position - 1;
        if (index >= 0) {
            JFTOLift jftoLift = (JFTOLift) JFTOLiftStore.instance().atIndex(index);
            JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) JFTOCustomAssistanceWorkoutStore.instance().find("mainLift", jftoLift);
            Intent intent = new Intent(getActivity(), FTOSimpleCustomEditWorkoutActivity.class);
            intent.putExtra(FTOSimpleCustomEditWorkoutActivity.EXTRA_FTO_LIFT_UUID, jftoLift.uuid);
            intent.putExtra(FTOSimpleCustomEditWorkoutActivity.EXTRA_WORKOUT_ID, customAssistanceWorkout.workout.uuid);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Custom Lifts");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this.getActivity(), FTOCustomAssistanceEditLiftsActivity.class);
        startActivity(intent);
        return true;
    }
}
