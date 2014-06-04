package com.stefankendall.BigLifts.data.models;

import com.google.common.collect.Lists;

import java.util.List;

abstract public class JModel {
    public String uuid;

    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList();
    }
}
