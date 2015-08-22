package com.stefankendall.BigLifts.views.fto.lift;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOWorkoutListAdapter extends SimpleListAdapter {
    public FTOWorkoutListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        ListMultimap<Integer, JFTOWorkout> workoutsByWeek = this.workoutsByWeek();
        List<Integer> weeks = Ordering.natural().immutableSortedCopy(workoutsByWeek.keySet());

        List<CustomListItem> items = Lists.newArrayList();
        for (int week : weeks) {
            items.add(new HeaderListItem(new FTOSectionTitleHelper().titleForSection(week - 1)));
            for (JFTOWorkout jftoWorkout : workoutsByWeek.get(week)) {
                items.add(new FTOWorkoutSummaryCell(jftoWorkout));
            }
        }

        return items;
    }

    protected ListMultimap<Integer, JFTOWorkout> workoutsByWeek() {
        ListMultimap<Integer, JFTOWorkout> workoutsByWeek = ArrayListMultimap.create();
        for (JFTOWorkout jftoWorkout : (List<JFTOWorkout>) JFTOWorkoutStore.instance().findAll()) {
            workoutsByWeek.put(jftoWorkout.week, jftoWorkout);
        }

        return workoutsByWeek;
    }

    public JFTOWorkout workoutForPosition(int pos) {
        CustomListItem item = this.items.get(pos);
        if (item instanceof HeaderListItem) {
            return null;
        }

        return ((FTOWorkoutSummaryCell) item).jftoWorkout;
    }
}
