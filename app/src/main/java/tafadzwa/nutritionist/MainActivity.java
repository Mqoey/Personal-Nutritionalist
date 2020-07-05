package tafadzwa.nutritionist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;

import tafadzwa.nutritionist.R.drawable;
import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.R.menu;
import tafadzwa.nutritionist.R.string;
import tafadzwa.nutritionist.login.LoginScreenActivity;
import tafadzwa.nutritionist.utils.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements OnNavigationItemSelectedListener {

    SharedPreferences pref;
    TextView bDiet, bEducation, bCatalogue, bEditDiet;
    String userID;
    AdapterViewFlipper adapterViewFlipper;
    ViewPager viewPager ;

    int[] images= {
            drawable.a,
            drawable.b,
            drawable.c,
            drawable.d,
            drawable.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_main);
        Toolbar toolbar = this.findViewById(id.toolbar);
        this.setSupportActionBar(toolbar);

        DrawerLayout drawer = this.findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = this.findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.pref = this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        this.bDiet = this.findViewById(id.bDiet2);
        this.bEducation = this.findViewById(id.bEducation);
        this.bCatalogue = this.findViewById(id.bCatalogue);
        this.bEditDiet = this.findViewById(id.bMeal);

        this.Diet();
        this.Catalogue();
        this.Education();
        this.DietEdit();

        this.viewPager = this.findViewById(id.flip );
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( this );
        this.viewPager. setAdapter (viewPagerAdapter );
    }

    public void Diet() {
        this.bDiet.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainActivity.this, DietPlannerActivity.class);
                        registerIntent.putExtra("myId", MainActivity.this.userID);
                        MainActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }
    public void Catalogue() {
        this.bCatalogue.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainActivity.this, CatalogueActivity.class);
                        MainActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    public void Education() {
        this.bEducation.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(MainActivity.this, DietEducationActivity.class);
                        MainActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }
    public void DietEdit() {
        this.bEditDiet.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent editIntent = new Intent(MainActivity.this, DietEditActivity.class);
                        MainActivity.this.startActivity(editIntent);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = this.findViewById(id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tips) {
            Intent intent = new Intent(this, TipsActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_user) {
            Intent intent = new Intent(this, UserDetailsActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_notifications) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Personal Nutritionist");
            intent.putExtra(Intent.EXTRA_TEXT, "Get guided about nutrition on your mobile.");
            intent.setType("text/plain");
            this.startActivity(intent);
        }
        DrawerLayout drawer = this.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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