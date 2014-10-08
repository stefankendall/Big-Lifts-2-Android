package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;

public class FTOLiftIncrementer {
    public static void incrementLifts() {
        JFTOLiftStore.instance().incrementLifts();
    }
}
