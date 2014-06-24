package com.stefankendall.BigLifts.views.startup;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;

import java.util.List;

public class StartupListAdapter extends SimpleListAdapter {
    public StartupListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
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

