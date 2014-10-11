package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.util.List;

public class FTOBoringButBigEditCell implements CustomListItem {
    private final JFTOBoringButBigLift boringButBigLift;

    public FTOBoringButBigEditCell(JFTOBoringButBigLift boringButBigLift) {
        this.boringButBigLift = boringButBigLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.boring_but_big_edit_cell, null);
        }

        TextView liftNameView = (TextView) view.findViewById(R.id.lift_name);
        if (liftNameView != null) {
            liftNameView.setText(this.boringButBigLift.mainLift.name);
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(App.getContext(),
                    R.layout.spinner_list_item, this.getFtoLiftNames());
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_list_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(this.getDefaultSelection());
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    FTOBoringButBigEditCell.this.valueChanged(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        return view;
    }

    private void valueChanged(int position) {
        this.boringButBigLift.boringLift = (JFTOLift) JFTOLiftStore.instance().atIndex(position);
    }

    private int getDefaultSelection() {
        return JFTOLiftStore.instance().findAll().indexOf(this.boringButBigLift.boringLift);
    }

    private List<CharSequence> getFtoLiftNames() {
        List<CharSequence> ftoLiftNames = Lists.newArrayList();
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift jftoLift = (JFTOLift) model;
            ftoLiftNames.add(jftoLift.name);
        }
        return ftoLiftNames;
    }
}
