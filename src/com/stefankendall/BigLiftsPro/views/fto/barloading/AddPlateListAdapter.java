package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class AddPlateListAdapter extends SimpleListAdapter {

    protected final FieldWatcher watcher;
    protected ParameterizedDecimalInputCell weight;
    protected ParameterizedIntegerInputCell count;

    public AddPlateListAdapter(FragmentActivity context, FieldWatcher watcher) {
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
