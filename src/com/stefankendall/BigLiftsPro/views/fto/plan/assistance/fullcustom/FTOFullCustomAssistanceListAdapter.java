package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.fullcustom;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOFullCustomAssistanceWorkoutStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.fto.lift.FTOSectionTitleHelper;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOFullCustomAssistanceListAdapter extends SimpleListAdapter {
    public FTOFullCustomAssistanceListAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<Integer> weeks = Lists.newArrayList(Iterables.filter(JFTOWorkoutStore.instance().unique("week"), Integer.class));
        List<CustomListItem> items = Lists.newArrayList();
        for (int week : weeks) {
            items.add(new HeaderListItem(new FTOSectionTitleHelper().titleForSection(week - 1)));
            for (JModel model : JFTOFullCustomAssistanceWorkoutStore.instance().findAllWhere("week", week)) {
                JFTOFullCustomAssistanceWorkout workout = (JFTOFullCustomAssistanceWorkout) model;
                items.add(new FTOFullCustomWorkoutSummaryLiftCell(workout));
            }
        }
        return items;
    }

    public JFTOFullCustomAssistanceWorkout workoutForPosition(int pos) {
        CustomListItem item = this.items.get(pos);
        if (item instanceof HeaderListItem) {
            return null;
        }

        return  ((FTOFullCustomWorkoutSummaryLiftCell) item).jftoFullCustomAssistanceWorkout;
    }
}



