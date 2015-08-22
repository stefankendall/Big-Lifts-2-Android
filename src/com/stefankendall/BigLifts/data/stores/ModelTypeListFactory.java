package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.stefankendall.BigLifts.data.models.*;
import com.stefankendall.BigLifts.data.models.fto.*;

import java.util.List;
import java.util.Map;

public class ModelTypeListFactory {
    public static TypeToken forClass(Class<? extends JModel> modelClass) {
        return getTypeMap().get(modelClass);
    }

    private static Map<Class, TypeToken> getTypeMap() {
        Map<Class, TypeToken> types = Maps.newHashMap();
        types.put(JFTOAssistance.class, new TypeToken<List<JFTOAssistance>>() {
        });
        types.put(JFTOLift.class, new TypeToken<List<JFTOLift>>() {
        });
        types.put(JFTOBoringButBig.class, new TypeToken<List<JFTOBoringButBig>>() {
        });
        types.put(JFTOBoringButBigLift.class, new TypeToken<List<JFTOBoringButBigLift>>() {
        });
        types.put(JFTOSet.class, new TypeToken<List<JFTOSet>>() {
        });
        types.put(JFTOCustomAssistanceWorkout.class, new TypeToken<List<JFTOCustomAssistanceWorkout>>() {
        });
        types.put(JFTOFullCustomAssistanceWorkout.class, new TypeToken<List<JFTOFullCustomAssistanceWorkout>>() {
        });
        types.put(JFTOCustomAssistanceLift.class, new TypeToken<List<JFTOCustomAssistanceLift>>() {
        });
        types.put(JFTOSettings.class, new TypeToken<List<JFTOSettings>>() {
        });
        types.put(JFTOVariant.class, new TypeToken<List<JFTOVariant>>() {
        });
        types.put(JFTOWorkout.class, new TypeToken<List<JFTOWorkout>>() {
        });
        types.put(JBar.class, new TypeToken<List<JBar>>() {
        });
        types.put(JCurrentProgram.class, new TypeToken<List<JCurrentProgram>>() {
        });
        types.put(JLift.class, new TypeToken<List<JLift>>() {
        });
        types.put(JPlate.class, new TypeToken<List<JPlate>>() {
        });
        types.put(JPurchase.class, new TypeToken<List<JPurchase>>() {
        });
        types.put(JSet.class, new TypeToken<List<JSet>>() {
        });
        types.put(JSetLog.class, new TypeToken<List<JSetLog>>() {
        });
        types.put(JSettings.class, new TypeToken<List<JSettings>>() {
        });
        types.put(JVersion.class, new TypeToken<List<JVersion>>() {
        });
        types.put(JWorkout.class, new TypeToken<List<JWorkout>>() {
        });
        types.put(JWorkoutLog.class, new TypeToken<List<JWorkoutLog>>() {
        });
        return types;
    }

    public static boolean contains(Class klass) {
        return getTypeMap().containsKey(klass);
    }
}
