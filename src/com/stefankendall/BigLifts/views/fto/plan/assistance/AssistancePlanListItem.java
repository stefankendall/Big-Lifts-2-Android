package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.view.View;
import android.widget.ImageView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLifts.data.stores.fto.JFTOAssistanceStore;
import com.stefankendall.BigLifts.views.fto.plan.PlanListItem;

public class AssistancePlanListItem extends PlanListItem {
    public AssistancePlanListItem(String templateLabel, String description, String variant, Class segue) {
        super(templateLabel, description, variant, segue);
    }

    protected void setCheckMarkState(View v) {
        ImageView checkmark = (ImageView) v.findViewById(R.id.checkmark);
        JFTOAssistance assistance = (JFTOAssistance) JFTOAssistanceStore.instance().first();
        if (variant.equals(assistance.name)) {
            checkmark.setVisibility(View.VISIBLE);
        } else {
            checkmark.setVisibility(View.GONE);
        }
    }
}
