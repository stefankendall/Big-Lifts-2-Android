package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.BLActivity;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.views.fto.lift.individual.change.FTOSetChangeFormActivity;
import com.stefankendall.BigLifts.views.fto.track.FTOTrackViewActivity;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FTOIndividualWorkoutFragment extends ListFragment {
    private JFTOWorkout ftoWorkout;
    private Integer tappedSetRow = null;
    public static int SET_CHANGE_REQUEST_CODE = 0;

    public static FTOIndividualWorkoutFragment newInstance(JFTOWorkout ftoWorkout) {
        FTOIndividualWorkoutFragment fragment = new FTOIndividualWorkoutFragment();
        assert ftoWorkout != null;
        assert ftoWorkout.workout != null;
        fragment.ftoWorkout = ftoWorkout;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.ftoWorkout == null){
            return;
        }

        setRetainInstance(true);
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.single_button, menu);
        MenuItem button = menu.findItem(R.id.button);
        if( this.ftoWorkout.done ){
            button.setTitle("Not Done");
            button.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    ftoWorkout.done = false;
                    getActivity().invalidateOptionsMenu();
                    return false;
                }
            });
        }
        else {
            button.setTitle("Done");

            button.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    FTOIndividualWorkoutFragment.this.onDoneTapped();
                    return false;
                }
            });
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FTOIndividualWorkoutListAdapter adapter = (FTOIndividualWorkoutListAdapter) this.getListAdapter();
        int setNumber = adapter.setNumberForPosition(position);
        if (setNumber >= 0) {
            this.tappedSetRow = setNumber;
            SetChange.reset();
            SetChange existingChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, this.tappedSetRow);
            SetChange.instance().weight = existingChange.weight;
            SetChange.instance().reps = existingChange.reps;
            SetChange.instance().modifyingSet = this.ftoWorkout.workout.sets.get(setNumber);
            Crashlytics.log("Modifying set number " + setNumber + " with workout:");
            Crashlytics.log(new Gson().toJson(this.ftoWorkout));
            Intent intent = new Intent(this.getActivity(), FTOSetChangeFormActivity.class);
            startActivityForResult(intent, SET_CHANGE_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SET_CHANGE_REQUEST_CODE) {
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, this.tappedSetRow);
            setChange.weight = SetChange.instance().weight;
            setChange.reps = SetChange.instance().reps;
            SimpleListAdapter adapter = (SimpleListAdapter) this.getListAdapter();
            adapter.reload();
        }
    }

    public void onDoneTapped() {
        this.ftoWorkout.done = true;
        this.logWorkout();

        FTOCycleAdjustor cycleAdjustor = new FTOCycleAdjustor();
        boolean willIncrement = cycleAdjustor.shouldIncrementLifts();
        cycleAdjustor.checkForCycleChange();
        if (willIncrement) {
//            [self performSegueWithIdentifier:@"ftoShowLiftIncrements" sender:self];
        } else {
            this.getActivity().setResult(BLActivity.RESULT_CLOSE_ALL);
            this.getActivity().finish();
            Intent i = new Intent(this.getActivity(), FTOTrackViewActivity.class);
            startActivity(i);
        }
    }

    protected void logWorkout() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());
        workoutLog.deload = this.ftoWorkout.deload;

        List<JSet> sets = this.ftoWorkout.workout.sets;
        for (int i = 0; i < sets.size(); i++) {
            JSet set = sets.get(i);
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, i);
            Integer reps = setChange.reps;
            if (reps != null && reps == 0) {
                continue;
            }
            BigDecimal weight = setChange.weight;

            JSetLog setLog = JSetLogStore.instance().createFromSet(set);
            if (reps != null) {
                setLog.reps = reps;
            }
            if (weight != null) {
                setLog.weight = weight;
            }

            workoutLog.addSet(setLog);
        }
        FTOWorkoutChangeCache.instance().clear();
    }
}
