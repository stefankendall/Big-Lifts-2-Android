package com.stefankendall.BigLifts.views.startup;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class StartupListAdapter extends SimpleListAdapter {
    public StartupListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new StartupListItem("5/3/1"),
                new StartupListItem("What's next?")
        );
    }

    public int getWhatsNextPosition() {
        return 1;
    }

    public int get531Position() {
        return 0;
    }
}

