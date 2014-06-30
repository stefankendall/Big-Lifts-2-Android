package com.stefankendall.BigLifts.views.fto.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class RoundToCellTests extends BLTestCase {
    public void testSetsLabel() {
        RoundToCell cell = new RoundToCell();
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView textView = (TextView) v.findViewById(R.id.label);
        Assert.assertEquals(textView.getText().toString(), "Round To");
    }

    public void testHasDefaultSelection() {
        RoundToCell cell = new RoundToCell();
        Assert.assertEquals(cell.defaultSelection(), 4);

        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.roundTo = new BigDecimal(JSettings.NEAREST_5_ROUNDING);
        Assert.assertEquals(cell.defaultSelection(), 5);

        settings.roundTo = new BigDecimal("0.5");
        Assert.assertEquals(cell.defaultSelection(), 0);
    }

    public void testValueCanBeChanged() {
        RoundToCell cell = new RoundToCell();
        cell.valueChanged(3);

        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Assert.assertEquals(settings.roundTo, BigDecimals.parse("2.5"));
    }
}
