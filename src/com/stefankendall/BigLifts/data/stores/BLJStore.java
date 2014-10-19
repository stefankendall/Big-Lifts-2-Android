package com.stefankendall.BigLifts.data.stores;

import android.content.SharedPreferences;
import com.crashlytics.android.Crashlytics;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.data.ObjectHelper;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.Orderable;
import com.stefankendall.BigLifts.data.models.json.JModelDeserializer;
import com.stefankendall.BigLifts.data.models.json.JModelSerializer;

import java.util.*;

abstract public class BLJStore {
    public List<JModel> data;
    public Map<String, Object> uuidCache;
    private static Map<String, BLJStore> stores;

    public BLJStore() {
        this.data = Lists.newCopyOnWriteArrayList();
    }

    public synchronized static BLJStore instance(Class<? extends BLJStore> klass) {
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

    protected void onLoad() {
    }

    private void buildUuidCache() {
        for (JModel model : this.data) {
            this.uuidCache.put(model.uuid, model);
        }
    }

    public Object create() {
        JModel object = null;
        try {
            object = this.checkedModelClass().newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        this.addUuid(object);
        this.data.add(object);
        this.setDefaultsForObject(object);

        return object;
    }

    abstract public Class<? extends JModel> modelClass();

    public Class<? extends JModel> checkedModelClass() {
        Class<? extends JModel> klass = modelClass();
        assert ModelTypeListFactory.contains(klass);
        return klass;
    }

    public void empty() {
        this.data = Lists.newCopyOnWriteArrayList();
        this.uuidCache = Maps.newHashMap();
    }

    public void addUuid(JModel model) {
        model.uuid = UUID.randomUUID().toString();
        this.uuidCache.put(model.uuid, model);
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
        model.dead = true;
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
            } else {
                JModel association = (JModel) value;
                BLJStoreManager.instance().storeForModel(association.getClass(), association.uuid).remove(association);
            }
        }
    }

    public void removeAtIndex(int index) {
        this.remove(this.findAll().get(index));
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

    public JModel find(final String name, final Object value) {
        return Iterables.find(this.data, nameValuePredicate(name, value), null);
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
        List<JModel> all = (List<JModel>) this.findAll();
        Iterable<JModel> filteredData = Iterables.filter(all, nameValuePredicate(name, value));
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
        return this.findAll().get(index);
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
                return BLJStore.this.serializeObject(model);
            }
        });
    }

    protected String serializeObject(JModel model) {
        return BLJStore.this.getGson().toJson(model);
    }

    public Gson getGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        for (Class klass : this.getAssociations()) {
            gsonBuilder.registerTypeAdapter(klass, new JModelSerializer());
            gsonBuilder.registerTypeAdapter(klass, new JModelDeserializer());
        }
        return gsonBuilder.create();
    }

    abstract protected List<Class> getAssociations();

    public void sync() {
        SharedPreferences.Editor editor = App.getSharedPreferencesEditor();
        editor.putString(this.keyNameForStore(), serializedAsJson());
    }

    public String serializedAsJson() {
        return "[" + Joiner.on(',').join(this.serialize()) + "]";
    }

    public void load() {
        Crashlytics.log("Loading store: " + this.getClass().getName());
        this.data = (List<JModel>) this.loadDataFromStore();
        this.uuidCache = Maps.newHashMap();

        if (this.data.isEmpty()) {
            this.setupDefaults();
        }

        this.buildUuidCache();
        this.onLoad();
    }

    public List<? extends JModel> loadDataFromStore() {
        SharedPreferences sharedPreferences = App.getSharedPreferences();
        String values = sharedPreferences.getString(this.keyNameForStore(), "[]");
        Crashlytics.log(values);

        return this.deserialize(values);
    }

    public List<? extends JModel> deserialize(String values) {
        TypeToken typeToDeserialize = ModelTypeListFactory.forClass(this.checkedModelClass());
        Crashlytics.log("Deserializing type: " + typeToDeserialize.toString());
        return getGson().fromJson(values, typeToDeserialize.getType());
    }

    protected String keyNameForStore() {
        return this.getClass().getSimpleName();
    }
}
