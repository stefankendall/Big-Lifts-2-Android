package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.util.List;

public abstract class SpinnerCell implements CustomListItem {

    protected FieldWatcher fieldWatcher;

    public SpinnerCell() {
    }

    public SpinnerCell(FieldWatcher fieldWatcher) {
        this.fieldWatcher = fieldWatcher;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_cell, null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(App.getContext(), R.layout.spinner_list_item, Lists.newArrayList(Iterables.filter(this.options(), CharSequence.class)));
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_list_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(this.defaultSelection());

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    SpinnerCell.this.valueChanged(position);
                    if (SpinnerCell.this.fieldWatcher != null) {
                        fieldWatcher.fieldChanged();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        return view;
    }

    protected abstract List<String> options();

    protected abstract String label();

    protected abstract int defaultSelection();

    abstract protected void valueChanged(int position);
}
