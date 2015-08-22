package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;

import java.util.List;

public class FTOSimpleCustomAssistanceListAdapter extends SimpleListAdapter {
    public FTOSimpleCustomAssistanceListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.<CustomListItem>newArrayList(
                new HeaderListItem("Assistance for lift")
        );

        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift jftoLift = (JFTOLift) model;
            items.add(new SimpleListItem(jftoLift.name));
        }

        return items;
    }
}
