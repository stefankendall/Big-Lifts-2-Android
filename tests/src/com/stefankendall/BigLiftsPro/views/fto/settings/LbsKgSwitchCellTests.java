package com.stefankendall.BigLiftsPro.views.fto.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import com.stefankendall.BigLiftsPro.R;
import junit.framework.Assert;

public class LbsKgSwitchCellTests extends BLTestCase {
    public void testSwitchesUnits() {
        LbsKgSwitchCell cell = new LbsKgSwitchCell();
        cell.valueChanged(1);

        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Assert.assertEquals(settings.units, "kg");

        cell.valueChanged(0);
        Assert.assertEquals(settings.units, "lbs");
    }

    public void testSetsStoredUnitsValueLbs() {
        assertSelected("lbs");
    }

    public void testSetsStoredUnitsValueKg() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.units = "kg";
        assertSelected("kg");
    }

    private void assertSelected(String units) {
        LbsKgSwitchCell cell = new LbsKgSwitchCell();
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        RadioGroup group = (RadioGroup) v.findViewById(R.id.options);
        RadioButton button = (RadioButton) v.findViewById(group.getCheckedRadioButtonId());
        Assert.assertEquals(button.getText(), units);
    }
}
