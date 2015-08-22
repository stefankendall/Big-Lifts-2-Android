package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class LeftRightTextCell implements CustomListItem {

    private final String leftText;
    private final String rightText;

    public LeftRightTextCell(String leftText, String rightText) {
        this.leftText = leftText;
        this.rightText = rightText;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.left_right_text_cell, null);
        }

        TextView leftLabel = (TextView) view.findViewById(R.id.leftLabel);
        TextView rightLabel = (TextView) view.findViewById(R.id.rightLabel);
        if (leftLabel != null) {
            leftLabel.setText(leftText);
            rightLabel.setText(rightText);
        }

        return view;
    }
}
