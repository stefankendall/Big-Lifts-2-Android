package com.stefankendall.BigLiftsPro.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.stores.JLiftStore;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.SetChange;
import junit.framework.Assert;

import java.math.BigDecimal;

public class SetCellWithPlatesTests extends BLTestCase {
    public void testSetCellSetsPlates() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.usesBar = true;
        lift.weight = new BigDecimal("300");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set, new SetChange());

        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "[45, 45, 35, 2.5]");
    }

    public void testSetCellWithoutPlates() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.weight = new BigDecimal("45");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set, new SetChange());
        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "");
    }

    public void testDoesNotSetPlatesIfLiftDoesNotUseBar() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.usesBar = false;
        lift.weight = new BigDecimal("200");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set, new SetChange());
        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "");
    }

    public void testMakesSetChangePlates() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.usesBar = true;
        lift.weight = new BigDecimal("200");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set, new SetChange(new BigDecimal("135"), null));
        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "[45]");
    }

    private View cellForSet(JSet set, SetChange setChange) {
        SetCellWithPlates cell = new SetCellWithPlates(set, setChange);
        return cell.fillView(null, LayoutInflater.from(App.getContext()));
    }
}
