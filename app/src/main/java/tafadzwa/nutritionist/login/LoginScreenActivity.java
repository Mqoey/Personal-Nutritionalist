package tafadzwa.nutritionist.login;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.utils.DatabaseHelper;
import tafadzwa.nutritionist.MainActivity;
import tafadzwa.nutritionist.R;

public class LoginScreenActivity extends AppCompatActivity {

    EditText etPassword, etEmail;
    TextView registerLink, passwordReset,bLogin,wrongLogin;
    DatabaseHelper myDb;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_login);

        this.pref = this.getSharedPreferences("user_details", Context.MODE_PRIVATE);

        this.myDb = new DatabaseHelper(this);
        this.myDb.FoodItems();
        this.myDb.signOut();

        this.etPassword = this.findViewById(id.etPassword);
        this.etEmail = this.findViewById(id.etEmail);
        this.bLogin = this.findViewById(id.bLogin);
        this.registerLink = this.findViewById(id.tvRegisterHere);
        this.passwordReset = this.findViewById(id.tvPasswordReset);
        this.wrongLogin = this.findViewById(id.wrong_login);

        this.Register();
        this.Login();
        this.PasswordReset();
    }

    public void Register() {
        this.registerLink.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(LoginScreenActivity.this, RegisterActivity.class);
                        LoginScreenActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    public void PasswordReset() {
        this.passwordReset.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent registerIntent = new Intent(LoginScreenActivity.this, PasswordRecoveryActivity.class);
                        LoginScreenActivity.this.startActivity(registerIntent);
                    }
                }
        );
    }

    public void Login(){
        this.bLogin.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String password = LoginScreenActivity.this.etPassword.getText().toString();
                        String identifier = LoginScreenActivity.this.etEmail.getText().toString();
                        String pass = LoginScreenActivity.this.myDb.searchPass(identifier);
                        String name = LoginScreenActivity.this.myDb.searchUserName(identifier);

                        if(password.equals(pass)){

                            Editor editor = LoginScreenActivity.this.pref.edit();
                            editor.putString("identifier", identifier);
                            editor.putString("password", password);
                            editor.commit();

                            Integer userID = LoginScreenActivity.this.myDb.searchID(identifier);
                            LoginScreenActivity.this.myDb.assignUser(userID);
                            Toast.makeText(LoginScreenActivity.this, "Welcome :" + name +" to Personal Nutritionist.", Toast.LENGTH_LONG).show();
                            Intent registerIntent = new Intent(LoginScreenActivity.this, MainActivity.class);
                            LoginScreenActivity.this.startActivity(registerIntent);
                            LoginScreenActivity.this.finish();
                        }
                        else{
                            Toast.makeText(LoginScreenActivity.this, "Username and Password do not match", Toast.LENGTH_LONG).show();
                            LoginScreenActivity.this.wrongLogin.setText("Username and Password do not match");
                        }
                    }
                }
        );
    }
}