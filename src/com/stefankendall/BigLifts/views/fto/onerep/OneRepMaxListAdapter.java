package com.stefankendall.BigLifts.views.fto.onerep;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.allprograms.formulas.OneRepEstimator;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedReadOnlyDecimalCell;
import com.stefankendall.BigLifts.views.cells.ReadOnlyDecimalCell;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.math.BigDecimal;
import java.util.List;

public class OneRepMaxListAdapter extends SimpleListAdapter {

    protected ParameterizedDecimalInputCell weight;
    protected ParameterizedIntegerInputCell reps;
    protected ReadOnlyDecimalCell oneRepMaxEstimate;
    protected FormulaSelectorCell formulaSelector;
    private FormulaDisplayCell formulaDisplay;
    private MaleFemaleCell maleFemaleCell;
    private BodyweightCell bodyweightCell;
    private WilksCell wilksCell;

    public OneRepMaxListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        this.weight = new ParameterizedDecimalInputCell("Weight", "", "300");
        this.reps = new ParameterizedIntegerInputCell("Reps", "", "5");
        FieldWatcher oneRepWatcher = new FieldWatcher() {
            @Override
            public void fieldChanged() {
                OneRepMaxListAdapter.this.oneRepValuesChanged();
            }
        };
        this.weight.setFieldWatcher(oneRepWatcher);
        this.reps.setFieldWatcher(oneRepWatcher);
        this.oneRepMaxEstimate = new ParameterizedReadOnlyDecimalCell("Estimated Max");
        this.formulaDisplay = new FormulaDisplayCell();

        this.formulaSelector = new FormulaSelectorCell(new FieldWatcher() {
            @Override
            public void fieldChanged() {
                OneRepMaxListAdapter.this.formulaChanged();
            }
        });

        this.maleFemaleCell = new MaleFemaleCell(new FieldWatcher() {
            @Override
            public void fieldChanged() {
                OneRepMaxListAdapter.this.maleFemaleChanged();
            }
        });

        this.bodyweightCell = new BodyweightCell("Bodyweight", "");
        this.wilksCell = new WilksCell();

        return Lists.newArrayList(
                this.weight,
                this.reps,
                this.oneRepMaxEstimate,
                this.formulaSelector,
                this.formulaDisplay,
                this.maleFemaleCell,
                this.bodyweightCell,
                this.wilksCell
        );
    }

    protected void oneRepValuesChanged() {
        if (this.weight.isEmpty() || this.reps.isEmpty()) {
            this.oneRepMaxEstimate.setValue("");
        }

        BigDecimal estimate = getEstimate();
        if (estimate.compareTo(BigDecimal.ZERO) > 0) {
            this.oneRepMaxEstimate.setValue(BigDecimals.print(estimate));
        } else {
            this.oneRepMaxEstimate.setValue("");
        }

        this.updateWilks();
    }

    private BigDecimal getEstimate() {
        BigDecimal weight = this.weight.getValue();
        int count = this.reps.getValue();
        return OneRepEstimator.estimate(weight, count);
    }

    protected void formulaChanged() {
        this.oneRepValuesChanged();
        this.formulaDisplay.update();
    }

    protected void maleFemaleChanged() {
        this.updateWilks();
    }

    private void updateWilks() {
        BigDecimal weight = getEstimate();
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        this.wilksCell.update(weight, settings.bodyweight);
    }
}
