package tafadzwa.nutritionist.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import tafadzwa.nutritionist.DietActivity;
import tafadzwa.nutritionist.FoodActivity;
import tafadzwa.nutritionist.UserActivity;

/**
 * Created by mqondisi on 16/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Assign db name
    public static final String DATABASE_NAME = "Nutritionist.db";
    //Assign table name
    public static final String USER_TABLE = "user_table";
    public static final String DIET_TABLE = "diet_table";
    public static final String FOOD_CATALOGUE = "catalogue_table";


    public static final String User_Col_1 = "USERID";
    public static final String User_Col_2 = "USERNAME";
    public static final String User_Col_3 = "EMAIL";
    public static final String User_Col_4 = "PASSWORD";
    public static final String User_Col_5 = "AGE";
    public static final String User_Col_6 = "HEIGHT";
    public static final String User_Col_7 = "WEIGHT";
    public static final String User_Col_8 = "GENDER";
    public static final String User_Col_9 = "DAILYCAL";

    public static final String Food_Col_1 = "FOODNAME";
    public static final String Food_Col_2 = "CALORIES";
    public static final String Food_Col_3 = "CATEGORY";
    public static final String Food_Col_4 = "INGREDIANTS";

    public static final String Diet_Col_2 = "FOODNAME";
    public static final String Diet_Col_3 = "DIETDATE";
    public static final String Diet_Col_4 = "CALORIES";
    public static final String Diet_Col_5 = "INGREDIANTS";
    public static final String Diet_Col_6 = "UID";
    public static final String Diet_Col_7 = "FID";


    public DatabaseHelper(Context context) {
        super(context, DatabaseHelper.DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DatabaseHelper.USER_TABLE + "(USERID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, AGE TEXT, HEIGHT TEXT, WEIGHT TEXT, GENDER TEXT, DAILYCAL TEXT)");
        db.execSQL("create table " + DatabaseHelper.FOOD_CATALOGUE + "(FOODID INTEGER PRIMARY KEY AUTOINCREMENT, FOODNAME TEXT, CALORIES TEXT, CATEGORY TEXT, INGREDIANTS TEXT)");
        db.execSQL("create table " + DatabaseHelper.DIET_TABLE + "(DIETID INTEGER PRIMARY KEY AUTOINCREMENT, FOODNAME TEXT, DIETDATE TEXT, CALORIES TEXT, INGREDIANTS TEXT, UID INTEGER, FID INTEGER, FOREIGN KEY (UID) REFERENCES USER_TABLE(USERID), FOREIGN KEY (FID) REFERENCES "+ DatabaseHelper.FOOD_CATALOGUE + "(FOODID))");

        db.execSQL("create table CURRENT_USER (CURRENTUSERID INTEGER PRIMARY KEY)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + DatabaseHelper.DIET_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + DatabaseHelper.FOOD_CATALOGUE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + DatabaseHelper.USER_TABLE +"'");
        db.execSQL("DROP TABLE IF EXISTS 'CURRENT_USER'");
        this.onCreate(db);

    }

    public void FoodItems(){
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM " + DatabaseHelper.FOOD_CATALOGUE;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){
        }

        else {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(DatabaseHelper.Food_Col_1, "Spicy meatballs with chilli black beans");
            contentValues1.put(DatabaseHelper.Food_Col_2, "376");
            contentValues1.put(DatabaseHelper.Food_Col_3, "Meat");
            contentValues1.put(DatabaseHelper.Food_Col_4, "Red Onions, Garlic Cloves, Ground Cumin, Cherry Tomatoes, Avocado, Turkey Breast Mince");

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put(DatabaseHelper.Food_Col_1, "Tandoori chicken");
            contentValues2.put(DatabaseHelper.Food_Col_2, "171");
            contentValues2.put(DatabaseHelper.Food_Col_3, "Meat");
            contentValues2.put(DatabaseHelper.Food_Col_4, "Lemons, Paprika, Red Onions, Chick Thighs, Vegetable Oil, Vegetable Oil, Ground Cumin");

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put(DatabaseHelper.Food_Col_1, "Miso-roasted aubergine steaks with sweet potato");
            contentValues3.put(DatabaseHelper.Food_Col_2, "344");
            contentValues3.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues3.put(DatabaseHelper.Food_Col_4, "Large Aubergine, Brown Miso Paste, Sweet Potatoes, Sunflower Oil, Ginger, Garlic Clove, Sale, Spring Onions, Pasley leaves");

            ContentValues contentValues4 = new ContentValues();
            contentValues4.put(DatabaseHelper.Food_Col_1, "Coconut & squash dhansak");
            contentValues4.put(DatabaseHelper.Food_Col_2, "320");
            contentValues4.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues4.put(DatabaseHelper.Food_Col_4, "Vegetable Oil, Buttenut Squash, Onions, Mild Curry Paste, Tomatoes, Coconut Milk, Naan Bread, Lentils, Spinach, Coconut Yogurt");

            ContentValues contentValues5 = new ContentValues();
            contentValues5.put(DatabaseHelper.Food_Col_1, "Breakfast");
            contentValues5.put(DatabaseHelper.Food_Col_2, "370");
            contentValues5.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues5.put(DatabaseHelper.Food_Col_4, "Banana, Apple, Bread, Orange, Beef, Coconut Milk, Spaghetti, Lentils, Spinach, Broccoli");

            ContentValues contentValues6 = new ContentValues();
            contentValues6.put(DatabaseHelper.Food_Col_1, "Fish");
            contentValues6.put(DatabaseHelper.Food_Col_2, "120");
            contentValues6.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues6.put(DatabaseHelper.Food_Col_4, "Fish");

            ContentValues contentValues7 = new ContentValues();
            contentValues7.put(DatabaseHelper.Food_Col_1, "Fried Rice & Chicken");
            contentValues7.put(DatabaseHelper.Food_Col_2, "349");
            contentValues7.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues7.put(DatabaseHelper.Food_Col_4, "Chicken, Vegetable Oil, Rice, King Onion, Buttenut Squash, Tomatoes");

            ContentValues contentValues8 = new ContentValues();
            contentValues8.put(DatabaseHelper.Food_Col_1, "Rice & Chicken");
            contentValues8.put(DatabaseHelper.Food_Col_2, "340");
            contentValues8.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues8.put(DatabaseHelper.Food_Col_4, "Rice, Chicken, Vegetable Oil, Buttenut Squash, Onions, Carrot, Tomatoes, Lemon");

            ContentValues contentValues9 = new ContentValues();
            contentValues9.put(DatabaseHelper.Food_Col_1, "Sadza & Beans");
            contentValues9.put(DatabaseHelper.Food_Col_2, "345");
            contentValues9.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues9.put(DatabaseHelper.Food_Col_4, "Vegetable Oil, Sugar Beans, Onions, Mild Curry Paste, Tomatoes, Mealie Meal, Rape Leaves");

            ContentValues contentValues10 = new ContentValues();
            contentValues10.put(DatabaseHelper.Food_Col_1, "Sadza & Beef");
            contentValues10.put(DatabaseHelper.Food_Col_2, "360");
            contentValues10.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues10.put(DatabaseHelper.Food_Col_4, "Beef, Onions, Mild Curry Paste, Tomatoes, Mealie Meal, Rape Leaves, Vegetable Oil");

            ContentValues contentValues11 = new ContentValues();
            contentValues11.put(DatabaseHelper.Food_Col_1, "Sadza and mixed Relish");
            contentValues11.put(DatabaseHelper.Food_Col_2, "390");
            contentValues11.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues11.put(DatabaseHelper.Food_Col_4, "Beef, Beans, Onions, Mild Curry Paste, Tomatoes, Mealie Meal, Rape Leaves, Vegetable Oil");

            ContentValues contentValues12 = new ContentValues();
            contentValues12.put(DatabaseHelper.Food_Col_1, "Sadza & Roasted Chicken");
            contentValues12.put(DatabaseHelper.Food_Col_2, "370");
            contentValues12.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues12.put(DatabaseHelper.Food_Col_4, "Mealie Meal, Chicken, Vegetable Oil, Rape Leaves, Tomatoes, Onions");

            ContentValues contentValues13 = new ContentValues();
            contentValues13.put(DatabaseHelper.Food_Col_1, "Sadza & Chicken Stew");
            contentValues13.put(DatabaseHelper.Food_Col_2, "377");
            contentValues13.put(DatabaseHelper.Food_Col_3, "Veg");
            contentValues13.put(DatabaseHelper.Food_Col_4, "Mealie Meal, Chicken, Vegetable Oil, Rape Leaves, Tomatoes, Onions");


            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues11);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues12);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues13);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues10);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues9);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues8);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues7);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues6);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues5);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues4);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues3);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues2);
            db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues1);
        }
        db.close();
    }

    public boolean insertFood(String name, String calories, String type, String ingredients)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Food_Col_1, name);
        contentValues.put(DatabaseHelper.Food_Col_2, calories);
        contentValues.put(DatabaseHelper.Food_Col_3, type);
        contentValues.put(DatabaseHelper.Food_Col_4, ingredients);

        long result = db.insert(DatabaseHelper.FOOD_CATALOGUE, null, contentValues);
        db.close();

        return result != -1;
    }

    public Boolean uniqueFood(String name){
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT FOODNAME FROM " + DatabaseHelper.FOOD_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        String foodName;
        boolean pass = true;

        if(cursor.moveToFirst()){
            do{
                foodName = cursor.getString(0);
                if(foodName.equals(name)){
                    pass = false;
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }

    public boolean insertUser(String name, String email, String password, String age, String height, String weight, String gender, String dailyCal)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.User_Col_2, name);
        contentValues.put(DatabaseHelper.User_Col_3, email);
        contentValues.put(DatabaseHelper.User_Col_4, password);
        contentValues.put(DatabaseHelper.User_Col_5, age);
        contentValues.put(DatabaseHelper.User_Col_6, height);
        contentValues.put(DatabaseHelper.User_Col_7, weight);
        contentValues.put(DatabaseHelper.User_Col_8, gender);
        contentValues.put(DatabaseHelper.User_Col_9, dailyCal);

        long result = db.insert(DatabaseHelper.USER_TABLE, null, contentValues);
        db.close();

        return result != -1;
    }

    public boolean insertDiet(String name, String date, String calories, String ingrediants, int userID, int foodID)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Diet_Col_2, name);
        contentValues.put(DatabaseHelper.Diet_Col_3, date);
        contentValues.put(DatabaseHelper.Diet_Col_4, calories);
        contentValues.put(DatabaseHelper.Diet_Col_5, ingrediants);
        contentValues.put(DatabaseHelper.Diet_Col_6, userID);
        contentValues.put(DatabaseHelper.Diet_Col_7, foodID);

        long result = db.insert(DatabaseHelper.DIET_TABLE, null, contentValues);
        db.close();

        return result != -1;
    }

    public void deleteDiet(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DatabaseHelper.DIET_TABLE, "DIETID = ?", new String[] {Integer.toString(id)});
    }

    public String searchPass(String address)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT EMAIL, PASSWORD FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String email;
        String pass = "NOI FOUND";

        if(cursor.moveToFirst()){
            do{
                email = cursor.getString(0);
                if(email.equals(address)){

                    pass = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }

    public UserActivity searchUser(int id)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        int identifier;
        String name = "";
        String email = "";
        String password  = "";
        int age  = 0;
        double height  = 0;
        double weight = 0;
        String gender = "";
        double cal = 0;

        if(cursor.moveToFirst()){
            do{
                identifier = Integer.parseInt(cursor.getString(0));
                if(identifier == id){
                    name = cursor.getString(1);
                    email = cursor.getString(2);
                    password = cursor.getString(3);
                    age = Integer.parseInt(cursor.getString(4));
                    height =Double.parseDouble(cursor.getString(5));
                    weight = Double.parseDouble(cursor.getString(6));
                    gender = cursor.getString(7);
                    cal = Double.parseDouble(cursor.getString(8));
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        UserActivity currentUserActivity = new UserActivity(id,name,email,password,age,height,weight,gender,cal);
        db.close();
        return currentUserActivity;
    }

    public Boolean isUnique(String email){
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT EMAIL FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String address;
        boolean pass = true;

        if(cursor.moveToFirst()){
            do{
                address = cursor.getString(0);
                if(address.equals(email)){
                    pass = false;
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }

    public Integer searchID(String uname)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT USERID, EMAIL FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String userName;
        int user = 3000;

        if(cursor.moveToFirst()){
            do{
                userName = cursor.getString(1);
                if(userName.equals(uname)){

                    user = Integer.parseInt(cursor.getString(0));
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public String searchUserName(String uname)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT USERNAME, EMAIL FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String userName;
        String user = "";

        if(cursor.moveToFirst()){
            do{
                userName = cursor.getString(1);
                if(userName.equals(uname)){
                    user = cursor.getString(0);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public void assignUser(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("CURRENT_USER", null, null);
        String currentuser = "CURRENTUSERID";

        ContentValues contentValues = new ContentValues();
        contentValues.put(currentuser, id);
        db.insert("CURRENT_USER", null, contentValues);
        db.close();
    }

    public double getWeight(int id)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT USERID, WEIGHT FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int userID;
        double user = 0;

        if(cursor.moveToFirst()){
            do{
                userID = Integer.parseInt(cursor.getString(0));
                if(userID == id){
                    user = Double.parseDouble(cursor.getString(1));
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public double getHeight(int id)
    {
        SQLiteDatabase db  = getReadableDatabase();
        String query = "SELECT USERID, HEIGHT FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int userID;
        double user = 0;

        if(cursor.moveToFirst()){
            do{
                userID = Integer.parseInt(cursor.getString(0));
                if(userID == id){
                    user = Double.parseDouble(cursor.getString(1));
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return user;
    }

    public int retrieveUser()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT CURRENTUSERID FROM CURRENT_USER";
        Cursor cursor = db.rawQuery(query, null);
        int user = -1;
        if(cursor.moveToFirst()) {
            user = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return user;
    }

    public void signOut()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("CURRENT_USER", null, null);
        db.close();
    }

    public ArrayList<FoodActivity> foodSpin(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.FOOD_CATALOGUE;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<FoodActivity> list = new ArrayList<FoodActivity>();
        if(cursor.moveToFirst()){
            do{
                FoodActivity f = new FoodActivity(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Double.parseDouble(cursor.getString(2)), cursor.getString(4));
                list.add(f);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public ArrayList<DietActivity> dietSpin(int user, String date){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.DIET_TABLE + " WHERE UID = " +user+ " AND DIETDATE = '" +date+ "'";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<DietActivity> list = new ArrayList<DietActivity>();
        if(cursor.moveToFirst()){
            do{
                DietActivity d = new DietActivity(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                list.add(d);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public boolean updateUser(String userEmail, String userName, String password)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query = "SELECT USERNAME, EMAIL FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String email, user;
        long result = -1;

        if(cursor.moveToFirst()){
            do{
                user = cursor.getString(0);
                email = cursor.getString(1);

                if(userName.equals(user)){

                    if(email.equals(userEmail)){
                        String[] whereArgs = {email};
                        contentValues.put(DatabaseHelper.User_Col_4, password);
                        result = db.update(DatabaseHelper.USER_TABLE, contentValues, DatabaseHelper.User_Col_3 +" =? " , whereArgs );
                        break;
                    }
                }
            }
            while(cursor.moveToNext());
        }

        db.close();

        return result != -1;
    }

    public boolean updateWeight(int id, double weight, String email)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        String query = "SELECT USERID, EMAIL, AGE, HEIGHT, GENDER FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String emailCheck;
        int idCheck, age;
        String gender;
        double height;
        long result = -1;
        double newCal = 0;

        if(cursor.moveToFirst()){
            do{

                idCheck = Integer.parseInt(cursor.getString(0));
                emailCheck = cursor.getString(1);
                height = Double.parseDouble(cursor.getString(3));
                gender = cursor.getString(4);
                age = Integer.parseInt(cursor.getString(2));

                if(id == idCheck ){
                    if (email.equals(emailCheck)) {

                        if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("Female")){
                            newCal = 10 * weight + 6.25 * height - 5 * age - 161;
                        }

                        else if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("Male")){
                            newCal = 10 * weight + 6.25 * height - 5 * age + 5;
                        }

                        else{
                            newCal = 0;
                        }

                        String[] whereArgs = {email};
                        contentValues1.put(DatabaseHelper.User_Col_9, newCal);
                        contentValues1.put(DatabaseHelper.User_Col_7, weight);
                        result = db.update(DatabaseHelper.USER_TABLE, contentValues1, DatabaseHelper.User_Col_3 +" =? " , whereArgs );
                        break;
                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();

        return result != -1;
    }

    public boolean updateHeight(int id, double height, String email)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        String query = "SELECT USERID, EMAIL, AGE, HEIGHT, GENDER FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String emailCheck;
        int idCheck, age;
        String gender;
        double weight;
        long result = -1;
        double newCal = 0;

        if(cursor.moveToFirst()){
            do{
                idCheck = Integer.parseInt(cursor.getString(0));
                emailCheck = cursor.getString(1);
                weight = Double.parseDouble(cursor.getString(3));
                gender = cursor.getString(4);
                age = Integer.parseInt(cursor.getString(2));

                if(id == idCheck ){
                    if (email.equals(emailCheck)) {
                        if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("Female")){
                            newCal = 10 * weight + 6.25 * height - 5 * age - 161;
                        }
                        else if(gender.equalsIgnoreCase("BottomNavigationActivity") || gender.equalsIgnoreCase("Male")){
                            newCal = 10 * height + 6.25 * weight - 5 * age + 5;
                        }
                        else{
                            newCal = 0;
                        }
                        String[] whereArgs = {email};
                        contentValues1.put(DatabaseHelper.User_Col_9, newCal);
                        contentValues1.put(DatabaseHelper.User_Col_6, height);
                        result = db.update(DatabaseHelper.USER_TABLE, contentValues1, DatabaseHelper.User_Col_3 +" =? " , whereArgs );

                        break;
                    }
                }
            }
            while(cursor.moveToNext());
        }

        db.close();

        return result != -1;
    }

    public String retrieveCalories(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT USERID, DAILYCAL FROM " + DatabaseHelper.USER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String cal= "" ;
        int user;
        if(cursor.moveToFirst()) {
            do{
                user = Integer.parseInt(cursor.getString(0));
                if(user == id){
                    cal = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return cal;
    }

    public double retrieveDailyCalories(int user, String date)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT UID, DIETDATE, CALORIES FROM " + DatabaseHelper.DIET_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int userCheck;
        String dateCheck;
        double dailycal = 0;
        double calories = 0;
        if(cursor.moveToFirst()) {
            do{
                userCheck = Integer.parseInt(cursor.getString(0));
                dateCheck = cursor.getString(1);
                if(user == userCheck){
                    if(date.equals(dateCheck)){
                        dailycal = Double.parseDouble(cursor.getString(2));
                        calories += dailycal;
                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return calories;
    }

    public boolean compareDietDate(String date, int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DIETDATE, UID FROM " + DatabaseHelper.DIET_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        String dateCheck;
        int idCheck;
        boolean check = false;

        if(cursor.moveToFirst()) {
            do{
                idCheck = Integer.parseInt(cursor.getString(1));
                dateCheck = cursor.getString(0);
                if(date.equals(dateCheck)){
                    if(id == idCheck){
                        check = true;
                        break;
                    }
                }
            }
            while(cursor.moveToNext());
        }
        db.close();
        return check;
    }

    public void closeDb()
    {
        SQLiteDatabase db = getReadableDatabase();
        db.close();
    }
}