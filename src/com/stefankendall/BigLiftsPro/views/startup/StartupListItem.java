package com.stefankendall.BigLiftsPro.views.startup;

import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListItem;

public class StartupListItem extends SimpleListItem{
    public StartupListItem(String value) {
        super(value);
    }

    @Override
    protected int listItemLayout() {
        return R.layout.startup_list_item;
    }
}
