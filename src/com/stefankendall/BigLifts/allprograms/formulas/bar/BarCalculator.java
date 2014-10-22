package com.stefankendall.BigLifts.allprograms.formulas.bar;

import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.CrashlyticsListener;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JPlateStore;

import java.math.BigDecimal;
import java.util.List;

public class BarCalculator {
    protected List<JPlate> plates;
    protected BigDecimal barWeight;

    public BarCalculator(List<JPlate> plates, BigDecimal barWeight) {
        this.plates = plates;
        this.barWeight = barWeight == null ? BigDecimal.ZERO : barWeight;
    }

    public List<BigDecimal> platesToMakeWeight(BigDecimal weight) {
//        this.logWeightForIssues(weight);

        BigDecimal targetWeight = weight.subtract(this.barWeight);
        List<PlateRemaining> remainingPlates = this.copyPlates(this.plates);
        List<BigDecimal> platesToMakeWeight = Lists.newArrayList();

        List<BigDecimal> potentialSolution = null;
        while (targetWeight.compareTo(BigDecimal.ZERO) > 0) {
            remainingPlates = Lists.newArrayList(Iterables.filter(remainingPlates, new Predicate<PlateRemaining>() {
                @Override
                public boolean apply(PlateRemaining plateRemaining) {
                    return plateRemaining.count > 0;
                }
            }));

            PlateRemaining nextPlate = this.findPlateClosestToWeight(targetWeight, remainingPlates);
            if (nextPlate == null) {
                if (targetWeight.compareTo(BigDecimal.ZERO) == 0 || potentialSolution != null) {
                    break;
                } else {
                    potentialSolution = Lists.newArrayList(platesToMakeWeight);
                    if (potentialSolution.size() > 0 && remainingPlates.size() > 0) {
                        final BigDecimal lastPlateWeight = potentialSolution.get(potentialSolution.size() - 1);
                        targetWeight = targetWeight.add(lastPlateWeight.multiply(new BigDecimal(2)));
                        Optional<PlateRemaining> plateForWeight = Iterables.tryFind(remainingPlates, new Predicate<PlateRemaining>() {
                            @Override
                            public boolean apply(PlateRemaining p) {
                                return p.weight.compareTo(lastPlateWeight) == 0;
                            }
                        });
                        if (plateForWeight.isPresent()) {
                            plateForWeight.get().count = 0;
                            platesToMakeWeight.remove(lastPlateWeight);
                        } else {
                            return potentialSolution;
                        }
                    }
                }

            } else {
                platesToMakeWeight.add(nextPlate.weight);
                nextPlate.count -= 2;
                targetWeight = targetWeight.subtract(nextPlate.weight.multiply(new BigDecimal(2)));
            }
        }

        return this.closestSolutionBetween(platesToMakeWeight, potentialSolution, targetWeight);
    }

    private void logWeightForIssues(BigDecimal weight) {
        Crashlytics.log(BigDecimals.print(weight));
        List<String> plates = Lists.transform(JPlateStore.instance().findAll(), new Function<JPlate, String>() {
            @Override
            public String apply(JPlate jPlate) {
                return BigDecimals.print(jPlate.weight);
            }
        });
        Crashlytics.log(BigDecimals.print(weight));
        Crashlytics.log(Joiner.on(",").join(plates));
    }

    protected List<BigDecimal> closestSolutionBetween(List<BigDecimal> solution1, List<BigDecimal> solution2, BigDecimal targetWeight) {
        if (solution2 == null) {
            return solution1;
        }

        BigDecimal solution1Sum = this.sum(solution1);
        BigDecimal solution2Sum = this.sum(solution2);

        BigDecimal solution1Diff = targetWeight.subtract(solution1Sum);
        BigDecimal solution2Diff = targetWeight.subtract(solution2Sum);

        return solution1Diff.compareTo(solution2Diff) < 0 ? solution1 : solution2;
    }

    protected BigDecimal sum(List<BigDecimal> plates) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal plate : plates) {
            sum = sum.add(plate.multiply(new BigDecimal(2)));
        }
        return sum;
    }

    protected List<PlateRemaining> copyPlates(List<JPlate> all) {
        return Lists.transform(JPlateStore.instance().findAll(), new Function<JPlate, PlateRemaining>() {
            @Override
            public PlateRemaining apply(JPlate jPlate) {
                return new PlateRemaining(jPlate);
            }
        });
    }

    protected PlateRemaining findPlateClosestToWeight(BigDecimal weight, List<PlateRemaining> plates) {
        for (PlateRemaining plate : plates) {
            if (plate.weight.multiply(new BigDecimal(2)).compareTo(weight) <= 0) {
                return plate;
            }
        }
        return null;
    }
}
