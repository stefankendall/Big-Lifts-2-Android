package com.stefankendall.BigLifts.views.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.stefankendall.BigLifts.App;

import java.util.List;
import java.util.Map;

public abstract class SimpleListAdapter extends BaseAdapter {
    protected List<? extends CustomListItem> items;

    public SimpleListAdapter() {
        this.items = buildItems();
    }

    public abstract List<? extends CustomListItem> buildItems();

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
        return item.fillView(convertView, LayoutInflater.from(App.getContext()));
    }
}
