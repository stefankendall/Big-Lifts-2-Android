package com.stefankendall.BigLiftsPro.views.fto.onerep;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import junit.framework.Assert;

public class FormulaSelectorCellTests extends BLTestCase {

    public void testCanChangeRoundingFormula(){
        FormulaSelectorCell cell = new FormulaSelectorCell(null);
        cell.valueChanged(1);
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Assert.assertEquals(settings.roundingFormula, JSettings.ROUNDING_FORMULA_BRZYCKI);
    }

    public void testDefaultsToCurrentRoundingFormula(){
        FormulaSelectorCell cell = new FormulaSelectorCell(null);
        Assert.assertEquals(cell.defaultSelection(), 0);
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.roundingFormula = JSettings.ROUNDING_FORMULA_BRZYCKI;
        Assert.assertEquals(cell.defaultSelection(), 1);
    }
}
