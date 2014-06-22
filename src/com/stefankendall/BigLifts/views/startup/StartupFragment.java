package com.stefankendall.BigLifts.views.startup;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class StartupFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        getActivity().setTitle("Choose a program!");
        this.setListAdapter(new StartupListAdapter());
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
