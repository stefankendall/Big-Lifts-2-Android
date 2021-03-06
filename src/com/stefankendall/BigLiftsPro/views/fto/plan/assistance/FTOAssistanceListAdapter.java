package com.stefankendall.BigLiftsPro.views.fto.plan.assistance;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig.FTOBoringButBigActivity;
import com.stefankendall.BigLiftsPro.views.fto.plan.assistance.fullcustom.FTOFullCustomAssistanceActivity;
import com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.FTOSimpleCustomAssistanceActivity;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOAssistanceListAdapter extends SimpleListAdapter {
    public FTOAssistanceListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new AssistancePlanListItem("None", "", JFTOAssistance.NONE, null),
                new AssistancePlanListItem("Boring But Big", "5 sets 10 reps @50% of main lift", JFTOAssistance.BORING_BUT_BIG, FTOBoringButBigActivity.class),
                new AssistancePlanListItem("Simple Custom", "Configure for each lift", JFTOAssistance.SIMPLE_CUSTOM, FTOSimpleCustomAssistanceActivity.class),
                new AssistancePlanListItem("Full Custom", "Assistance for lift and week", JFTOAssistance.FULL_CUSTOM, FTOFullCustomAssistanceActivity.class)
        );
    }
}
