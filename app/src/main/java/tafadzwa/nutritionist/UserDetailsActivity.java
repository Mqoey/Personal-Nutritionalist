package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.R.menu;
import tafadzwa.nutritionist.login.LoginScreenActivity;
import tafadzwa.nutritionist.utils.DatabaseHelper;
import tafadzwa.nutritionist.utils.SettingsActivity;

public class UserDetailsActivity extends AppCompatActivity {

    TextView tvUserName, tvUserEmail, tvUserAge, tvUserGender, tvUserHeight, tvUserWeight, tvUserCal;
    Button bChangeWeight, bChangeHeight;
    DatabaseHelper myDb;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_user_details);
        this.myDb = new DatabaseHelper(this);

        this.bChangeWeight = this.findViewById(id.bChangeWeight);
        this.bChangeHeight = this.findViewById(id.bChangeHeight);
        this.tvUserName = this.findViewById(id.tvUserName);
        this.tvUserEmail = this.findViewById(id.tvUserEmail);
        this.tvUserAge = this.findViewById(id.etUserAge);
        this.tvUserGender = this.findViewById(id.tvUserGender);
        this.tvUserHeight = this.findViewById(id.tvHeight);
        this.tvUserWeight = this.findViewById(id.tvUserWeight);
        this.tvUserCal = this.findViewById(id.tvUserCalorie);

        int id = this.myDb.retrieveUser();

        UserActivity current = this.myDb.searchUser(id);

        this.tvUserName.setText(current.getNsme());
        this.tvUserEmail.setText(current.getEmail());
        this.tvUserAge.setText(Integer.toString(current.getAge()));
        this.tvUserGender.setText(current.getGender());
        this.tvUserHeight.setText(Double.toString(current.getHeight()));
        this.tvUserWeight.setText(Double.toString(current.getWeight()));
        this.tvUserCal.setText(Double.toString(current.getCalories()));
        this.pref = this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        this.ChangeWeight();
        this.ChangeHeight();
    }
    public void ChangeWeight() {
        this.bChangeWeight.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(UserDetailsActivity.this, WeightEditActivity.class);
                        UserDetailsActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }
    public void ChangeHeight() {
        this.bChangeHeight.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerInten = new Intent(UserDetailsActivity.this, HeightEditActivity.class);
                        UserDetailsActivity.this.startActivity(registerInten);
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case id.about:
                Intent intent = new Intent(this, AboutAppActivity.class);
                this.startActivity(intent);
                return true;

            case id.signout:
                Editor editor = this.pref.edit();
                editor.clear();
                editor.commit();
                Intent registerIntent = new Intent(this, LoginScreenActivity.class);
                this.startActivity(registerIntent);
                this.finish();
                return true;

            case id.settings:
                Intent intentsettings = new Intent(this, SettingsActivity.class);
                this.startActivity(intentsettings);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}