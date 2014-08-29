package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.stores.JLiftStore;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class SetCellWithPlatesTests extends BLTestCase {
    public void testSetCellSetsPlates() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.weight = new BigDecimal("300");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set);

        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "[45, 45, 35, 2.5]");
    }

    public void testSetCellWithoutPlates() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.weight = new BigDecimal("45");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set);
        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "");
    }

    public void testDoesNotSetPlatesIfLiftDoesNotUseBar() {
        JLift lift = (JLift) JLiftStore.instance().create();
        lift.usesBar = false;
        lift.weight = new BigDecimal("200");
        JSet set = JSetStore.instance().create(lift, new BigDecimal("100"));

        View v = cellForSet(set);
        TextView plates = (TextView) v.findViewById(R.id.plates);
        Assert.assertEquals(plates.getText(), "");
    }

    private View cellForSet(JSet set) {
        SetCellWithPlates cell = new SetCellWithPlates(set);
        return cell.fillView(null, LayoutInflater.from(App.getContext()));
    }
}
