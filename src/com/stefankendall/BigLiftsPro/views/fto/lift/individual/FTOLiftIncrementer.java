package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;

public class FTOLiftIncrementer {
    public static void incrementLifts() {
        JFTOLiftStore.instance().incrementLifts();
    }
}
