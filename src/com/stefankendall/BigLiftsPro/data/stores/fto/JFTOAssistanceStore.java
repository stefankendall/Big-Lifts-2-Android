package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.BLJStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.plans.assistance.*;

import java.util.List;
import java.util.Map;

public class JFTOAssistanceStore extends BLJStore {
    @Override
    public void setupDefaults() {
        JFTOAssistance assistance = (JFTOAssistance) this.create();
        assistance.name = JFTOAssistance.NONE;
    }

    public void changeTo(String assistanceName) {
        JFTOAssistance assistance = (JFTOAssistance) this.first();
        assistance.name = assistanceName;
        this.removeAssistance();
        JFTOWorkoutStore.instance().restoreTemplate();
        this.addAssistance();
    }

    private void removeAssistance() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout ftoWorkout = (JFTOWorkout) model;
            ftoWorkout.workout.removeSets(ftoWorkout.workout.assistanceSets());
        }
    }

    public void addAssistance() {
        this.assistanceGeneratorForName(this.currentAssistance()).setup();
    }

    public void cycleChange() {
        this.assistanceGeneratorForName(this.currentAssistance()).cycleChange();
    }

    public void restore() {
        this.changeTo(this.currentAssistance());
    }

    private String currentAssistance() {
        JFTOAssistance assistance = (JFTOAssistance) this.first();
        return assistance.name;
    }

    public JFTOAssistanceProtocol assistanceGeneratorForName(String name) {
        Map<String, JFTOAssistanceProtocol> generators = ImmutableMap.<String, JFTOAssistanceProtocol>builder()
                .put(JFTOAssistance.NONE, new JFTONoneAssistance())
                .put(JFTOAssistance.BORING_BUT_BIG, new JFTOBoringButBigAssistance())
                .put(JFTOAssistance.SIMPLE_CUSTOM, new JFTOSimpleCustomAssistance())
                .put(JFTOAssistance.FULL_CUSTOM, new JFTOFullCustomAssistance())
                .build();
        return generators.get(name);
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOAssistance.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public static JFTOAssistanceStore instance() {
        return (JFTOAssistanceStore) BLJStore.instance(JFTOAssistanceStore.class);
    }
}
