package com.example.cooked_app.Activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooked_app.Database.CookED_DB;
import com.example.cooked_app.Model.Recipe_Model;
import com.example.cooked_app.R;

public class AddRecipeActivity extends AppCompatActivity {
    private CookED_DB DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        DB = new CookED_DB(this);

        Button add_recipe_btn = findViewById(R.id.btn_submit_recipe);
        add_recipe_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText recipe_Name, recipe_Cuisine, recipe_Course,
                        recipe_Ingredients, recipe_Steps, recipe_Category,
                        recipe_Desc;
                recipe_Name = findViewById(R.id.et_recipe_name);
                recipe_Cuisine = findViewById(R.id.et_recipe_cuisine);
                recipe_Course = findViewById(R.id.et_recipe_course);
                recipe_Ingredients = findViewById(R.id.et_recipe_ingredients);
                recipe_Steps = findViewById(R.id.et_recipe_steps);
                recipe_Category = findViewById(R.id.et_recipe_category);
                recipe_Desc = findViewById(R.id.et_recipe_description);


                String recipeName, recipeCuisine, recipeCourse,
                        recipeIngredients, recipeSteps, recipeCategory,
                        recipeDesc;
                recipeName = recipe_Name.getText().toString();
                recipeCuisine = recipe_Cuisine.getText().toString();
                recipeCourse = recipe_Course.getText().toString();
                recipeIngredients = recipe_Ingredients.getText().toString();
                recipeSteps = recipe_Steps.getText().toString();
                recipeCategory = recipe_Category.getText().toString();
                recipeDesc = recipe_Desc.getText().toString();

                // TODO: add error checking for empty strings

                System.out.println(recipeName);
                System.out.println(recipeCuisine);
                System.out.println(recipeCourse);
                System.out.println(recipeIngredients);
                System.out.println(recipeSteps);
                System.out.println(recipeCategory);
                System.out.println(recipeDesc);

                Recipe_Model new_recipe = new Recipe_Model();
                new_recipe.setRecipe_Name(recipeName);
                new_recipe.setCuisine(recipeCuisine);
                new_recipe.setCourse(recipeCourse);
                new_recipe.setIngredients(recipeIngredients);
                new_recipe.setSteps(recipeSteps);
                new_recipe.setCategory(recipeCategory);
                new_recipe.setDescription(recipeDesc);
                if (DB.addRecipes(new_recipe) == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Recipe added successfully", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
}
