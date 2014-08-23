package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;

import java.util.List;

public class FTOWorkoutChangeCache {
    static FTOWorkoutChangeCache cache;
    protected List<FTOWorkoutChange> ftoWorkoutChanges;
    private List<Integer> completedSets;

    public synchronized static FTOWorkoutChangeCache instance() {
        if (cache == null) {
            cache = new FTOWorkoutChangeCache();
            cache.ftoWorkoutChanges = Lists.newArrayList();
            cache.completedSets = Lists.newArrayList();
        }
        return cache;
    }

    public void clear() {
        this.ftoWorkoutChanges = Lists.newArrayList();
        this.completedSets = Lists.newArrayList();
    }

    public FTOWorkoutChange changeForWorkout(final JFTOWorkout workout) {
        Optional<FTOWorkoutChange> change = Iterables.tryFind(this.ftoWorkoutChanges, new Predicate<FTOWorkoutChange>() {
            @Override
            public boolean apply(FTOWorkoutChange ftoWorkoutChange) {
                return ftoWorkoutChange.workout == workout;
            }
        });

        if (!change.isPresent()) {
            change = Optional.of(new FTOWorkoutChange(workout));
            this.ftoWorkoutChanges.add(change.get());
        }
        return change.get();
    }

    public SetChange changeForWorkout(JFTOWorkout workout, int set) {
        FTOWorkoutChange workoutChange = this.changeForWorkout(workout);
        SetChange setChange = workoutChange.changesBySet.get(set);
        if (setChange == null) {
            setChange = new SetChange();
            workoutChange.changesBySet.put(set, setChange);
        }

        return setChange;
    }

    public boolean isComplete(int set) {
        return this.completedSets.contains(set);
    }

    public void toggleComplete(int set) {
        if (this.isComplete(set)) {
            this.completedSets.remove(set);
        } else {
            this.completedSets.add(set);
        }
    }
}
