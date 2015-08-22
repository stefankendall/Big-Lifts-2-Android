package com.stefankendall.BigLifts.views.fto.settings;

import android.support.v4.app.FragmentActivity;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class SettingsListAdapter extends SimpleListAdapter {
    private final FieldWatcher watcher;

    public SettingsListAdapter(FragmentActivity context, FieldWatcher watcher) {
        this.activity = context;
        this.watcher = watcher;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        LbsKgSwitchCell lbsKgSwitchCell = new LbsKgSwitchCell();
        lbsKgSwitchCell.setFieldWatcher(this.watcher);
        BarLoadingCell barLoadingCell = JPurchaseStore.instance().hasPurchasedEverything() ? new BarLoadingCell() : null;
        List<CustomListItem> items = Lists.newArrayList(
                lbsKgSwitchCell,
                new BarWeightCell(),
                barLoadingCell,
                new RoundToCell(),
                new RoundingTypeCell(),
                new KeepScreenOnCell()
        );
        return Lists.newArrayList(Iterables.filter(items, Predicates.notNull()));
    }
}
