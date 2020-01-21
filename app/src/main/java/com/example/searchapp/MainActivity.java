package com.example.searchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // I create ListView and adapter global in the main class to
    // can access outside of onCreate method.

    ListView search_categories;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_categories = (ListView)findViewById(R.id.search_categories);
        //ListView and ArrayAdapter reCycle View
        ArrayList<String> arrayCategories = new ArrayList<>();
        //use string file to store data. array called my_menu
        arrayCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.my_menu)));
        adapter = new ArrayAdapter<String>(
                //layout_1 has one textView and it's the default one to be used.
                MainActivity.this,android.R.layout.simple_list_item_1,arrayCategories
        );

        search_categories.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_categories);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}
