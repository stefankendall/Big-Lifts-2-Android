package com.stefankendall.BigLifts.views.fto.plan.assistance.fullcustom;

import android.app.Activity;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOFullCustomAssistanceWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.fto.lift.FTOSectionTitleHelper;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOFullCustomAssistanceListAdapter extends SimpleListAdapter {
    public FTOFullCustomAssistanceListAdapter(Activity activity) {
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



