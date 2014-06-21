package com.stefankendall.BigLifts.views;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.R;

public class StartupFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        getActivity().setTitle("Choose a program!");
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        this.setListAdapter(new StartupListAdapter(getActivity(), Lists.newArrayList("5/3/1", "What's next?", "lbs/kg")));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i("tag", "click");
//        String pokemon = (String) this.getListAdapter().getItem(position);
//        Intent intent = new Intent(getActivity(), PokemonTypeViewActivity.class);
//        intent.putExtra(PokemonTypeViewFragment.EXTRA_POKEMON_NAME, pokemon);
//        startActivity(intent);
    }
}
