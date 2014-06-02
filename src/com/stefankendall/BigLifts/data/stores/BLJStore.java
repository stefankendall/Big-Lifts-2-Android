package com.stefankendall.BigLifts.data.stores;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.Orderable;

import java.math.BigDecimal;
import java.util.*;

abstract public class BLJStore<T> {
    public List<JModel> data;
    public Map<String, Object> uuidCache;
    private static Map<String, BLJStore> stores;
    private static Gson gson;

    public static BLJStore instance(Class<? extends BLJStore> klass) {
        if (stores == null) {
            gson = new Gson();
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
        this.data = Lists.newArrayList();
        this.uuidCache = Maps.newHashMap();
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
        return this.count() == 0 ? null : this.findAll().get(0);
    }

    public Object last() {
        List<JModel> all = this.findAll();
        return this.count() == 0 ? null : all.get(all.size() - 1);
    }

    public Object find(final String name, final Object value) {
        return Iterables.find(this.data, new Predicate<JModel>() {
            @Override
            public boolean apply(JModel model) {
                Map<String, Object> values = (Map<String, Object>) gson.fromJson(gson.toJsonTree(model), Map.class);
                return values.get(name) == value;
            }
        });
    }

    public List<JModel> findAll() {
        if (Orderable.class.isAssignableFrom(this.modelClass())) {
            Ordering<Orderable> byOrderOrdering = new Ordering<Orderable>() {
                @Override
                public int compare(Orderable orderable, Orderable orderable2) {
                    return orderable.getOrder() - orderable2.getOrder();
                }
            };

            ImmutableList<Orderable> ordered = byOrderOrdering.immutableSortedCopy(Iterables.filter(this.data, Orderable.class));
            return ImmutableList.copyOf(Iterables.filter(ordered, JModel.class));
        } else {
            return ImmutableList.copyOf(this.data);
        }
    }

    public List<JModel> findAllWhere(String name, Object value) {
        return Collections.emptyList();
    }

    public Object atIndex(int index) {
        return null;
    }

    public BigDecimal max(String property) {
        return null;
    }

    public int count() {
        return this.data.size();
    }

    public TreeSet<? extends Comparable> unique(String property) {
        return Sets.newTreeSet();
    }

    public List<String> serialize() {
        return Lists.transform(this.data, new Function<JModel, String>() {
            @Override
            public String apply(JModel model) {
                return gson.toJson(model);
            }
        });
    }
}
