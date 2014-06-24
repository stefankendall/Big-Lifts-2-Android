package com.stefankendall.BigLifts.views.fto;

import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.SingleFragmentActivity;
import com.stefankendall.BigLifts.views.fto.nav.FTONavListAdapter;
import com.stefankendall.BigLifts.views.nav.NavListItem;

public abstract class FTOSingleFragmentActivity extends SingleFragmentActivity {
    protected void setupNavigation() {
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setHomeAsUpIndicator(
                getResources().getDrawable(R.drawable.ic_navigation_drawer));

        final ListView listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new FTONavListAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavListItem item = (NavListItem) listView.getAdapter().getItem(i);
                item.getNavAction().run(FTOSingleFragmentActivity.this);
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
            }
        };
        this.drawerLayout.setDrawerListener(this.drawerToggle);
    }

    @Override
    abstract protected Fragment createFragment();
}
