package com.stefankendall.BigLifts.views.fto.lift;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import junit.framework.Assert;

import java.util.List;

public class FTOWorkoutListAdapterTests extends BLTestCase {

    public void testGetsWorkoutsByWeek() {
        ListMultimap<Integer, JFTOWorkout> workoutsByWeek = new FTOWorkoutListAdapter(new FTOWorkoutListActivity()).workoutsByWeek();
        Assert.assertEquals(workoutsByWeek.keySet().size(), 4);
        Assert.assertEquals(workoutsByWeek.get(1).size(), 4);
        Assert.assertEquals(workoutsByWeek.get(2).size(), 4);
        Assert.assertEquals(workoutsByWeek.get(3).size(), 4);
        Assert.assertEquals(workoutsByWeek.get(4).size(), 4);

        Assert.assertEquals(workoutsByWeek.get(2).get(0).order, 0);
        Assert.assertEquals(workoutsByWeek.get(2).get(1).order, 1);
        Assert.assertEquals(workoutsByWeek.get(2).get(2).order, 2);
        Assert.assertEquals(workoutsByWeek.get(2).get(3).order, 3);
    }

    public void testHasHeaderListItems() {
        List<CustomListItem> items = (List<CustomListItem>) new FTOWorkoutListAdapter(new FTOWorkoutListActivity()).buildItems();
        List<HeaderListItem> headers = Lists.newArrayList(Iterables.filter(items, HeaderListItem.class));

        Assert.assertEquals(headers.size(), 4);
        Assert.assertEquals(headers.get(0).value, "5/5/5");
        Assert.assertEquals(headers.get(1).value, "3/3/3");
        Assert.assertEquals(headers.get(2).value, "5/3/1");
        Assert.assertEquals(headers.get(3).value, "Deload");
    }
}
