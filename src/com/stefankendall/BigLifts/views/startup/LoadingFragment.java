package com.stefankendall.BigLifts.views.startup;

import android.os.Bundle;
import com.stefankendall.BigLifts.views.BLListFragment;

public class LoadingFragment extends BLListFragment {
    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
