package com.stefankendall.BigLifts.views.nav;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class NavListItem implements CustomListItem {
    private final String text;
    private int drawable;
    private NavAction navAction;

    public NavListItem(String text, int drawable, NavAction navAction) {
        this.text = text;
        this.drawable = drawable;
        this.navAction = navAction;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.nav_menu_list_item, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.menu_item_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.menu_item_image);

        if (textView != null) {
            textView.setText(this.text);
            imageView.setImageDrawable(App.getContext().getResources().getDrawable(this.drawable));
        }

        return view;
    }

    public NavAction getNavAction() {
        return navAction;
    }
}