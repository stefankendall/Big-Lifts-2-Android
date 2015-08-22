package com.stefankendall.BigLiftsPro.views.fto.lift.individual.repsToBeat;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLiftsPro.data.helpers.SetHelper;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.views.cells.LeftRightTextCell;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.FTORepsToBeatCalculator;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.math.BigDecimal;
import java.util.List;

public class FTORepsToBeatListAdapter extends SimpleListAdapter {
    protected JFTOWorkout jftoWorkout;

    public FTORepsToBeatListAdapter(FragmentActivity context, JFTOWorkout jftoWorkout) {
        this.jftoWorkout = jftoWorkout;

        if (this.jftoWorkout == null) {
            throw new IllegalStateException("JFTOWorkout is null.");
        }
        if (this.jftoWorkout.workout == null) {
            throw new IllegalStateException("Workout is null.");
        }

        this.items = buildItems();
        this.activity = context;
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        List<JSet> workSets = this.jftoWorkout.workout.workSets();
        if (workSets.size() > 0) {
            JSet heaviestAmrap = SetHelper.heaviestAmrapSet(workSets);
            JFTOLift lift = (JFTOLift) heaviestAmrap.lift;

            BigDecimal logMax = FTORepsToBeatCalculator.findLogMax(lift);
            items.add(new LeftRightTextCell("Entered one rep max", BigDecimals.print(lift.weight)));
            items.add(new LeftRightTextCell("Estimated max from log", BigDecimals.print(logMax)));

            int repsToBeat = FTORepsToBeatCalculator.repsToBeat(lift, heaviestAmrap.roundedEffectiveWeight());
            String repsAndWeight = String.format("%dx %s would be...", repsToBeat, BigDecimals.print(heaviestAmrap.roundedEffectiveWeight()));

            BigDecimal oneRepEstimate = OneRepEstimator.estimate(heaviestAmrap.roundedEffectiveWeight(), repsToBeat);
            items.add(new LeftRightTextCell(repsAndWeight, BigDecimals.print(oneRepEstimate)));
        }
        return items;
    }
}
