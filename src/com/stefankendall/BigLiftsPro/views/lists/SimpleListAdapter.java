package com.stefankendall.BigLiftsPro.views.lists;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.stefankendall.BigLiftsPro.App;

import java.util.List;
import java.util.Map;

public abstract class SimpleListAdapter extends BaseAdapter {
    protected FragmentActivity activity;
    protected List<CustomListItem> items;

    public SimpleListAdapter() {
    }

    public SimpleListAdapter(FragmentActivity context) {
        this.items = buildItems();
        this.activity = context;
    }

    public void reload() {
        this.items.clear();
        this.items.addAll(this.buildItems());
        notifyDataSetChanged();
    }

    public abstract List<CustomListItem> buildItems();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        int count = Sets.newHashSet(Iterables.transform(items, new Function<CustomListItem, Class>() {
            @Override
            public Class apply(CustomListItem item) {
                return item.getClass();
            }
        })).size();
        return count == 0 ? 1 : count;
    }

    @Override
    public int getItemViewType(int position) {
        Map<Class, Integer> typePositionMap = this.getTypePositionMap();
        return typePositionMap.get(this.items.get(position).getClass());
    }

    private Map<Class, Integer> getTypePositionMap() {
        Map<Class, Integer> positionMap = Maps.newHashMap();
        int id = 0;
        for (CustomListItem item : this.items) {
            Class itemClass = item.getClass();
            if (!positionMap.keySet().contains(itemClass)) {
                positionMap.put(itemClass, id++);
            }
        }
        return positionMap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        CustomListItem item = items.get(position);
        LayoutInflater inflater = this.activity == null ? LayoutInflater.from(App.getContext()) : this.activity.getLayoutInflater();
        return item.fillView(convertView, inflater);
    }
}
