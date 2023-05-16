package com.example.cooked_app.Model;

import java.io.Serializable;
import java.util.StringJoiner;

/*
 * Class to Add Recipes to the Database
*/
public class Recipe_Model implements Serializable {
    private int Recipe_ID;
    private String Recipe_Name;
    private String Cuisine;
    private String Course;
    private String Ingredients;
    private String Steps;
    private int imageResourceId;
    private String description;

    private String category;
    // Constructors
    public Recipe_Model(int recipe_ID,
                        String recipe_Name,
                        String recipe_Cuisine,
                        String course,
                        String ingredients,
                        String steps,
                        String category,
                        String description) {
        this.Recipe_ID = recipe_ID;
        this.Recipe_Name = recipe_Name;
        this.Cuisine = recipe_Cuisine;
        this.Course = course;
        this.Ingredients = ingredients;
        this.Steps = steps;
        this.category = category;
        this.description = description;
    }

    public Recipe_Model(){

    }

    // Getters & Setters
    public int getRecipe_ID() {
        return Recipe_ID;
    }

    public String getCategory() {
        return category;
    }

    public void setRecipe_ID(int recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public String getRecipe_Name() {
        return Recipe_Name;
    }

    public int getImageResourceID() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setRecipe_Name(String recipe_Name) {
        Recipe_Name = recipe_Name;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public void setCuisine(String recipe_Cuisine) {
        Cuisine = recipe_Cuisine;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }

    public void setImageResourceID(int imageResourceId) { this.imageResourceId = imageResourceId; }
}
