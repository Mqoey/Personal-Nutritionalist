package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.utils.DatabaseHelper;

public class DietPlannerActivity extends AppCompatActivity {

    String userID;
    TextView bAddDiet;
    DatabaseHelper myDb;
    CalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_diet_planner);

        this.myDb = new DatabaseHelper(this);
        this.bAddDiet = this.findViewById(id.bAddDiet);
        this.Diet();

        this.calendar = this.findViewById(id.cDiet);
        this.calendar.setMinDate(System.currentTimeMillis() - 10000);
        this.Calendar();
    }

    public void Diet() {
        this.bAddDiet.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent registerIntent = new Intent(DietPlannerActivity.this, DietForumActivity.class);
                        DietPlannerActivity.this.startActivity(registerIntent);
                        DietPlannerActivity.this.finish();
                    }
                }
        );
    }
    public void Calendar(){
        this.calendar.setOnDateChangeListener(new OnDateChangeListener(){


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth+"/"+(month+1)+"/" +year;

                if(DietPlannerActivity.this.myDb.compareDietDate(date, DietPlannerActivity.this.myDb.retrieveUser()) == true) {

                    Intent registerIntent = new Intent(DietPlannerActivity.this, DietEditActivity.class);
                    registerIntent.putExtra("Date", date);
                    DietPlannerActivity.this.startActivity(registerIntent);
                }
                else{
                }
            }
        });
    }
}