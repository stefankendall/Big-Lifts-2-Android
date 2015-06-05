package com.stefankendall.BigLiftsPro.views.fto.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOVariant;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

public class PlanListItem implements CustomListItem {
    private final String templateLabel;
    private final String description;
    public final String variant;
    public Class segue;

    public PlanListItem(String templateLabel, String description, String variant, Class segue) {
        this.templateLabel = templateLabel;
        this.description = description;
        this.variant = variant;
        this.segue = segue;
    }

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
            setData(view);
            setCheckMarkState(view);
        }
        return view;
    }

    protected void setData(View v) {
        TextView templateLabel = (TextView) v.findViewById(R.id.template_name);
        TextView description = (TextView) v.findViewById(R.id.description);

        templateLabel.setText(this.templateLabel);
        description.setText(this.description);
    }

    protected void setCheckMarkState(View v) {
        ImageView checkmark = (ImageView) v.findViewById(R.id.checkmark);
        JFTOVariant jftoVariant = (JFTOVariant) JFTOVariantStore.instance().first();
        if (jftoVariant.name.equals(this.variant)) {
            checkmark.setVisibility(View.VISIBLE);
        } else {
            checkmark.setVisibility(View.INVISIBLE);
        }
    }
}
