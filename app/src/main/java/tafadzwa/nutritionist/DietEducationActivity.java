package tafadzwa.nutritionist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;

public class DietEducationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_diet_education);
        Toolbar toolbar = this.findViewById(id.toolbar);
        this.setSupportActionBar(toolbar);

        FloatingActionButton fab = this.findViewById(id.faby);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DietEducationActivity.this, AboutAppActivity.class);
                DietEducationActivity.this.startActivity(intent);
            }
        });
    }
}