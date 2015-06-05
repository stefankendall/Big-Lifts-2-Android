package com.stefankendall.BigLiftsPro.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.stores.JLiftStore;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.SetChange;
import junit.framework.Assert;

import java.math.BigDecimal;

public class SetCellTests extends BLTestCase {
    public void testShowsNoWeightIfWeight0AndNoBar() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.usesBar = false;
        lift.weight = BigDecimal.ZERO;
        JSet set = JSetStore.instance().create(lift, BigDecimal.ZERO);
        SetCell cell = new SetCell(set, new SetChange());

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView weightText = (TextView) view.findViewById(R.id.weight);
        Assert.assertEquals(weightText.getText(), "");
    }

    public void testAmrapIndicator() {
        JLift lift = (JLift) JLiftStore.instance().create();
        JSet set = JSetStore.instance().create(lift, BigDecimal.ZERO);
        set.amrap = true;
        SetCell cell = new SetCell(set, new SetChange());

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView repsText = (TextView) view.findViewById(R.id.reps);
        Assert.assertTrue(repsText.getText().toString().contains("+"));
    }

    public void testUsesEnteredWeight() {
        JLift lift = (JLift) JLiftStore.instance().create();
        JSet set = JSetStore.instance().create(lift, BigDecimal.ZERO);
        set.amrap = true;
        SetCell cell = new SetCell(set, new SetChange(new BigDecimal("100.5"), null));

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView weightText = (TextView) view.findViewById(R.id.weight);
        Assert.assertEquals(weightText.getText(), "100.5");
    }

    public void testUsesEnteredReps() {
        JLift lift = (JLift) JLiftStore.instance().create();
        JSet set = JSetStore.instance().create(lift, BigDecimal.ZERO);
        set.reps = 3;
        SetCell cell = new SetCell(set, new SetChange(null, 4));

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView repsText = (TextView) view.findViewById(R.id.reps);
        Assert.assertEquals(repsText.getText(), "4x");
    }
}
