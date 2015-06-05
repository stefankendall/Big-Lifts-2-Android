package com.stefankendall.BigLiftsPro.views.fto.exportimport;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.stores.JSetLogStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;

import java.util.List;
import java.util.Map;

public class LogJsonExporterImporter {
    public static String export() {
        Map<String, List<String>> logs = Maps.newHashMap();
        logs.put("setLogs", JSetLogStore.instance().serialize());
        logs.put("workoutLogs", JWorkoutLogStore.instance().serialize());
        Gson gson = new Gson();
        return gson.toJson(logs);
    }

    public static void importLog(String contents) {
        Gson gson = new Gson();
        Map<String, List<String>> dictionary = gson.fromJson(contents, Map.class);

        String setLogsStringValue = "[" + Joiner.on(",").join(dictionary.get("setLogs")) + "]";
        List<JModel> setLogs = (List<JModel>) JSetLogStore.instance().deserialize(setLogsStringValue);

        JSetLogStore.instance().empty();
        JSetLogStore.instance().setupData(setLogs);

        String workoutLogsStringValue = "[" + Joiner.on(",").join(dictionary.get("workoutLogs")) + "]";
        List<JModel> workoutLogs = (List<JModel>) JWorkoutLogStore.instance().deserialize(workoutLogsStringValue);

        JWorkoutLogStore.instance().empty();
        JWorkoutLogStore.instance().setupData(workoutLogs);
    }
}
