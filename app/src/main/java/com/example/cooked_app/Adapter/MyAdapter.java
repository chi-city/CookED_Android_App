package com.example.cooked_app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.R;

import java.util.List;


class SearchViewHolder extends RecyclerView.ViewHolder {
    public TextView recipe_name, recipe_cuisine, recipe_course;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.recipe_name = (TextView) itemView.findViewById(R.id.recipe_name);
        this.recipe_cuisine = (TextView) itemView.findViewById(R.id.recipe_cuisine);
        this.recipe_course = (TextView) itemView.findViewById(R.id.recipe_course);
    }
}

public class MyAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context;
    private static List<Recipe_Model> recipes;
    private OnRecipeClickListener listener;

    private static int click_position;

    public MyAdapter(Context context, List<Recipe_Model> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.search_layout, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recipe_name.setText(recipes.get(position).getRecipe_Name());
        holder.recipe_cuisine.setText(recipes.get(position).getCuisine());
        holder.recipe_course.setText(recipes.get(position).getCourse());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position);
                    click_position = position;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recipes.size();
    }

    static Recipe_Model getItem() {
        return recipes.get(click_position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = (OnRecipeClickListener) listener;
    }

}


