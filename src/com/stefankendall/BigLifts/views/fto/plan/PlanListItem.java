package com.stefankendall.BigLifts.views.fto.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class PlanListItem implements CustomListItem {
    private final String templateLabel;
    private final String description;
    public final String variant;

    public PlanListItem(String templateLabel, String description, String variant) {
        this.templateLabel = templateLabel;
        this.description = description;
        this.variant = variant;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.plan_list_item, null);
        }

        TextView templateLabel = (TextView) view.findViewById(R.id.template_name);
        if (templateLabel != null) {
            TextView description = (TextView) view.findViewById(R.id.description);
            ImageView checkmark = (ImageView) view.findViewById(R.id.checkmark);

            templateLabel.setText(this.templateLabel);
            description.setText(this.description);

            JFTOVariant jftoVariant = (JFTOVariant) JFTOVariantStore.instance().first();
            if (jftoVariant.name.equals(this.variant)) {
                checkmark.setVisibility(View.VISIBLE);
            } else {
                checkmark.setVisibility(View.INVISIBLE);
            }
        }
        return view;
    }
}
