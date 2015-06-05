package com.stefankendall.BigLiftsPro.data.models;

import com.google.common.collect.Lists;

import java.util.List;

abstract public class JModel {
    public String uuid;
    public transient boolean dead;

    public boolean isDead(){
        return this.dead;
    }

    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList();
    }
}
