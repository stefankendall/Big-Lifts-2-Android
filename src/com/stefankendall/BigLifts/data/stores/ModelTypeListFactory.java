package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.stefankendall.BigLifts.data.models.*;
import com.stefankendall.BigLifts.data.models.fto.*;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModelTypeListFactory {
    public static Type forClass(Class<? extends JModel> modelClass) {
        Map<Class, TypeToken> types = getTypeMap();
        return types.get(modelClass).getType();
    }

    private static Map<Class, TypeToken> getTypeMap() {
        Map<Class, TypeToken> types = Maps.newHashMap();
        types.put(JFTOAssistance.class, new TypeToken<CopyOnWriteArrayList<JFTOAssistance>>() {
        });
        types.put(JFTOLift.class, new TypeToken<CopyOnWriteArrayList<JFTOLift>>() {
        });
        types.put(JFTOBoringButBig.class, new TypeToken<CopyOnWriteArrayList<JFTOBoringButBig>>() {
        });
        types.put(JFTOBoringButBigLift.class, new TypeToken<CopyOnWriteArrayList<JFTOBoringButBigLift>>() {
        });
        types.put(JFTOSet.class, new TypeToken<CopyOnWriteArrayList<JFTOSet>>() {
        });
        types.put(JFTOSettings.class, new TypeToken<CopyOnWriteArrayList<JFTOSettings>>() {
        });
        types.put(JFTOVariant.class, new TypeToken<CopyOnWriteArrayList<JFTOVariant>>() {
        });
        types.put(JFTOWorkout.class, new TypeToken<CopyOnWriteArrayList<JFTOWorkout>>() {
        });
        types.put(JBar.class, new TypeToken<CopyOnWriteArrayList<JBar>>() {
        });
        types.put(JCurrentProgram.class, new TypeToken<CopyOnWriteArrayList<JCurrentProgram>>() {
        });
        types.put(JLift.class, new TypeToken<CopyOnWriteArrayList<JLift>>() {
        });
        types.put(JPlate.class, new TypeToken<CopyOnWriteArrayList<JPlate>>() {
        });
        types.put(JPurchase.class, new TypeToken<CopyOnWriteArrayList<JPurchase>>() {
        });
        types.put(JSet.class, new TypeToken<CopyOnWriteArrayList<JSet>>() {
        });
        types.put(JSetLog.class, new TypeToken<CopyOnWriteArrayList<JSetLog>>() {
        });
        types.put(JSettings.class, new TypeToken<CopyOnWriteArrayList<JSettings>>() {
        });
        types.put(JVersion.class, new TypeToken<CopyOnWriteArrayList<JVersion>>() {
        });
        types.put(JWorkout.class, new TypeToken<CopyOnWriteArrayList<JWorkout>>() {
        });
        types.put(JWorkoutLog.class, new TypeToken<CopyOnWriteArrayList<JWorkoutLog>>() {
        });
        return types;
    }

    public static boolean contains(Class klass) {
        return getTypeMap().containsKey(klass);
    }
}
