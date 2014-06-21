package com.stefankendall.BigLifts.data.models.json;

import com.google.gson.*;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.BLJStoreManager;

import java.lang.reflect.Type;

public class JModelDeserializer implements JsonDeserializer<JModel> {
    @Override
    public JModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String uuid = jsonElement.getAsString();
        BLJStore store = BLJStoreManager.instance().storeForModel((Class) type, uuid);
        return store.find("uuid", uuid);
    }
}
