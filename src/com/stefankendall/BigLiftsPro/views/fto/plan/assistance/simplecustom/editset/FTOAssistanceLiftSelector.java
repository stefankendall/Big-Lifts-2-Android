package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editset;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLiftsPro.views.cells.SpinnerCell;

import java.util.List;

public class FTOAssistanceLiftSelector extends SpinnerCell {
    private final JSet set;

    public FTOAssistanceLiftSelector(JSet set) {
        this.set = set;
    }

    @Override
    protected List<String> options() {
        List<String> options = Lists.newArrayList();
        for (JModel model : JFTOLiftStore.instance().findAll()) {
            JFTOLift lift = (JFTOLift) model;
            options.add(lift.name);
        }

        for (JModel model : JFTOCustomAssistanceLiftStore.instance().findAll()) {
            JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) model;
            options.add(lift.name);
        }

        return options;
    }

    @Override
    protected String label() {
        return "Lift";
    }

    @Override
    protected int defaultSelection() {
        List<? extends JModel> ftoLifts = JFTOLiftStore.instance().findAll();
        List<? extends JModel> customLifts = JFTOCustomAssistanceLiftStore.instance().findAll();
        if (ftoLifts.contains(this.set.lift)) {
            return ftoLifts.indexOf(this.set.lift);
        } else if (customLifts.contains(this.set.lift)) {
            return ftoLifts.size() + customLifts.indexOf(this.set.lift);
        }
        return 0;
    }

    @Override
    protected void valueChanged(int position) {
        List<JFTOLift> ftoLifts = (List<JFTOLift>) JFTOLiftStore.instance().findAll();
        if (position < ftoLifts.size()) {
            this.set.lift = (JLift) JFTOLiftStore.instance().atIndex(position);
        }
        else {
            int customLiftPosition = position - ftoLifts.size();
            this.set.lift = (JLift) JFTOCustomAssistanceLiftStore.instance().atIndex(customLiftPosition);
        }
    }
}
