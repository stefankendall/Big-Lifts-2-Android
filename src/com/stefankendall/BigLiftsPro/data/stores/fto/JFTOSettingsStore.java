package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.stores.BLJStore;

import java.math.BigDecimal;
import java.util.List;

public class JFTOSettingsStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOSettings.class;
    }

    public static JFTOSettingsStore instance() {
        return (JFTOSettingsStore) BLJStore.instance(JFTOSettingsStore.class);
    }

    @Override
    public void setupDefaults() {
        JFTOSettings settings = (JFTOSettings) this.create();
        settings.trainingMax = new BigDecimal("90");
        settings.warmupEnabled = true;
        settings.sixWeekEnabled = false;
        settings.logState = JFTOSettings.LogState.kShowWorkSets;
        settings.repsToBeatConfig = JFTOSettings.RepsToBeatConfig.kRepsToBeatEverything;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }
}
