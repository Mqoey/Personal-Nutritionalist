package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

public class UserActivity {

    int id;
    String name;
    String email;
    String password;
    int age;
    double height;
    double weight;
    String gender;
    double calories;

    public int getId(){
        return this.id;
    }
    public String getNsme(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getGender(){
        return this.gender;
    }
    public double getHeight(){
        return this.height;
    }
    public double getWeight(){
        return this.weight;
    }
    public int getAge(){
        return this.age;
    }
    public double getCalories(){
        return this.calories;
    }
    public UserActivity(){}


    public UserActivity(int id, String name, String email, String password, int age, double height, double weight, String gender, double calories){
        this.id =id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.calories = calories;
    }



}
