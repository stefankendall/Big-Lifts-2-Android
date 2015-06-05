package com.stefankendall.BigLiftsPro.data.models.fto;

import com.stefankendall.BigLiftsPro.data.models.JBar;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.stores.JBarStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;

import java.math.BigDecimal;

public class JFTOSet extends JSet {
    public BigDecimal effectiveWeight(){
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        BigDecimal effectiveWeight = super.effectiveWeight().multiply(settings.trainingMax).divide(new BigDecimal("100"));

        if(this.lift.usesBar){
            JBar bar = (JBar) JBarStore.instance().first();
            if(effectiveWeight.compareTo(bar.weight) < 0){
                return bar.weight;
            }
        }

        return effectiveWeight;
    }
}
