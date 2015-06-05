package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListItem;

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
