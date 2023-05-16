package com.example.cooked_app.ui.recipes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cooked_app.Adapter.RecipeAdapter;
import com.example.cooked_app.Database.CookED_DB;
import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {

    private RecipesViewModel mViewModel;
    private CookED_DB DB;

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe_Model> recipeList;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DB = new CookED_DB(getActivity().getApplicationContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        // Set up the Spinner and its adapter
        Spinner categorySpinner = view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.recipe_categories,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        // Set up the RecyclerView and its adapter
        RecyclerView recyclerView = view.findViewById(R.id.recipes_recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // TODO: Use layout for recipeDetailsActivity

        // Create a list of recipes & set images
        List<Recipe_Model> recipes = DB.getRecipes();
        recipes.get(0).setImageResourceID(R.drawable.burgerpic);
        recipes.get(1).setImageResourceID(R.drawable.ravioli);
        recipes.get(2).setImageResourceID(R.drawable.strawberry_poke_cake);
        recipes.get(3).setImageResourceID(R.drawable.strawberry_pretzel_salad);
        recipes.get(4).setImageResourceID(R.drawable.cucumber_stuffed_cherry_tomatoes);

        // Initialize the RecipeAdapter and set it to the RecyclerView
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes);
        recyclerView.setAdapter(recipeAdapter);

        // Set up the Spinner's item selection listener
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                System.out.println("Category: " + selectedCategory);
                if (selectedCategory.equals("All")) {
                    recipeAdapter.getFilter().filter("");
                } else {
                    recipeAdapter.getFilter().filter(selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
        // super.onCreateOptionsMenu(menu, inflater);
    }
}