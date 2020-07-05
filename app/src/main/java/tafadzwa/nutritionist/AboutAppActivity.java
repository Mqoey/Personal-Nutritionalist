package tafadzwa.nutritionist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.R.menu;
import tafadzwa.nutritionist.login.LoginScreenActivity;
import tafadzwa.nutritionist.utils.SettingsActivity;

/**
 * Created by mqondisi on 5/28/20.
 */

public class AboutAppActivity extends AppCompatActivity{
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_about);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        this.pref = this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
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
