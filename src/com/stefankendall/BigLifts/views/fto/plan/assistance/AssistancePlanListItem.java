package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.view.View;
import android.widget.ImageView;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.fto.plan.PlanListItem;

public class AssistancePlanListItem extends PlanListItem {
    public AssistancePlanListItem(String templateLabel, String description, String variant) {
        super(templateLabel, description, variant);
    }

    protected void setCheckMarkState(ImageView checkmark) {
        checkmark.setVisibility(View.VISIBLE);
        checkmark.setVisibility(View.INVISIBLE);
    }
}
