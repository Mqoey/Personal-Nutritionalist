package tafadzwa.nutritionist.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import tafadzwa.nutritionist.MainActivity;
import tafadzwa.nutritionist.R;
import tafadzwa.nutritionist.R.layout;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_splash_screen);
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = SplashScreenActivity.this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
                if(pref.contains("identifier") && pref.contains("password")) {
                    Intent registerIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    SplashScreenActivity.this.startActivity(registerIntent);
                }
                else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginScreenActivity.class);
                    SplashScreenActivity.this.startActivity(intent);
                }
                SplashScreenActivity.this.finish();
            }
        },3000);
    }
}