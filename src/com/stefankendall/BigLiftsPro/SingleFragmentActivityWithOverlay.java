package com.stefankendall.BigLiftsPro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import com.stefankendall.BigLiftsPro.billing.util.IabService;

public abstract class SingleFragmentActivityWithOverlay extends BLActivity {
    public Fragment fragment;
    protected ActionBarDrawerToggle drawerToggle;
    protected DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_with_overlay);

        setupFragments();

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void setupFragments() {
        this.fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (this.fragment == null) {
            this.fragment = createFragment();
            Fragment overlay = createOverlay();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, this.fragment);
            if (overlay != null) {
                fragmentTransaction.add(R.id.overlayContainer, overlay);
            }
            fragmentTransaction.commitAllowingStateLoss();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (IabService.getInstance().iabHelper != null && !IabService.getInstance().iabHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected abstract Fragment createFragment();

    protected abstract Fragment createOverlay();
}