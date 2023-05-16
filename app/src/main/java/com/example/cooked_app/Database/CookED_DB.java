package com.example.cooked_app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.cooked_app.Model.Recipe_Model;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class CookED_DB extends SQLiteAssetHelper {
    private static final String DB_NAME = "CookED.db";
    private static final String RECIPE_TABLE = "Recipes";
    private static final String COLUMN_RECIPE_ID = "Recipe_ID";
    private static final String COLUMN_RECIPE_NAME = "Recipe_Name";
    private static final String COLUMN_CUISINE = "Recipe_Cuisine";
    private static final String COLUMN_COURSE = "Recipe_Course";
    private static final String COLUMN_INGREDIENTS = "Recipe_Ingredients";
    private static final String COLUMN_STEPS = "Recipe_Steps";
    private static final String COLUMN_CATEGORY = "Recipe_Category";
    private static final String COLUMN_DESCRIPTION = "Recipe_Description";
    private static int DB_VER = 6;

    public CookED_DB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    @Override
    // TODO: Update Database Versions
    // Found a workaround but it is not robust.
    // Will need to implement another solution.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            DB_VER = newVersion;
        }
    }

    // Get All Recipes (Models)
    public List<Recipe_Model> getRecipes() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_RECIPE_ID, COLUMN_RECIPE_NAME, COLUMN_CUISINE,
                COLUMN_COURSE, COLUMN_INGREDIENTS, COLUMN_STEPS, COLUMN_CATEGORY, COLUMN_DESCRIPTION};
        qb.setTables(RECIPE_TABLE);

        Cursor cursor = qb.query(db, sqlSelect, null,
                null, null, null, null);

        List<Recipe_Model> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int recipeID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_ID));
                String recipeName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_NAME));
                String recipeCuisine = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUISINE));
                String recipeCourse = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE));
                String recipeIngredients = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENTS));
                String recipeSteps = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS));
                String recipeCategory = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                String recipeDescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));

                Recipe_Model recipe = new Recipe_Model(recipeID, recipeName, recipeCuisine, recipeCourse,
                        recipeIngredients, recipeSteps, recipeCategory, recipeDescription);
                result.add(recipe);
            } while (cursor.moveToNext());
        }
        db.close();
        return result;
    }

    // Get All Recipes (String)
    public List<String> getAllRecipes() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_RECIPE_NAME};
        qb.setTables(RECIPE_TABLE);

        Cursor cursor = qb.query(db, sqlSelect, null,
                null, null, null, null);

        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_NAME)));
            } while (cursor.moveToNext());
        }
        db.close();
        return result;
    }

    // Get Recipe By Name
    public List<Recipe_Model> getRecipeByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {COLUMN_RECIPE_ID, COLUMN_RECIPE_NAME, COLUMN_CUISINE,
                COLUMN_COURSE, COLUMN_INGREDIENTS, COLUMN_STEPS, COLUMN_CATEGORY,
                COLUMN_DESCRIPTION};
        qb.setTables(RECIPE_TABLE);

        Cursor cursor = qb.query(db, sqlSelect, "Recipe_Name LIKE ?",
                new String[] {"%"+name+"%"}, null, null, null);
        List<Recipe_Model> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Recipe_Model recipe = new Recipe_Model();
                recipe.setRecipe_ID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_ID)));
                recipe.setRecipe_Name(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECIPE_NAME)));
                recipe.setCuisine(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUISINE)));
                recipe.setCourse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE)));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENTS)));
                recipe.setSteps(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS)));
                recipe.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)));
                recipe.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                result.add(recipe);
            } while (cursor.moveToNext());
        }
        db.close();
        return result;
    }

    // Add new recipes from users to the database
    public int addRecipes(Recipe_Model Recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, Recipe.getRecipe_Name());
        values.put(COLUMN_CUISINE, Recipe.getCuisine());
        values.put(COLUMN_COURSE, Recipe.getCourse());
        values.put(COLUMN_INGREDIENTS, Recipe.getIngredients());
        values.put(COLUMN_STEPS, Recipe.getSteps());
        values.put(COLUMN_CATEGORY, Recipe.getCategory());
        values.put(COLUMN_DESCRIPTION, Recipe.getDescription());
        long insert =  db.insert(RECIPE_TABLE, null, values);
        db.close();
        if (insert == -1) {
            throw new RuntimeException("Failed to add recipe");
        }
        return 1;
    }
}
