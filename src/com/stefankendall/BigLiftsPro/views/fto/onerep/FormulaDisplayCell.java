package com.stefankendall.BigLiftsPro.views.fto.onerep;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.common.collect.ImmutableMap;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

import java.util.Map;

public class FormulaDisplayCell implements CustomListItem {
    private TextView formula;

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.formula_display_cell, null);
        }

        this.formula = (TextView) view.findViewById(R.id.formula);
        if (formula != null) {
            this.update();
        }

        return view;
    }

    public void update() {
        Map<String, String> formulas = ImmutableMap.<String, String>builder()
                .put(JSettings.ROUNDING_FORMULA_EPLEY, "1RM = w(1 + r/30)")
                .put(JSettings.ROUNDING_FORMULA_BRZYCKI, "1RM = w(36/(37-r))")
                .build();

        JSettings settings = (JSettings) JSettingsStore.instance().first();
        String formulaText = formulas.get(settings.roundingFormula);
        this.formula.setText(formulaText);
    }
}
