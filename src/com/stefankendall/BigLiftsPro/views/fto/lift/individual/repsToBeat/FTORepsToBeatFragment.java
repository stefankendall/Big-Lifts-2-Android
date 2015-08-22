package com.stefankendall.BigLiftsPro.views.fto.lift.individual.repsToBeat;

import android.os.Bundle;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.BLListFragment;

public class FTORepsToBeatFragment extends BLListFragment {
    private JFTOWorkout ftoWorkout;

    @Override
    protected void save(Bundle outState) {
        outState.putString("uuid", this.ftoWorkout.uuid);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        String uuid = savedInstanceState.getString("uuid");
        this.ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().find("uuid", uuid);
    }

    public static FTORepsToBeatFragment newInstance(JFTOWorkout ftoWorkout) {
        FTORepsToBeatFragment fragment = new FTORepsToBeatFragment();
        fragment.ftoWorkout = ftoWorkout;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTORepsToBeatListAdapter(this.getActivity(), this.ftoWorkout));
    }
}
