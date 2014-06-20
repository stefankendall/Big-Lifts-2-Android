package com.stefankendall.BigLifts.data.models.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.stefankendall.BigLifts.data.models.JModel;

import java.lang.reflect.Type;

public class JModelSerializer implements JsonSerializer<JModel> {
    @Override
    public JsonElement serialize(JModel model, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(model.uuid);
    }
}
