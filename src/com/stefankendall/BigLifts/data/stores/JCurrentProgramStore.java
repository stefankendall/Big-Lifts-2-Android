package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JCurrentProgram;
import com.stefankendall.BigLifts.data.models.JModel;

public class JCurrentProgramStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JCurrentProgram.class;
    }

    @Override
    public void setupDefaults() {
        this.create();
    }

    public static JCurrentProgramStore instance() {
        return (JCurrentProgramStore) BLJStore.instance(JCurrentProgramStore.class);
    }

    public void clearProgram() {
        JCurrentProgram program = (JCurrentProgram) this.first();
        program.name = null;
    }
}
