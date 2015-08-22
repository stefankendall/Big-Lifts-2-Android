package com.stefankendall.BigLifts.views.cells;

import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class SetCellFactory {
    public static CustomListItem create(JSet set, SetChange setChange) {
        if (SetCellFactory.shouldUseBarLoading()) {
            return new SetCellWithPlates(set, setChange);
        }
        return new SetCell(set, setChange);
    }

    private static boolean shouldUseBarLoading() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return JPurchaseStore.instance().hasPurchasedEverything() && settings.barLoadingEnabled;
    }
}
