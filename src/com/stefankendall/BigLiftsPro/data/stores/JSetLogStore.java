package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;

import java.math.BigDecimal;
import java.util.List;

public class JSetLogStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JSetLog.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public static JSetLogStore instance() {
        return (JSetLogStore) BLJStore.instance(JSetLogStore.class);
    }

    public JSetLog createFromSet(JSet set) {
        JSetLog setLog = (JSetLog) this.create();
        setLog.reps = set.reps != null ? set.reps : 0;
        setLog.weight = set.roundedEffectiveWeight();
        setLog.name = set.lift.name;
        setLog.warmup = set.warmup;
        setLog.assistance = set.assistance;
        setLog.amrap = set.amrap;
        return setLog;
    }

    public JSetLog create(String name, BigDecimal weight, int reps, boolean warmup, boolean assistance, boolean amrap) {
        JSetLog log = (JSetLog) this.create();
        log.name = name;
        log.weight = weight;
        log.reps = reps;
        log.warmup = warmup;
        log.assistance = assistance;
        log.amrap = amrap;
        return log;
    }
}
