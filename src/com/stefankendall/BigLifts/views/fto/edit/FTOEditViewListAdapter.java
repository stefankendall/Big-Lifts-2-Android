package com.stefankendall.BigLifts.views.fto.edit;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.LeftRightHeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOEditViewListAdapter extends SimpleListAdapter {
    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
                new LeftRightHeaderListItem("Max", "Training Max")
        );
    }
}
