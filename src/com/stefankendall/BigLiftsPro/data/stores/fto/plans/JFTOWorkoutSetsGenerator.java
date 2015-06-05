package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOVariant;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOVariantStore;

import java.util.List;
import java.util.Map;

public class JFTOWorkoutSetsGenerator {
    public JFTOPlan planForVariant(String variant) {
        Map<String, JFTOPlan> plans = ImmutableMap.<String, JFTOPlan>builder()
                .put(JFTOVariant.STANDARD, new JFTOStandardPlan())
                .put(JFTOVariant.HEAVIER, new JFTOHeavierPlan())
                .put(JFTOVariant.POWERLIFTING, new JFTOPowerliftingPlan())
                .put(JFTOVariant.PYRAMID, new JFTOPyramidPlan())
                .put(JFTOVariant.JOKER, new JFTOJokerPlan())
                .put(JFTOVariant.FIRST_SET_LAST, new JFTOFirstSetLastPlan())
                .put(JFTOVariant.FIRST_SET_LAST_MULTIPLE_SETS, new JFTOFirstSetLastsMultipleSetsPlan())
                .put(JFTOVariant.ADVANCED, new JFTOAdvancedPlan())
                .put(JFTOVariant.FIVES_PROGRESSION, new JFTOFivesProgression())
                .build();
        return plans.get(variant);
    }

    public JFTOPlan planForCurrentVariant() {
        JFTOVariant variant = (JFTOVariant) JFTOVariantStore.instance().first();
        return this.planForVariant(variant.name);
    }

    public List<Integer> incrementMaxesWeeks() {
        return this.planForCurrentVariant().incrementMaxesWeeks();
    }

    public List<Integer> deloadWeeks() {
        return this.planForCurrentVariant().deloadWeeks();
    }

    public List<JSetData> setsForWeek(int week, JFTOLift lift) {
        return this.setsFor(lift).get(week);
    }

    private Map<Integer, List<JSetData>> setsFor(JFTOLift lift) {
        return this.planForCurrentVariant().generate(lift);
    }

    public Map<Integer, List<JSetData>> setsFor(JFTOLift lift, String variant) {
        return this.planForVariant(variant).generate(lift);
    }
}
