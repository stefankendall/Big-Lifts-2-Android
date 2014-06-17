package com.stefankendall.BigLifts.data.stores;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.data.ObjectHelper;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.Orderable;

import java.util.*;

abstract public class BLJStore {
    public List<JModel> data;
    public Map<String, Object> uuidCache;
    private static Map<String, BLJStore> stores;
    private static Gson gson;

    public BLJStore() {
        this.data = Lists.newCopyOnWriteArrayList();
    }

    public synchronized static BLJStore instance(Class<? extends BLJStore> klass) {
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
            object = this.modelClass().newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        this.addUuid(object);
        this.data.add(object);
        this.setDefaultsForObject(object);

        return object;
    }

    abstract public Class<? extends JModel> modelClass();

    public void empty() {
        this.data = Lists.newCopyOnWriteArrayList();
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
        while (!this.data.isEmpty()) {
            this.remove(this.data.get(0));
        }
    }

    public void remove(JModel model) {
        this.data.remove(model);
        this.uuidCache.remove(model.uuid);
        this.removeCascadeAssocations(model);
    }

    private void removeCascadeAssocations(JModel model) {
        for (String property : model.cascadeDeleteProperties()) {
            Object value = ObjectHelper.getProperty(model, property);
            if (value == null) {
                continue;
            }

            if (value instanceof List) {
                List<JModel> models = (List<JModel>) value;
                for (JModel association : models) {
                    BLJStoreManager.instance().storeForModel(association.getClass(), association.uuid).remove(association);
                }
            }
            else {
                JModel association = (JModel) value;
                BLJStoreManager.instance().storeForModel(association.getClass(), association.uuid).remove(association);
            }
        }
    }

    public void removeAtIndex(int index) {
        this.remove(this.data.get(index));
    }

    public void reset() {
        this.empty();
        this.setupDefaults();
    }

    public JModel first() {
        return this.count() == 0 ? null : this.findAll().get(0);
    }

    public JModel last() {
        List all = this.findAll();
        return this.count() == 0 ? null : (JModel) all.get(all.size() - 1);
    }

    public Object find(final String name, final Object value) {
        return Iterables.find(this.data, nameValuePredicate(name, value));
    }

    public synchronized List<? extends JModel> findAll() {
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

    public JModel findBy(Predicate<? super JModel> function) {
        return Iterables.find(this.data, function);
    }

    public synchronized List<? extends JModel> findAllWhere(final String name, final Object value) {
        Iterable<JModel> filteredData = Iterables.filter(this.data, nameValuePredicate(name, value));
        return ImmutableList.copyOf(filteredData);
    }

    private Predicate<JModel> nameValuePredicate(final String name, final Object value) {
        return new Predicate<JModel>() {
            @Override
            public boolean apply(JModel model) {
                return value.equals(ObjectHelper.getProperty(model, name));
            }
        };
    }

    public Object atIndex(int index) {
        return this.data.get(index);
    }

    public Comparable max(final String property) {
        if (this.count() == 0) {
            return null;
        }

        List<Comparable> values = Lists.newArrayList(Lists.transform(this.data, new Function<JModel, Comparable>() {
            @Override
            public Comparable apply(JModel model) {
                return (Comparable) ObjectHelper.getProperty(model, property);
            }
        }));

        Collections.sort(values);
        return values.get(values.size() - 1);
    }

    public int count() {
        return this.data.size();
    }

    public TreeSet<? extends Comparable> unique(final String property) {
        return Sets.newTreeSet(Iterables.transform(this.data, new Function<JModel, Comparable>() {
            @Override
            public Comparable apply(JModel jModel) {
                return (Comparable) ObjectHelper.getProperty(jModel, property);
            }
        }));
    }

    public List<String> serialize() {
        return Lists.transform(this.data, new Function<JModel, String>() {
            @Override
            public String apply(JModel model) {
                return gson.toJson(model);
            }
        });
    }

    public void sync() {
        //todo
    }
}
