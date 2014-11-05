package com.stefankendall.BigLifts.views.fto.track.edit;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;

public class FTOEditLogFragment extends ListFragmentWithControls {
    private JWorkoutLog workoutLog;

    public static Fragment newInstance(JWorkoutLog workoutLog) {
        FTOEditLogFragment fragment = new FTOEditLogFragment();
        fragment.workoutLog = workoutLog;
        return fragment;
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        String uuid = savedInstanceState.getString("uuid");
        this.workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().find("uuid", uuid);
    }

    @Override
    protected void save(Bundle outState) {
        outState.putString("uuid", this.workoutLog.uuid);
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOEditLogListAdapter(getActivity(), this.workoutLog);
    }
}
