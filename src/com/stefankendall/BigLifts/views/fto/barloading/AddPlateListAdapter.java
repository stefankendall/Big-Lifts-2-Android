package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class AddPlateListAdapter extends SimpleListAdapter {

    protected final FieldsWatcher watcher;
    protected ParameterizedDecimalInputCell weight;
    protected ParameterizedIntegerInputCell count;

    public AddPlateListAdapter(Activity context, FieldsWatcher watcher) {
        super(context);
        this.watcher = watcher;
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        this.weight = new ParameterizedDecimalInputCell("Weight", "");
        this.count = new ParameterizedIntegerInputCell("Count", "");
        return Lists.newArrayList(this.weight, this.count);
    }
}
