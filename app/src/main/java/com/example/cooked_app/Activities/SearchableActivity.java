package com.example.cooked_app.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooked_app.Adapter.MyAdapter;
import com.example.cooked_app.Adapter.OnRecipeClickListener;
import com.example.cooked_app.Database.CookED_DB;
import com.example.cooked_app.R;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchableActivity extends AppCompatActivity {
    private CookED_DB DB;
    private RecyclerView recyclerView;
    MyAdapter adapter;
    static MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    // TODO: Make Suggested hints clickable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // init View
        recyclerView = findViewById(R.id.recycler_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = findViewById(R.id.search_bar);

        // init DB
        DB = new CookED_DB(this);

        // setup search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        adapter = new MyAdapter(this, DB.getRecipes());
        recyclerView.setAdapter(adapter);
        loadSuggestList();
        textListener();
        performSearch();
    }

    private void performSearch() {
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

    private void textListener() {
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void startSearch(String text) {
        adapter = new MyAdapter(this, DB.getRecipeByName(text));
        recyclerView.setAdapter(adapter);
        OnRecipeClickListener clickListener = new OnRecipeClickListener(this);
        adapter.setOnItemClickListener(clickListener);
    }

    private void loadSuggestList() {
        suggestList = DB.getAllRecipes();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}
