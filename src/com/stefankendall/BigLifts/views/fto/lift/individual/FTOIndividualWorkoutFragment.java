package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FTOIndividualWorkoutFragment extends ListFragment {
    private JFTOWorkout ftoWorkout;

    public static FTOIndividualWorkoutFragment newInstance(JFTOWorkout ftoWorkout) {
        FTOIndividualWorkoutFragment fragment = new FTOIndividualWorkoutFragment();
        fragment.ftoWorkout = ftoWorkout;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.done_menu, menu);
        menu.findItem(R.id.done).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                FTOIndividualWorkoutFragment.this.onDoneTapped();
                return false;
            }
        });
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
