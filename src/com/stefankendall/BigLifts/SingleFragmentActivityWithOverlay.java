package com.stefankendall.BigLifts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public abstract class SingleFragmentActivityWithOverlay extends BLActivity {
    public Fragment fragment;
    protected ActionBarDrawerToggle drawerToggle;
    protected DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_with_overlay);

        setupFragments();

        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    protected void setupFragments() {
        this.fragment = getFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (this.fragment == null) {
            this.fragment = createFragment();
            Fragment overlay = createOverlay();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, this.fragment);
            if (overlay != null) {
                fragmentTransaction.add(R.id.overlayContainer, overlay);
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.drawerToggle != null && this.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract Fragment createFragment();

    protected abstract Fragment createOverlay();
}