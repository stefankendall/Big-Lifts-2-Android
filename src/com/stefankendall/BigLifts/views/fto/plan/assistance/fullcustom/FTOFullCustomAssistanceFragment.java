package com.stefankendall.BigLifts.views.fto.plan.assistance.fullcustom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.views.BLListFragment;
import com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.FTOSimpleCustomEditWorkoutActivity;
import com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts.FTOCustomAssistanceEditLiftsActivity;

public class FTOFullCustomAssistanceFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.setListAdapter(new FTOFullCustomAssistanceListAdapter(getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FTOFullCustomAssistanceListAdapter adapter = (FTOFullCustomAssistanceListAdapter) this.getListAdapter();
        JFTOFullCustomAssistanceWorkout jftoFullCustomAssistanceWorkout = adapter.workoutForPosition(position);
        if (jftoFullCustomAssistanceWorkout != null) {
            Intent intent = new Intent(getActivity(), FTOSimpleCustomEditWorkoutActivity.class);
            intent.putExtra(FTOSimpleCustomEditWorkoutActivity.EXTRA_WORKOUT_ID, jftoFullCustomAssistanceWorkout.workout.uuid);
            intent.putExtra(FTOSimpleCustomEditWorkoutActivity.EXTRA_FTO_LIFT_UUID, jftoFullCustomAssistanceWorkout.mainLift.uuid);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Edit Lifts");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this.getActivity(), FTOCustomAssistanceEditLiftsActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
