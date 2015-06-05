package com.stefankendall.BigLiftsPro.views.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;

public class SimpleListItemWithSubtitle implements CustomListItem {
    private String text;
    private String subtitle;

    public SimpleListItemWithSubtitle(String text, String subtitle) {
        this.text = text;
        this.subtitle = subtitle;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.simple_list_item_with_subtitle, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.text);
        if (textView != null) {
            textView.setText(this.text);

            TextView subtitleView = (TextView) view.findViewById(R.id.subtitle);
            subtitleView.setText(this.subtitle);
        }
        return view;
    }
}
