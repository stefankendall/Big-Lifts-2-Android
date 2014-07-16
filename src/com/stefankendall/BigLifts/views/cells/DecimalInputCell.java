package com.stefankendall.BigLifts.views.cells;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;

public abstract class DecimalInputCell extends NumberInputCell {
    @Override
    protected int getLayoutResource() {
        return R.layout.decimal_input_cell;
    }

    public BigDecimal getValue() {
        return BigDecimals.parse(this.input.getText().toString());
    }
}
