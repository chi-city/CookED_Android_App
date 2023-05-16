package com.example.cooked_app.Adapter;

import android.content.Context;
import android.content.Intent;

import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.Activities.RecipeDetailsActivity;

import java.io.Serializable;

public class OnRecipeClickListener implements MyAdapter.OnItemClickListener, Serializable {
    private Context context;

    public OnRecipeClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(int position) {
        // Get the recipe that was clicked
        Recipe_Model recipe = MyAdapter.getItem();

        // Start the RecipeDetailActivity and pass the recipe as an extra
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra("recipe", (Serializable) recipe);
        context.startActivity(intent);
    }
}