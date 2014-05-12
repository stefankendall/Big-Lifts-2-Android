package com.stefankendall.BigLifts;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

public abstract class SingleFragmentActivity extends Activity {
    public Fragment fragment;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        setupFragment();

        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    protected void setupNavigation() {
//        getActionBar().setDisplayShowHomeEnabled(false);
//        getActionBar().setHomeAsUpIndicator(
//                getResources().getDrawable(R.drawable.ic_navigation_drawer));
//
//        final ListView listView = (ListView) findViewById(R.id.left_drawer);
//        listView.setAdapter(new NavListAdapter());
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                NavListItem item = (NavListItem) listView.getAdapter().getItem(i);
//                item.getNavAction().run(SingleFragmentActivity.this);
//            }
//        });
//
//        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        this.drawerToggle = new ActionBarDrawerToggle(
//                this,
//                this.drawerLayout,
//                R.drawable.ic_navigation_drawer,
//                0,
//                0
//        ) {
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        this.drawerLayout.setDrawerListener(this.drawerToggle);
    }

    private void setupFragment() {
        this.fragment = getFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (this.fragment == null) {
            this.fragment = createFragment();
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, this.fragment).commit();
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
}