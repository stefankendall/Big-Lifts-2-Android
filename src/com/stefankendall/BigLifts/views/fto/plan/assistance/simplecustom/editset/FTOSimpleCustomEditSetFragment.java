package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;

public class FTOSimpleCustomEditSetFragment extends ListFragmentWithControls implements FieldWatcher {
    private JWorkout workout;
    private JSet set;
    private int setIndex;

    public static FTOSimpleCustomEditSetFragment newInstance(JWorkout workout, JSet set) {
        FTOSimpleCustomEditSetFragment ftoSimpleCustomEditSetFragment = new FTOSimpleCustomEditSetFragment();
        ftoSimpleCustomEditSetFragment.workout = workout;
        ftoSimpleCustomEditSetFragment.set = set;
        ftoSimpleCustomEditSetFragment.setIndex = workout.sets.indexOf(set);
        return ftoSimpleCustomEditSetFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        String workoutUuid = savedInstanceState.getString("workoutUuid");
        String setUuid = savedInstanceState.getString("setUuid");
        this.setIndex = savedInstanceState.getInt("setIndex");
        this.set = (JSet) JSetStore.instance().find("uuid", setUuid);
        this.workout = (JWorkout) JWorkoutStore.instance().find("uuid", workoutUuid);
    }

    @Override
    protected void save(Bundle outState) {
        outState.putString("workoutUuid", workout.uuid);
        outState.putString("setUuid", set.uuid);
        outState.putInt("setIndex", setIndex);
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOSimpleCustomEditSetListAdapter(this, this.workout, this.set);
    }

    @Override
    public void fieldChanged() {
        this.set = this.workout.sets.get(setIndex);
        this.setListAdapter(this.getListAdapterForControls());
    }
}
