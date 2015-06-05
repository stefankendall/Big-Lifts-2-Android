package com.stefankendall.BigLiftsPro.views.fto.plan;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOVariant;
import com.stefankendall.BigLiftsPro.data.stores.JPurchaseStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOPlanListAdapter extends SimpleListAdapter {
    public FTOPlanListAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> iaps = Lists.<CustomListItem>newArrayList(
                new IapPlanListItem("Joker Sets", "", JFTOVariant.JOKER, true),
                new IapPlanListItem("Advanced", "", JFTOVariant.ADVANCED, false),
                new IapPlanListItem("5's Progression (Beginner)", "", JFTOVariant.FIVES_PROGRESSION, false)
        );

        List<CustomListItem> unlocked = Lists.<CustomListItem>newArrayList(
                new PlanListItem("Joker Sets", "", JFTOVariant.JOKER),
                new PlanListItem("Advanced", "", JFTOVariant.ADVANCED),
                new PlanListItem("5's Progression (Beginner)", "", JFTOVariant.FIVES_PROGRESSION));

        List<CustomListItem> lockedItems = JPurchaseStore.instance().hasPurchasedEverything() ? unlocked : iaps;

        List<CustomListItem> baseItems = Lists.newArrayList(
                new HeaderListItem("Lifting"),
                new TrainingMaxCell(),
                new WarmupCell(),
                new SixWeekCell(),
                new HeaderListItem("Variation"),
                new PlanListItem("Standard", "3x5, 3x3, 5/3/1, deload", JFTOVariant.STANDARD),
                new PlanListItem("Heavier", "standard + heavier weeks 1/2", JFTOVariant.HEAVIER),
                new PlanListItem("Powerlifting", "3x3, 5x3, 5/3/1, deload", JFTOVariant.POWERLIFTING),
                new PlanListItem("Pyramid", "standard + pyramid down", JFTOVariant.PYRAMID),
                new PlanListItem("First Set Last", "", JFTOVariant.FIRST_SET_LAST),
                new PlanListItem("First Set Last - Multiple Sets", "", JFTOVariant.FIRST_SET_LAST_MULTIPLE_SETS)
        );
        baseItems.addAll(lockedItems);
        return baseItems;
    }
}
