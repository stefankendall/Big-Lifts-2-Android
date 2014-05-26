package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.stefankendall.BigLifts.data.models.JModel;

import java.math.BigDecimal;
import java.util.*;

abstract public class BLJStore<T> {
    public List<JModel> data;
    public Map<String, Object> uuidCache;
    private static Map<String, BLJStore> stores;

    public static BLJStore instance(Class<? extends BLJStore> klass) {
        if (stores == null) {
            stores = Maps.newHashMap();
        }

        if (!stores.containsKey(klass.getName())) {
            try {
                stores.put(klass.getName(), klass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return stores.get(klass.getName());
    }

    public void load() {
        this.data = Lists.newArrayList();

        if (this.data.isEmpty()) {
            this.setupDefaults();
        }

        this.buildUuidCache();
        this.onLoad();
    }

    protected void onLoad() {
    }

    private void buildUuidCache() {
        this.uuidCache = Maps.newHashMap();
        for (JModel model : this.data) {
            this.uuidCache.put(model.uuid, model);
        }
    }

    public Object create() {
        JModel object = null;
        try {
            object = (JModel) this.modelClass().newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        this.addUuid(object);
        this.data.add(object);
        this.setDefaultsForObject(object);

        return object;
    }

    abstract public Class modelClass();

    public void empty() {

    }

    public void addUuid(JModel model) {
        model.uuid = UUID.randomUUID().toString();
    }

    public void setupDefaults() {

    }

    public void setDefaultsForObject(JModel object) {
    }

    public void removeAll() {

    }

    public void remove(Object object) {

    }

    public void removeAtIndex(int index) {

    }

    public void reset() {

    }

    public Object first() {
        return null;
    }

    public Object last() {
        return null;
    }

    public Object find(String name, Object value) {
        return null;
    }

    public List<Object> findAll() {
        return Collections.emptyList();
    }

    public List<Object> findAllWhere(String name, Object value) {
        return Collections.emptyList();
    }

    public Object atIndex(int index) {
        return null;
    }

    public BigDecimal max(String property) {
        return null;
    }

    public int count() {
        return 0;
    }

    public TreeSet<? extends Comparable> unique(String property) {
        return Sets.newTreeSet();
    }
}
