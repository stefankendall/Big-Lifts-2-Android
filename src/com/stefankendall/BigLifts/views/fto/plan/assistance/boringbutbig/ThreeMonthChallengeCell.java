package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLifts.views.cells.SwitchCell;

public class ThreeMonthChallengeCell extends SwitchCell {
//    @Override
//    public View fillView(View view, LayoutInflater inflater) {
//        if (view == null) {
//            view = inflater.inflate(R.layout.switch_cell, null);
//        }
//
//        TextView labelView = (TextView) view.findViewById(R.id.label);
//        if (labelView != null) {
//            labelView.setText(this.label());
//            Switch toggle = (Switch) view.findViewById(R.id.toggle_switch);
//            toggle.setTextOn(this.switchOnText());
//            toggle.setTextOff(this.switchOffText());
//            toggle.setChecked(this.defaultState());
//            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    SwitchCell.this.valueChanged(b);
//                }
//            });
//        }
//
//        return view;
//    }

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
