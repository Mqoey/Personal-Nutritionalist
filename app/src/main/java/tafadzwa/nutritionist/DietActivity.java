package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

public class DietActivity {

    int dietID;
    public int getDietID() { return this.dietID; }

    String foodName;
    public String getfoodName() { return this.foodName; }

    Double calories;
    public Double getCalories() { return this.calories; }

    String ingredients;
    public String getIngredients() { return this.ingredients; }

    int uId;
    public int getuId() { return this.uId; }

    int fId;
    public int getfId() { return this.fId;}

    public DietActivity(int id, String name, String date, double calo, String ing, int uid, int fid){
        this.dietID = id;
        this.foodName = name;
        this.calories = calo;
        this.ingredients = ing;
        this.uId = uid;
        this.fId = fid;
    }
}
