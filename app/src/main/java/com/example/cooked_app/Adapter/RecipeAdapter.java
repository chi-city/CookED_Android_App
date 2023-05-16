package com.example.cooked_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>
        implements Filterable {

    private List<Recipe_Model> recipeList;
    private List<Recipe_Model> filteredRecipeList;

    public RecipeAdapter(List<Recipe_Model> recipeList) {
        this.recipeList = recipeList;
        this.filteredRecipeList = new ArrayList<>(recipeList);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe_Model recipe = filteredRecipeList.get(position);
        holder.recipeImage.setImageResource(recipe.getImageResourceID());
        holder.recipeTitle.setText(recipe.getRecipe_Name());
        holder.recipeDescription.setText(recipe.getDescription());
    }

    @Override
    public int getItemCount() {
        return filteredRecipeList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterString = constraint.toString();
                List<Recipe_Model> filteredList = new ArrayList<>();

                if (filterString.isEmpty()) {
                    filteredList.addAll(recipeList);
                } else {
                    for (Recipe_Model recipe : recipeList) {
                        if (recipe.getCategory().trim().equals(filterString)) {
                            filteredList.add(recipe);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredRecipeList.clear();
                if (results != null && results.values != null) {
                    filteredRecipeList.addAll((List) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeTitle;
        TextView recipeDescription;
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipeDescription = itemView.findViewById(R.id.recipe_description);
        }
    }
}
