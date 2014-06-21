package com.stefankendall.BigLifts.views;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class StartupListAdapter extends ArrayAdapter<String> {
    public StartupListAdapter(Context context, List<String> programs) {
        super(context, android.R.layout.simple_list_item_1, programs);
    }
}

