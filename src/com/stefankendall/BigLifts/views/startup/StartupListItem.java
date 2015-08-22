package com.stefankendall.BigLifts.views.startup;

import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;

public class StartupListItem extends SimpleListItem{
    public StartupListItem(String value) {
        super(value);
    }

    @Override
    protected int listItemLayout() {
        return R.layout.startup_list_item;
    }
}
