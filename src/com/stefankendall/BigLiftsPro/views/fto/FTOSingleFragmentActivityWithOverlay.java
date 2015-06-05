package com.stefankendall.BigLiftsPro.views.fto;

import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.SingleFragmentActivityWithOverlay;
import com.stefankendall.BigLiftsPro.views.fto.nav.FTONavListAdapter;
import com.stefankendall.BigLiftsPro.views.nav.NavListItem;

public abstract class FTOSingleFragmentActivityWithOverlay extends SingleFragmentActivityWithOverlay {
    protected void setupNavigation() {
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(
                getResources().getDrawable(R.drawable.ic_navigation_drawer));

        final ListView listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new FTONavListAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavListItem item = (NavListItem) listView.getAdapter().getItem(i);
                item.getNavAction().run(FTOSingleFragmentActivityWithOverlay.this);
            }
        });

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerToggle = new ActionBarDrawerToggle(
                this,
                this.drawerLayout,
                R.drawable.ic_navigation_drawer,
                0,
                0
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                FTOSingleFragmentActivityWithOverlay.this.hideKeyboard();
            }
        };
        this.drawerLayout.setDrawerListener(this.drawerToggle);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = this.getCurrentFocus();
        if (v != null) {
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
