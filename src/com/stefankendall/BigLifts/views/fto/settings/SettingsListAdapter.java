package com.stefankendall.BigLifts.views.fto.settings;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class SettingsListAdapter extends SimpleListAdapter {
    public SettingsListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
                new LbsKgSwitchCell()
        );
    }
}
