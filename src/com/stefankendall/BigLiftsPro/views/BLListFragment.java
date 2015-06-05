package com.stefankendall.BigLiftsPro.views;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

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
