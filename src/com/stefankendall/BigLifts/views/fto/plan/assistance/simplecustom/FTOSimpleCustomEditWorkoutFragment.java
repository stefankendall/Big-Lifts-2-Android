package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset.FTOSimpleCustomEditSetActivity;

public class FTOSimpleCustomEditWorkoutFragment extends ListFragment {
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position == this.getListAdapter().getCount() - 1) {
            Intent intent = new Intent(getActivity(), FTOSimpleCustomEditSetActivity.class);
            intent.putExtra(FTOSimpleCustomEditSetActivity.EXTRA_WORKOUT_UUID, this.workout.uuid);

            JSet newSet = (JSet) JSetStore.instance().create();
            this.workout.addSet(newSet);

            intent.putExtra(FTOSimpleCustomEditSetActivity.EXTRA_SET_UUID, newSet.uuid);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListAdapter(new FTOSimpleCustomEditWorkoutListAdapter(getActivity(), workout));
    }
}
