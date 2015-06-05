package com.stefankendall.BigLiftsPro.views.fto.plan;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.stores.JPurchaseStore;

public class IapPlanListItem extends PlanListItem {
    private final boolean shouldShowIapText;

    public IapPlanListItem(String templateLabel, String description, String variant, boolean shouldShowIapText) {
        super(templateLabel, description, variant);
        this.shouldShowIapText = shouldShowIapText;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.iap_plan_list_item, null);
        }

        TextView templateLabel = (TextView) view.findViewById(R.id.template_name);
        if (templateLabel != null) {
            setData(view);
            setCheckMarkState(view);

            if (!this.shouldShowIapText) {
                view.findViewById(R.id.overlay_text).setVisibility(View.GONE);
            }
            else {
                view.findViewById(R.id.overlay_text).setVisibility(View.VISIBLE);
            }

            View overlay = view.findViewById(R.id.overlay);
            if (JPurchaseStore.instance().hasPurchasedEverything()) {
                overlay.setVisibility(View.GONE);
            } else {
                overlay.setVisibility(View.VISIBLE);
                overlay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return true;
                    }
                });
            }
        }
        return view;
    }
}
