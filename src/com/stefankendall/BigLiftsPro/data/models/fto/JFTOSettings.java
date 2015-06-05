package com.stefankendall.BigLiftsPro.data.models.fto;

import com.stefankendall.BigLiftsPro.data.models.JModel;

import java.math.BigDecimal;

public class JFTOSettings extends JModel {
    public static enum LogState {
        kShowAll,
        kShowWorkSets,
        kShowAmrap
    }

    public static enum RepsToBeatConfig {
        kRepsToBeatEverything,
        kRepsToBeatLogOnly
    }

    public BigDecimal trainingMax;
    public boolean warmupEnabled;
    public boolean sixWeekEnabled;
    public LogState logState;
    public RepsToBeatConfig repsToBeatConfig;
}
