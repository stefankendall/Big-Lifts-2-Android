package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JCurrentProgram;
import com.stefankendall.BigLifts.data.models.JModel;

public class JCurrentProgramStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JCurrentProgram.class;
    }

    public static JCurrentProgramStore instance() {
        return (JCurrentProgramStore) BLJStore.instance(JCurrentProgramStore.class);
    }
}
