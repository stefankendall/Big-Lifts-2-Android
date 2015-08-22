package com.stefankendall.BigLifts.views.fto.lift;

import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOPlan;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOWorkoutSetsGenerator;

import java.util.List;

public class FTOSectionTitleHelper {
    public String titleForSection(int section) {
        JFTOVariant variant = (JFTOVariant) JFTOVariantStore.instance().first();
        JFTOPlan ftoPlan = new JFTOWorkoutSetsGenerator().planForVariant(variant.name);

        int titleSection = 0;
        JFTOSettings ftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        if (ftoSettings.sixWeekEnabled) {
            if (section < 3) {
                titleSection = section;
            } else {
                titleSection = section - 3;
            }
        } else {
            titleSection = section;
        }

        List<String> weekNames = ftoPlan.weekNames();
        if (titleSection >= weekNames.size()) {
            return "";
        }
        return weekNames.get(titleSection);
    }
}
