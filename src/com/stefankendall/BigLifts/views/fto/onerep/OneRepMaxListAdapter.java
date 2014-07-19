package com.stefankendall.BigLifts.views.fto.onerep;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class OneRepMaxListAdapter extends SimpleListAdapter {

    private ParameterizedDecimalInputCell weight;
    private ParameterizedIntegerInputCell reps;

    public OneRepMaxListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        this.weight = new ParameterizedDecimalInputCell("Weight", "", "300");
        this.reps = new ParameterizedIntegerInputCell("Reps", "", "5");
        return Lists.newArrayList(
                this.weight,
                this.reps
        );
    }
}
