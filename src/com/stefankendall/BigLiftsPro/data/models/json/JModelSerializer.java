package com.stefankendall.BigLiftsPro.data.models.json;

import com.google.gson.*;
import com.stefankendall.BigLiftsPro.data.models.JModel;

import java.lang.reflect.Type;

public class JModelSerializer implements JsonSerializer<JModel> {
    @Override
    public JsonElement serialize(JModel model, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(model.uuid);
    }
}
