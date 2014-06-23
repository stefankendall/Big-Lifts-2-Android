package com.stefankendall.BigLifts.views.fto.nav;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.nav.NavAction;
import com.stefankendall.BigLifts.views.nav.NavListAdapter;
import com.stefankendall.BigLifts.views.nav.NavListItem;

import java.util.List;

public class FTONavListAdapter extends NavListAdapter {
    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
                new NavListItem("Lift", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Edit Lifts", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Plan Workout", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Bar Loading", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Track", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Estimate 1RM", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Settings", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Feedback", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                }),
                new NavListItem("Program", android.R.drawable.ic_menu_search, new NavAction() {
                    @Override
                    public void run(Activity context) {
                    }
                })
        );
    }
}
