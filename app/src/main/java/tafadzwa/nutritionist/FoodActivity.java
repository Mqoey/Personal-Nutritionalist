package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

public class FoodActivity {

    int foodID;
    public int getFoodID() { return this.foodID; }

    String foodName;
    public String getfoodName() { return this.foodName; }

    Double calories;
    public Double getCalories() { return this.calories; }

    String ingredients;
    public String getIngredients() { return this.ingredients; }


    public FoodActivity(int id, String name, double calo, String ing){
        this.foodID = id;
        this.foodName = name;
        this.calories = calo;
        this.ingredients = ing;
    }
}
