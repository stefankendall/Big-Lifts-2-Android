package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Activity;
import android.widget.ListAdapter;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLifts.views.fto.barloading.AddCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;

import java.util.ArrayList;
import java.util.List;

public class FTOCustomAssistanceEditLiftsAdapter extends SimpleListAdapter {
    public FTOCustomAssistanceEditLiftsAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.<CustomListItem>newArrayList(
                new HeaderListItem("Tap and hold to remove")
        );

        for (JModel customLiftModel : JFTOCustomAssistanceLiftStore.instance().findAll()) {
            JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) customLiftModel;
            items.add(new SimpleListItem(lift.name));
        }

        items.add(new AddCell());

        return items;
    }
}
