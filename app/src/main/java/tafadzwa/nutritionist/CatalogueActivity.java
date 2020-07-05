package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tafadzwa.nutritionist.R.drawable;
import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.utils.DatabaseHelper;

public class CatalogueActivity extends AppCompatActivity implements OnItemSelectedListener {

    private ArrayList<FoodActivity> list;

    Button bAddItem;
    DatabaseHelper myDb;
    ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_catalogue);
        this.myDb = new DatabaseHelper(this);
        this.foodImage = this.findViewById(id.ivFood);

        this.list = this.myDb.foodSpin();
        Spinner spinner = this.findViewById(id.dietSpin);

        List<String> foodNames = new ArrayList<String>();
        for (int i = 0; i < this.list.size(); i++)
        {
            foodNames.add(this.list.get(i).getfoodName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        this.bAddItem = this.findViewById(id.bAddDiet);
        this.addItem();
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = this.findViewById(R.id.tvDietName);
        TextView calories = this.findViewById(R.id.tvCalories);
        TextView ingrediants = this.findViewById(R.id.tvIngredients);

        // Used to display info
        for (FoodActivity f: this.list)
        {

            if (f.getfoodName().equalsIgnoreCase(name))
            {
                item.setText(f.getfoodName());
                calories.setText(f.getCalories().toString());
                ingrediants.setText(f.getIngredients());

                if(f.getfoodName().toString().equals("Spicy meatballs with chilli black beans")){
                    this.foodImage.setImageResource(drawable.meatballs);
                }

                else if(f.getfoodName().toString().equals("Tandoori chicken")) {
                    this.foodImage.setImageResource(drawable.tandori);
                }

                else if(f.getfoodName().toString().equals("Coconut & squash dhansak")) {
                    this.foodImage.setImageResource(drawable.coconutsquash);
                }
                else if(f.getfoodName().toString().equals("Miso-roasted aubergine steaks with sweet potato")) {
                    this.foodImage.setImageResource(drawable.miso);
                }
                else if(f.getfoodName().toString().equals("Breakfast")) {
                    this.foodImage.setImageResource(drawable.breakfast);
                }
                else if(f.getfoodName().toString().equals("Fish")) {
                    this.foodImage.setImageResource(drawable.fish);
                }
                else if(f.getfoodName().toString().equals("Sadza & Beef")) {
                    this.foodImage.setImageResource(drawable.sadzabeef);
                }
                else if(f.getfoodName().toString().equals("Sadza and mixed Relish")) {
                    this.foodImage.setImageResource(drawable.sadzabeefbeans);
                }
                else if(f.getfoodName().toString().equals("Fried Rice & Chicken")) {
                    this.foodImage.setImageResource(drawable.ricebeef);
                }
                else if(f.getfoodName().toString().equals("Rice & Chicken")) {
                    this.foodImage.setImageResource(drawable.ricechicken);
                }
                else if(f.getfoodName().toString().equals("Sadza & Beans")) {
                    this.foodImage.setImageResource(drawable.sadzabeans);
                }
                else if(f.getfoodName().toString().equals("Sadza & Roasted Chicken")) {
                    this.foodImage.setImageResource(drawable.sadzachicken);
                }
                else if(f.getfoodName().toString().equals("Sadza & Chicken Stew")) {
                    this.foodImage.setImageResource(drawable.sadzachickenstew);
                }
                else {
                    this.foodImage.setImageResource(0);
                }
            }
        }
    }
    public void addItem(){
        this.bAddItem.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(CatalogueActivity.this, CatalogueForumActivity.class);
                        startActivity(registerIntent);
                    }
                }
        );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}