package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.stores.BLJStore;
import com.stefankendall.BigLiftsPro.data.stores.JLiftStore;

import java.math.BigDecimal;

public class JFTOCustomAssistanceLiftStore extends JLiftStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOCustomAssistanceLift.class;
    }

    public static JFTOCustomAssistanceLiftStore instance() {
        return (JFTOCustomAssistanceLiftStore) BLJStore.instance(JFTOCustomAssistanceLiftStore.class);
    }

    public void setDefaultsForObject(JModel model) {
        super.setDefaultsForObject(model);
        JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) model;

        Integer order = (Integer) this.max("order");
        lift.order = order != null ? order + 1 : 0;
        lift.increment = BigDecimal.ZERO;
    }

    public void liftsChanged() {
        JFTOCustomAssistanceWorkoutStore.instance().removeSetsForMissingAssistanceLifts();
        super.liftsChanged();
    }
}
