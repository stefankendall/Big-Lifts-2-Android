package com.stefankendall.BigLifts.views.cells;

import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class SetCellFactory {
    public static CustomListItem create(JSet set) {
        if (SetCellFactory.shouldUseBarLoading()) {
            return new SetCellWithPlates(set);
        }
        return new SetCell(set);
    }

    private static boolean shouldUseBarLoading() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return settings.barLoadingEnabled;
    }
}
