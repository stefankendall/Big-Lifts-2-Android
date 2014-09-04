package com.stefankendall.BigLifts.data.helpers;

import com.stefankendall.BigLifts.data.models.JSetLog;

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
}
