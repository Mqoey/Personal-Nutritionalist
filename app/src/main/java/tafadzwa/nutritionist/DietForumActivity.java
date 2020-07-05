package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.R.layout;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.utils.DatabaseHelper;
import tafadzwa.nutritionist.utils.MyDialogFragment;

public class DietForumActivity extends AppCompatActivity implements OnItemSelectedListener {
    Calendar calendar = Calendar.getInstance();

    TextView date;
    private ArrayList<FoodActivity> list;
    DatabaseHelper myDb;
    TextView bDiet;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_diet_forum);


        this.myDb = new DatabaseHelper(this);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
        this.date = this.findViewById(id.tvDate2);
        this.date.setText(df.format(c.getTime()));
        String currentDate = this.date.getText().toString();

        this.selectDate();
        this.list = this.myDb.foodSpin();
        Spinner spinner = this.findViewById(id.dietSpin);
        List<String> foodNames = new ArrayList<String>();
        for (int i = 0; i < this.list.size(); i++) {
            foodNames.add(this.list.get(i).getfoodName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        this.bDiet = this.findViewById(id.baDiet);
        this.addDiet();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views

        TextView item = this.findViewById(R.id.tvDietName);
        TextView calories = this.findViewById(R.id.tvCalories);
        TextView ingredients = this.findViewById(R.id.tvIngredients);

        // Used to display info
        for (FoodActivity f : this.list) {
            //checks to find mathing api level
            if (f.getfoodName().equalsIgnoreCase(name)) {
                item.setText(f.getfoodName());
                calories.setText(f.getCalories().toString());
                ingredients.setText(f.getIngredients());
            }
        }
    }

    public void addDiet() {
        this.bDiet.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = DietForumActivity.this.findViewById(id.tvDietName);
                        String currentSelected = item.getText().toString();
                        if (currentSelected.equalsIgnoreCase("Foodname")) {
                            Toast.makeText(DietForumActivity.this, "Meal Not Found", Toast.LENGTH_LONG).show();
                        } else {
                            for (int i = 0; i < DietForumActivity.this.list.size(); i++) {
                                if (currentSelected.equals(DietForumActivity.this.list.get(i).foodName)) {
                                    FoodActivity currentFoodActivity = DietForumActivity.this.list.get(i);
                                    boolean isInserted = DietForumActivity.this.myDb.insertDiet(currentFoodActivity.getfoodName(), DietForumActivity.this.date.getText().toString(), currentFoodActivity.getCalories().toString(), currentFoodActivity.getIngredients(), DietForumActivity.this.myDb.retrieveUser(), currentFoodActivity.getFoodID());

                                    if (isInserted == true) {

                                        FragmentManager manager = DietForumActivity.this.getSupportFragmentManager();
                                        MyDialogFragment myDialogFragment = new MyDialogFragment();
                                        myDialogFragment.show(manager, "MyDialogFragment");

                                    } else {
                                        Toast.makeText(DietForumActivity.this, "Failed to add meal", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    //Toast.makeText(DietForumActivity.this, "Cannot Find DietActivity", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                }
        );
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public DatePicker selectDate() {
        this.date.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(DietForumActivity.this, DietForumActivity.this.listener, DietForumActivity.this.calendar.get(Calendar.YEAR), DietForumActivity.this.calendar.get(Calendar.MONTH), DietForumActivity.this.calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                }
        );
        return null;
    }
    OnDateSetListener listener = new OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            DietForumActivity.this.date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    };
}