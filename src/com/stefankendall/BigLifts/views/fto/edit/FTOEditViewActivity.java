package com.stefankendall.BigLifts.views.fto.edit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOEditViewActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Edit");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOEditViewFragment();
    }
}
