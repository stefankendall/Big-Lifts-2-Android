package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;

public class FTOSimpleCustomAssistanceFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomAssistanceListAdapter(this.getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int index = position - 1;
        if (index >= 0) {
            JFTOLift jftoLift = (JFTOLift) JFTOLiftStore.instance().atIndex(index);
            Intent intent = new Intent(getActivity(), FTOSimpleCustomEditWorkoutActivity.class);
            intent.putExtra(FTOSimpleCustomEditWorkoutActivity.EXTRA_FTO_LIFT_UUID, jftoLift.uuid);
            startActivity(intent);
        }
    }
}
