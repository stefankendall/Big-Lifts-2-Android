package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
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
    protected ListAdapter getListAdapterForControls() {
        return new FTOSimpleCustomEditSetListAdapter(this, this.workout, this.set);
    }

    @Override
    public void fieldChanged() {
        this.set = this.workout.sets.get(setIndex);
        this.setListAdapter(this.getListAdapterForControls());
    }
}
