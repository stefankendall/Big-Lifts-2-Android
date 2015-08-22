package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JCurrentProgram;
import com.stefankendall.BigLifts.data.models.JModel;

import java.util.List;

public class JCurrentProgramStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JCurrentProgram.class;
    }

    @Override
    public void setupDefaults() {
        this.create();
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public static JCurrentProgramStore instance() {
        return (JCurrentProgramStore) BLJStore.instance(JCurrentProgramStore.class);
    }

    public void clearProgram() {
        JCurrentProgram program = (JCurrentProgram) this.first();
        program.name = null;
    }
}
