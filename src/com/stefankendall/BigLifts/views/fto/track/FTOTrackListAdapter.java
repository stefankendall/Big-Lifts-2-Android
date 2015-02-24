package com.stefankendall.BigLifts.views.fto.track;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FTOTrackListAdapter extends SimpleListAdapter {
    public TrackSort sorting = TrackSort.DATE;

    public FTOTrackListAdapter() {
    }

    public FTOTrackListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList(Lists.transform(getLog(), new Function<JWorkoutLog, CustomListItem>() {
            @Override
            public CustomListItem apply(JWorkoutLog log) {
                return new TrackListItem(log);
            }
        }));
        items.add(0, new HeaderListItem("Tap and hold to edit"));
        return items;
    }

    public List<JWorkoutLog> getLog() {
        List<JWorkoutLog> log = Lists.newArrayList(Iterables.filter(JWorkoutLogStore.instance().findAllWhere("name", "5/3/1"), JWorkoutLog.class));
        if (sorting == TrackSort.NAME) {
            Collections.sort(log, new Comparator<JWorkoutLog>() {
                @Override
                public int compare(JWorkoutLog log1, JWorkoutLog log2) {
                    JSetLog setLog1 = log1.sets.get(0);
                    JSetLog setLog2 = log2.sets.get(0);

                    if (setLog1.name.equals(setLog2.name)) {
                        return log2.date.compareTo(log1.date);
                    } else {
                        return setLog1.name.compareTo(setLog2.name);
                    }
                }
            });
        }

        return log;
    }
}
