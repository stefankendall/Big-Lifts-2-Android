package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JSetData;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOPlan;
import com.stefankendall.BigLifts.data.stores.fto.plans.JFTOWorkoutSetsGenerator;

import java.util.List;
import java.util.Map;

public class JFTOWorkoutStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOWorkout.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.<Class>newArrayList(JWorkout.class);
    }

    public static JFTOWorkoutStore instance() {
        return (JFTOWorkoutStore) BLJStore.instance(JFTOWorkoutStore.class);
    }

    @Override
    public void setupDefaults() {
        this.switchTemplate();
    }

    public void switchTemplate() {
        this.restoreTemplate();
        JFTOAssistanceStore.instance().addAssistance();
    }

    public void restoreTemplate() {
        ListMultimap<Integer, JLift> doneLiftsByWeek = this.getDoneLiftsByWeek();
        this.removeAll();
        this.createWorkoutsForEachLift();
        this.markDeloadWorkouts();
        this.markWeekIncrements();
        this.remarkDoneLifts(doneLiftsByWeek);
        if (!((JFTOSettings) JFTOSettingsStore.instance().first()).warmupEnabled) {
            this.removeWarmup();
        }
        //todo: Full custom adjust to fto
    }

    private void removeWarmup() {
        for (JModel model : JFTOWorkoutStore.instance().findAll()) {
            JFTOWorkout ftoWorkout = (JFTOWorkout) model;
            ftoWorkout.workout.removeSets(ftoWorkout.workout.warmupSets());
        }
    }

    private void remarkDoneLifts(ListMultimap<Integer, JLift> doneLiftsByWeek) {
        for (final Map.Entry<Integer, JLift> weekLiftPair : doneLiftsByWeek.entries()) {
            Optional<? extends JModel> matchingWorkout = Iterables.tryFind(JFTOWorkoutStore.instance().findAll(), new Predicate<JModel>() {
                @Override
                public boolean apply(JModel model) {
                    JFTOWorkout ftoWorkout = (JFTOWorkout) model;
                    return ftoWorkout.week == weekLiftPair.getKey() &&
                            ftoWorkout.workout.sets.get(0).lift == weekLiftPair.getValue();
                }
            });
            if (matchingWorkout.isPresent()) {
                JFTOWorkout match = (JFTOWorkout) matchingWorkout.get();
                match.done = true;
            }
        }
    }

    private void markDeloadWorkouts() {
        for (int week : new JFTOWorkoutSetsGenerator().deloadWeeks()) {
            for (JModel model : JFTOWorkoutStore.instance().findAllWhere("week", week)) {
                JFTOWorkout ftoWorkout = (JFTOWorkout) model;
                ftoWorkout.deload = true;
            }
        }
    }

    private void markWeekIncrements() {
        for (int week : new JFTOWorkoutSetsGenerator().incrementMaxesWeeks()) {
            for (JModel model : JFTOWorkoutStore.instance().findAllWhere("week", week)) {
                JFTOWorkout ftoWorkout = (JFTOWorkout) model;
                ftoWorkout.incrementAfterWeek = true;
            }
        }
    }

    private ListMultimap<Integer, JLift> getDoneLiftsByWeek() {
        ListMultimap<Integer, JLift> doneLiftsByWeek = ArrayListMultimap.create();
        for (JModel object : this.findAll()) {
            JFTOWorkout ftoWorkout = (JFTOWorkout) object;
            if (ftoWorkout.done) {
                JLift lift = ftoWorkout.workout.sets.get(0).lift;
                if (lift != null) {
                    doneLiftsByWeek.put(ftoWorkout.week, lift);
                }
            }
        }
        return doneLiftsByWeek;
    }

    private void createWorkoutsForEachLift() {
        JFTOVariant variant = (JFTOVariant) JFTOVariantStore.instance().first();
        JFTOPlan ftoPlan = new JFTOWorkoutSetsGenerator().planForVariant(variant.name);
        if (((JFTOSettings) JFTOSettingsStore.instance().first()).sixWeekEnabled) {
            this.createSixWeekWorkouts(ftoPlan);
        } else {
            this.createNormalWorkouts(ftoPlan);
        }

    }

    private void createSixWeekWorkouts(JFTOPlan ftoPlan) {
        List<JFTOLift> lifts = (List<JFTOLift>) JFTOLiftStore.instance().findAll();
        for (int week = 1; week <= 3; week++) {
            for (JFTOLift lift : lifts) {
                this.createWithWorkout(this.createWorkoutForLift(lift, week), week, lift.order);
            }
        }

        int weeks = ftoPlan.generate(null).size();
        for (int week = 1; week <= weeks; week++) {
            for (JFTOLift lift : lifts) {
                this.createWithWorkout(this.createWorkoutForLift(lift, week), week + 3, lift.order);
            }
        }
    }

    private void createNormalWorkouts(JFTOPlan ftoPlan) {
        int weeks = ftoPlan.generate(null).size();
        List<JFTOLift> lifts = (List<JFTOLift>) JFTOLiftStore.instance().findAll();
        for (int week = 1; week <= weeks; week++) {
            for (JFTOLift lift : lifts) {
                this.createWithWorkout(this.createWorkoutForLift(lift, week), week, lift.order);
            }
        }
    }

    private void createWithWorkout(JWorkout workout, int week, int order) {
        JFTOWorkout ftoWorkout = (JFTOWorkout) this.create();
        ftoWorkout.workout = workout;
        ftoWorkout.week = week;
        ftoWorkout.order = order;
    }

    private JWorkout createWorkoutForLift(JFTOLift lift, int week) {
        JWorkout workout = (JWorkout) JWorkoutStore.instance().create();
        List<JSetData> setData = new JFTOWorkoutSetsGenerator().setsForWeek(week, lift);
        List<JSet> sets = Lists.transform(setData, new Function<JSetData, JSet>() {
            @Override
            public JSet apply(JSetData setData) {
                return setData.createSet();
            }
        });
        workout.addSets(sets);
        return workout;
    }
}