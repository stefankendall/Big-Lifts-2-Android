package com.stefankendall.BigLifts.views.fto.plan;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOPlanListAdapter extends SimpleListAdapter {
    public FTOPlanListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
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
                new PlanListItem("First Set Last - Multiple Sets", "", JFTOVariant.FIRST_SET_LAST_MULTIPLE_SETS),
                new PlanListItem("Joker Sets", "", JFTOVariant.JOKER),
                new PlanListItem("Advanced", "", JFTOVariant.ADVANCED),
                new PlanListItem("5's Progression (Beginner)", "", JFTOVariant.FIVES_PROGRESSION)
        );
    }
}
