package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JPlate;
import com.stefankendall.BigLiftsPro.data.models.JSettings;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JPlateStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JPlate.class;
    }

    public static JPlateStore instance() {
        return (JPlateStore) BLJStore.instance(JPlateStore.class);
    }

    public void setupDefaults() {
        this.createPlate(new BigDecimal("45"), 6);
        this.createPlate(new BigDecimal("35"), 6);
        this.createPlate(new BigDecimal("25"), 6);
        this.createPlate(new BigDecimal("10"), 6);
        this.createPlate(new BigDecimal("5"), 6);
        this.createPlate(new BigDecimal("2.5"), 6);
    }

    public void createPlate(BigDecimal weight, int count) {
        JPlate p = (JPlate) this.create();
        p.weight = weight;
        p.count = count;
    }

    @Override
    public List<JPlate> findAll() {
        List<JPlate> sorted = Lists.newArrayList(Iterables.filter(this.data, JPlate.class));
        Collections.sort(sorted, new Comparator<JModel>() {
            @Override
            public int compare(JModel model1, JModel model2) {
                return ((JPlate) model2).weight.intValue() - ((JPlate) model1).weight.intValue();
            }
        });
        return sorted;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public void adjustForKg() {
        if (((JSettings) JSettingsStore.instance().first()).units.equals("kg")) {
            JPlate firstPlate = (JPlate) this.first();
            if (firstPlate.weight.equals(new BigDecimal("45"))) {
                this.empty();
                this.createPlate(new BigDecimal("20"), 6);
                this.createPlate(new BigDecimal("15"), 6);
                this.createPlate(new BigDecimal("10"), 6);
                this.createPlate(new BigDecimal("5"), 6);
                this.createPlate(new BigDecimal("2.5"), 6);
                this.createPlate(new BigDecimal("1"), 6);
            }
        }
    }
}
