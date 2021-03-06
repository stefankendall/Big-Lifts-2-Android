package com.stefankendall.BigLiftsPro.views.startup;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

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

