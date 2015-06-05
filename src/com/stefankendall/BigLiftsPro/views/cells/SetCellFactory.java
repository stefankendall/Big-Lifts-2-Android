package com.stefankendall.BigLiftsPro.views.cells;

import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JPurchaseStore;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

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
