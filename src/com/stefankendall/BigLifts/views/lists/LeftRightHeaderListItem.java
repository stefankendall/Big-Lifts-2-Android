package com.stefankendall.BigLifts.views.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;

public class LeftRightHeaderListItem implements CustomListItem {
    private final String left;
    private final String right;

    public LeftRightHeaderListItem(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_header_left_right, null);
        }

        TextView textViewLeft = (TextView) view.findViewById(R.id.header_left);
        TextView textViewRight= (TextView) view.findViewById(R.id.header_right);
        if (textViewLeft != null) {
            textViewLeft.setText(this.left);
            textViewRight.setText(this.right);
        }
        return view;
    }
}
