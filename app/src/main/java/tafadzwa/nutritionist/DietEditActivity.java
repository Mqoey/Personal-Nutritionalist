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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.utils.DatabaseHelper;

public class DietEditActivity extends AppCompatActivity implements OnItemSelectedListener {

    TextView date, daily, userCal;
    private ArrayList<DietActivity> list;
    DatabaseHelper myDb;
    Button bDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_diet_edit);

        this.myDb = new DatabaseHelper(this);

        this.daily = this.findViewById(id.tvDailyCal);
        this.date = this.findViewById(id.tvDate2);
        this.userCal = this.findViewById(id.tvUserCal);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
        this.date.setText(df.format(c.getTime()));
        String currentDate = this.date.getText().toString();

        this.list = this.myDb.dietSpin(this.myDb.retrieveUser(), currentDate);
        Spinner spinner = this.findViewById(id.dietSpin);

        List<String> dietNames = new ArrayList<String>();
        for (int i = 0; i < this.list.size(); i++) {
            dietNames.add(this.list.get(i).getfoodName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.simple_spinner_item, dietNames);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        this.daily.setText(Double.toString(this.Calories(this.list)));
        this.userCal.setText(this.myDb.retrieveCalories(this.myDb.retrieveUser()));
        this.bDelete = this.findViewById(id.bAddDiet);
        this.removeDiet();
    }

    public void removeDiet() {
        this.bDelete.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        TextView item = DietEditActivity.this.findViewById(id.tvDietName);
                        String currentSelected = item.getText().toString();
                        if(currentSelected.equalsIgnoreCase("Foodname")){
                            Toast.makeText(DietEditActivity.this, "Meal Not Found", Toast.LENGTH_LONG).show();
                        }
                        else {
                            for (int i = 0; i < DietEditActivity.this.list.size(); i++) {
                                if (currentSelected.equals(DietEditActivity.this.list.get(i).foodName)) {
                                    DietActivity currentFood = DietEditActivity.this.list.get(i);
                                    DietEditActivity.this.myDb.deleteDiet(currentFood.getDietID());
                                    Toast.makeText(DietEditActivity.this, "Deleted Item " + currentFood.getfoodName(), Toast.LENGTH_LONG).show();
                                    Intent registerIntent = new Intent(DietEditActivity.this, DietPlannerActivity.class);
                                    startActivity(registerIntent);
                                    break;
                                }
                            }
                        }
                    }
                }
        );
    }

    public double Calories(ArrayList<DietActivity> items){
        double total = 0;
        for(int i=0; i<items.size(); i++){
            total += items.get(i).getCalories();
        }
        return total;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String name = parent.getItemAtPosition(pos).toString();

        //Assigns local variables for all text views
        TextView item = this.findViewById(R.id.tvDietName);
        TextView calories = this.findViewById(R.id.tvCalories);
        TextView ingredients = this.findViewById(R.id.tvIngredients);

        // Used to display info
        for (DietActivity d : this.list) {
            if (d.getfoodName().equalsIgnoreCase(name)) {

                item.setText(d.getfoodName());
                calories.setText(d.getCalories().toString());
                ingredients.setText(d.getIngredients().toString());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}