package com.stefankendall.BigLiftsPro.views.fto.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JBar;
import com.stefankendall.BigLiftsPro.data.stores.JBarStore;
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
        EditText input = (EditText) v.findViewById(R.id.input);
        Assert.assertEquals(input.getText().toString(), "45");
    }

    public void testValueCanBeChanged() {
        BarWeightCell cell = new BarWeightCell();
        cell.valueChanged(new BigDecimal("22.5"));
        JBar bar = (JBar) JBarStore.instance().first();
        Assert.assertEquals(bar.weight, new BigDecimal("22.5"));
    }
}
