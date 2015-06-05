package com.stefankendall.BigLiftsPro.data.helpers;

import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;

import java.util.List;

public class SetHelper {
    public static JSetLog heaviestAmrapSetLog(List<JSetLog> sets) {
        JSetLog heaviestAmrap = null;
        for (JSetLog testSet : sets) {
            if (testSet.amrap) {
                if (heaviestAmrap == null) {
                    heaviestAmrap = testSet;
                } else if (testSet.weight.compareTo(heaviestAmrap.weight) > 0) {
                    heaviestAmrap = testSet;
                }
            }
        }
        return heaviestAmrap;
    }

    public static JSet heaviestAmrapSet(List<JSet> sets) {
        JSet heaviestAmrap = null;
        for (JSet set : sets) {
            if (set.amrap) {
                if (heaviestAmrap == null) {
                    heaviestAmrap = set;
                } else if (set.effectiveWeight().compareTo(heaviestAmrap.effectiveWeight()) > 0) {
                    heaviestAmrap = set;
                }
            }
        }
        return heaviestAmrap;
    }
}

