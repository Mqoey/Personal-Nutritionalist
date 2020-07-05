package tafadzwa.nutritionist.login;

/**
 * Created by mqondisi on 5/20/20.
 */
import android.content.Intent;
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
import tafadzwa.nutritionist.R;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText etName, etAge, etEmail, etPassword, etPasswordConfirm, etHeight, etGender, etWeight;
    TextView bRegister;
    String emailPattern, passwordPattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_register);

        this.myDb = new DatabaseHelper(this);

        this.etName = this.findViewById(id.etUserName);
        this.etAge = this.findViewById(id.etAge);
        this.etEmail = this.findViewById(id.etEmail);
        this.etPassword = this.findViewById(id.etPassword);
        this.etPasswordConfirm = this.findViewById(id.etPasswordConfirm);
        this.etHeight = this.findViewById(id.etHeight);
        this.etGender = this.findViewById(id.etGender);
        this.etWeight = this.findViewById(id.etWeight);

        this.emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        this.passwordPattern = "((?=.*\\d).{6,20})";

        this.bRegister = this.findViewById(id.btRegister);

        this.AddData();
    }

    public void AddData(){
        this.bRegister.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        if(RegisterActivity.this.etName.getText().toString().equals("") || RegisterActivity.this.etAge.getText().toString().equals("") || RegisterActivity.this.etEmail.getText().toString().equals("") || RegisterActivity.this.etPassword.getText().toString().equals("") || RegisterActivity.this.etPasswordConfirm.getText().toString().equals("") || RegisterActivity.this.etHeight.getText().toString().equals("") || RegisterActivity.this.etGender.getText().toString().equals("") || RegisterActivity.this.etWeight.getText().toString().equals("")){
                            Toast.makeText(RegisterActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
                        }

                        else {
                            if (RegisterActivity.this.etEmail.getText().toString().matches(RegisterActivity.this.emailPattern))
                            {
                                boolean pass = RegisterActivity.this.myDb.isUnique(RegisterActivity.this.etEmail.getText().toString());

                                if (pass == true) {

                                    if(RegisterActivity.this.etPassword.getText().toString().matches(RegisterActivity.this.passwordPattern)) {
                                        //Checks passwords match
                                        if (RegisterActivity.this.etPassword.getText().toString().equals(RegisterActivity.this.etPasswordConfirm.getText().toString())) {

                                            //Calculate and stores calorie intake
                                            double intake = RegisterActivity.this.calcDailyCal(Integer.parseInt(RegisterActivity.this.etAge.getText().toString()),
                                                    Integer.parseInt(RegisterActivity.this.etHeight.getText().toString()),
                                                    Integer.parseInt(RegisterActivity.this.etWeight.getText().toString()),
                                                    RegisterActivity.this.etGender.getText().toString()
                                            );

                                            if (intake == 0) {
                                                //do nothing
                                            } else {

                                                String calories = Double.toString(intake);

                                                boolean isInserted = RegisterActivity.this.myDb.insertUser(
                                                        RegisterActivity.this.etName.getText().toString(),
                                                        RegisterActivity.this.etEmail.getText().toString(),
                                                        RegisterActivity.this.etPassword.getText().toString(),
                                                        RegisterActivity.this.etAge.getText().toString(),
                                                        RegisterActivity.this.etHeight.getText().toString(),
                                                        RegisterActivity.this.etWeight.getText().toString(),
                                                        RegisterActivity.this.etGender.getText().toString(),
                                                        calories
                                                );

                                                if (isInserted == true) {
                                                    Intent registerIntent = new Intent(RegisterActivity.this, LoginScreenActivity.class);
                                                    RegisterActivity.this.startActivity(registerIntent);
                                                    RegisterActivity.this.finish();
                                                    Toast.makeText(RegisterActivity.this, "You have registered successfully", Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    Toast.makeText(RegisterActivity.this, "Error on registration!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, "Passwords do not Match", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "Password must contain one number and be longer than 6 characters but not more than 20", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Email must be unique", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Invalid Email Address", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

    public double calcDailyCal(int age,  double height, double weight, String gender){
        //using the calorie formula Mifflin-St. Jeor Equation to calculate expected intake
        double intake = 0;

        if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("Female")){
            intake = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        else if(gender.equalsIgnoreCase("BottomNavigationActivity") || gender.equalsIgnoreCase("Male")){
            intake = 10 * weight + 6.25 * height - 5 * age + 5;
        }
        else{
            intake = 0;
            Toast.makeText(this, "Invalid Gender", Toast.LENGTH_LONG).show();
        }

        return intake;

    }

}