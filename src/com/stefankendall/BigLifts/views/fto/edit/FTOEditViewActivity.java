package com.stefankendall.BigLifts.views.fto.edit;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FtoSingleFragmentActivity;

public class FTOEditViewActivity extends FtoSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
    }

    @Override
    protected Fragment createFragment() {
        return new FTOEditViewFragment();
    }
}
