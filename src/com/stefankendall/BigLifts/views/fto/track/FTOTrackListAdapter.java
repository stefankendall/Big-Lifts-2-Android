package com.stefankendall.BigLifts.views.fto.track;

import android.app.Activity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOTrackListAdapter extends SimpleListAdapter {
    public FTOTrackListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.transform(getLog(), new Function<JModel, CustomListItem>() {
            @Override
            public CustomListItem apply(JModel jModel) {
                return new TrackListItem((JWorkoutLog) jModel);
            }
        });
    }

    private List<? extends JModel> getLog() {
        return JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
    }
}
