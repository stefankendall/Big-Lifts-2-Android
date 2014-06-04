package com.stefankendall.BigLifts.data.models;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

import java.util.List;

public class JWorkout extends JModel {
    public List<JSet> sets;

    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList("sets");
    }

    public List<JSet> workSets() {
        return ImmutableList.copyOf(Iterables.filter(this.sets, new Predicate<JSet>() {
            @Override
            public boolean apply(JSet set) {
                return !set.warmup && !set.assistance;
            }
        }));
    }

    public List<JSet> warmupSets() {
        return ImmutableList.copyOf(Iterables.filter(this.sets, new Predicate<JSet>() {
            @Override
            public boolean apply(JSet set) {
                return set.warmup && !set.assistance;
            }
        }));
    }

    public List<JSet> assistanceSets() {
        return ImmutableList.copyOf(Iterables.filter(this.sets, new Predicate<JSet>() {
            @Override
            public boolean apply(JSet set) {
                return set.assistance;
            }
        }));
    }

    public void addSet(JSet set) {
        this.sets.add(set);
    }

    public void addSets(List<JSet> newSets) {
        this.sets.addAll(newSets);
    }

    public void removeSet(JSet set) {
        this.sets.remove(set);
        BLJStoreManager.instance().storeForModel(set.getClass(), set.uuid).remove(set);
    }

    public void removeSets(List<JSet> setsToRemove) {
        if (setsToRemove == this.sets) {
            for (JSet set : setsToRemove) {
                BLJStoreManager.instance().storeForModel(set.getClass(), set.uuid).remove(set);
            }
            this.sets = Lists.newArrayList();
        } else {
            for (JSet set : setsToRemove) {
                this.removeSet(set);
            }
        }
    }

    public JLift firstLift() {
        if(this.sets.size() > 0){
            return this.sets.get(0).lift;
        }
        return null;
    }

    public void addSets(List<JSet> sets, int index){
        if(index != 0){
            throw new RuntimeException("Not supported");
        }
        this.sets.addAll(0, sets);
    }
}
