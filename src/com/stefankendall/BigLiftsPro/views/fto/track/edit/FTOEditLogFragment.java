package com.stefankendall.BigLiftsPro.views.fto.track.edit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.ListAdapter;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLiftsPro.views.ListFragmentWithControls;

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
