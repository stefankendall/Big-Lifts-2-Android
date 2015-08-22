package com.stefankendall.BigLifts.views.fto.barloading;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.stefankendall.BigLifts.SingleFragmentActivity;

public class AddPlateActivity extends SingleFragmentActivity {

    private AddPlateFragment addPlateFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Bar Loading");
    }

    @Override
    protected Fragment createFragment() {
        addPlateFragment = new AddPlateFragment();
        return addPlateFragment;
    }

    public void saveTapped(MenuItem item) {
        addPlateFragment.saveTapped();
    }
}
