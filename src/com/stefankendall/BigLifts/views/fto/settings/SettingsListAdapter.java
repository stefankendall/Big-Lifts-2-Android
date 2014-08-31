package com.stefankendall.BigLifts.views.fto.settings;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class SettingsListAdapter extends SimpleListAdapter {
    private final FieldWatcher watcher;

    public SettingsListAdapter(Activity context, FieldWatcher watcher) {
        this.activity = context;
        this.watcher = watcher;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        LbsKgSwitchCell lbsKgSwitchCell = new LbsKgSwitchCell();
        lbsKgSwitchCell.setFieldWatcher(this.watcher);
        return Lists.newArrayList(
                lbsKgSwitchCell,
                new BarWeightCell(),
                new BarLoadingCell(),
                new RoundToCell(),
                new RoundingTypeCell(),
                new KeepScreenOnCell()
        );
    }
}
