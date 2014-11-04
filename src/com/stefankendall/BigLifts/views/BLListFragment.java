package com.stefankendall.BigLifts.views;

import android.app.ListFragment;
import android.os.Bundle;

public abstract class BLListFragment extends ListFragment {
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.save(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.restore(savedInstanceState);
        }
    }

    protected abstract void restore(Bundle savedInstanceState);

    protected abstract void save(Bundle outState);
}
