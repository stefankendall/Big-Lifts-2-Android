package com.stefankendall.BigLifts.views.fto.settings;

import com.google.common.collect.*;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;

import java.util.List;
import java.util.Map;

public class RoundingTypeCell extends SpinnerCell {
    @Override
    protected List<String> options() {
        return Lists.newArrayList(JSettings.ROUNDING_TYPE_DOWN, JSettings.ROUNDING_TYPE_NORMAL, JSettings.ROUNDING_TYPE_UP);
    }

    @Override
    protected String label() {
        return "Rounding Type";
    }

    @Override
    protected int defaultSelection() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Map<String, Integer> selectionToPosition = typePositionMap();
        return selectionToPosition.get(settings.roundingType);
    }

    private BiMap<String, Integer> typePositionMap() {
        return ImmutableBiMap.<String, Integer>builder()
                .put(JSettings.ROUNDING_TYPE_DOWN, 0)
                .put(JSettings.ROUNDING_TYPE_NORMAL, 1)
                .put(JSettings.ROUNDING_TYPE_UP, 2).build();
    }

    @Override
    protected void valueChanged(int position) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.roundingType = typePositionMap().inverse().get(position);
    }
}
