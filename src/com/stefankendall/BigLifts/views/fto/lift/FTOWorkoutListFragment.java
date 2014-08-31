package com.stefankendall.BigLifts.views.fto.lift;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.BLActivity;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.fto.lift.individual.FTOIndividualWorkoutActivity;

public class FTOWorkoutListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOWorkoutListAdapter(this.getActivity()));
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
