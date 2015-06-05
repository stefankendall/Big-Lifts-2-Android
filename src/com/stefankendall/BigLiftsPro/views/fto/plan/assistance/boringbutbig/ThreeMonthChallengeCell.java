package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;

public class ThreeMonthChallengeCell extends SwitchCell {
    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.boring_but_big_toggle_cell, null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            setToggleValues(view);
        }

        return view;
    }

    @Override
    protected String label() {
        return "3-month challenge";
    }

    @Override
    protected boolean defaultState() {
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        return boringButBig.threeMonthChallenge;
    }

    @Override
    protected void valueChanged(boolean state) {
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        boringButBig.threeMonthChallenge = state;
    }
}
