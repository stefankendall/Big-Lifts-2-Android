package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class AddPlateListAdapter extends SimpleListAdapter {

    protected final FieldWatcher watcher;
    protected ParameterizedDecimalInputCell weight;
    protected ParameterizedIntegerInputCell count;

    public AddPlateListAdapter(Activity context, FieldWatcher watcher) {
        this.activity = context;
        this.watcher = watcher;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        this.weight = new ParameterizedDecimalInputCell("Weight", "");
        this.count = new ParameterizedIntegerInputCell("Count", "");
        this.weight.setFieldWatcher(watcher);
        this.count.setFieldWatcher(watcher);
        return Lists.<CustomListItem>newArrayList(this.weight, this.count);
    }
}
