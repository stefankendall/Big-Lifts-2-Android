package com.stefankendall.BigLifts.views.fto.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class BarWeightCellTests extends BLTestCase {
    public void testSetsLabel() {
        BarWeightCell cell = new BarWeightCell();
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        TextView textView = (TextView) v.findViewById(R.id.label);
        Assert.assertEquals(textView.getText().toString(), "Bar Weight");
    }

    public void testSetsDefaultValue() {
        BarWeightCell cell = new BarWeightCell();
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        EditText input = (EditText) v.findViewById(R.id.decimal_input);
        Assert.assertEquals(input.getText().toString(), "45");
    }

    public void testValueCanBeChanged() {
        BarWeightCell cell = new BarWeightCell();
        cell.valueChanged(new BigDecimal("22.5"));
        JBar bar = (JBar) JBarStore.instance().first();
        Assert.assertEquals(bar.weight, new BigDecimal("22.5"));
    }
}
