package com.stefankendall.BigLiftsPro.data.models.json;

import com.google.gson.*;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.stores.BLJStore;
import com.stefankendall.BigLiftsPro.data.stores.BLJStoreManager;

import java.lang.reflect.Type;

public class JModelDeserializer implements JsonDeserializer<JModel> {
    @Override
    public JModel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String uuid = jsonElement.getAsString();
        BLJStore store = BLJStoreManager.instance().storeForModel((Class) type, uuid);
        if (store == null) {
            return null;
        } else {
            return store.find("uuid", uuid);
        }
    }
}
