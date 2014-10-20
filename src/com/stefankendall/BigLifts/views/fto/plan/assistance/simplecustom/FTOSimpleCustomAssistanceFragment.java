package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts.FTOCustomAssistanceEditLiftsActivity;

public class FTOSimpleCustomAssistanceFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOSimpleCustomAssistanceListAdapter(this.getActivity()));
        this.setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Custom Lifts");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this.getActivity(), FTOCustomAssistanceEditLiftsActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListAdapter(new FTOSimpleCustomAssistanceListAdapter(this.getActivity()));
    }
}
