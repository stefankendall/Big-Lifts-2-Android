package com.stefankendall.BigLifts.views.startup;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;

import java.util.List;

public class StartupListAdapter extends SimpleListAdapter {
    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
                new StartupListItem("5/3/1"),
                new StartupListItem("What's next?")
        );
    }
}

