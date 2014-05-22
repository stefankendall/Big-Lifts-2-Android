package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

abstract public class BLJStore {
    public List<Object> data;
    public Map<String, Object> uuidCache;

    public Object create() {
        return null;
    }

    public void empty() {

    }

    public void setupDefaults() {

    }

    public void setDefaultsForObject(Object object) {
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

    public BLJStore instance() {
        return null;
    }
}
