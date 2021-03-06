package com.stefankendall.BigLiftsPro.views.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;

public class HeaderListItem implements CustomListItem {
    public String value;

    public HeaderListItem(String value) {
        this.value = value;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_header, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.header);
        if (textView != null) {
            textView.setText(this.value);
        }
        return view;
    }
}
