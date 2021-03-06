package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.stores.JPlateStore;
import com.stefankendall.BigLiftsPro.views.ListFragmentWithControls;

import java.math.BigDecimal;

public class AddPlateFragment extends ListFragmentWithControls implements FieldWatcher {
    protected MenuItem saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        this.saveButton = menu.findItem(R.id.save);
        this.saveButton.setEnabled(false);
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new AddPlateListAdapter(this.getActivity(), this);
    }

    public void saveTapped() {
        AddPlateListAdapter adapter = (AddPlateListAdapter) this.getListAdapter();
        BigDecimal weight = adapter.weight.getValue();
        int count = adapter.count.getValue();
        JPlateStore.instance().createPlate(weight, count);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void fieldChanged() {
        AddPlateListAdapter adapter = (AddPlateListAdapter) this.getListAdapter();
        boolean shouldEnableSave = !adapter.weight.isEmpty() && !adapter.count.isEmpty();
        this.saveButton.setEnabled(shouldEnableSave);
    }
}
