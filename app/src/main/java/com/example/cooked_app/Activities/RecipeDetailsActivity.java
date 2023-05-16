package com.example.cooked_app.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.R;

public class RecipeDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Recipe_Model recipe = (Recipe_Model) getIntent().getSerializableExtra("recipe");

        TextView recipe_Name, recipe_Cuisine, recipe_Course,
                recipe_Ingredients, recipe_Steps, recipe_Category,
                recipe_Description;
        recipe_Name = findViewById(R.id.recipe_name);
        recipe_Cuisine = findViewById(R.id.recipe_cuisine);
        recipe_Course = findViewById(R.id.recipe_course);
        recipe_Ingredients = findViewById(R.id.recipe_ingredients);
        recipe_Steps = findViewById(R.id.recipe_steps);
        recipe_Category = findViewById(R.id.recipe_category);
        recipe_Description = findViewById(R.id.recipe_description);

        recipe_Name.setText("Name: " + "\n" + recipe.getRecipe_Name());
        recipe_Cuisine.setText("Cuisine: " + recipe.getCuisine());
        recipe_Course.setText("Course: " + recipe.getCourse());
        recipe_Ingredients.setText("Ingredients: " + "\n" +  recipe.getIngredients());
        recipe_Steps.setText("Steps: " + "\n" +  recipe.getSteps());
        recipe_Category.setText("Category: " + recipe.getCategory());
        recipe_Description.setText("Description: " + "\n" +  recipe.getDescription());
    }
}
