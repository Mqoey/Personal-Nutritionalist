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

public class PasswordRecoveryActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etPasswordConfirm, etName;
    TextView bUpdatePassword;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_password_recovery);


        this.myDb = new DatabaseHelper(this);

        this.etEmail = this.findViewById(id.etEmail);
        this.etPassword = this.findViewById(id.etPassword);
        this.etPasswordConfirm = this.findViewById(id.etPasswordConfirm);
        this.etName = this.findViewById(id.etName);
        this.bUpdatePassword = this.findViewById(id.bUpdatePass);
        this.UpdatePassword();
    }

    public void UpdatePassword() {
        this.bUpdatePassword.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(PasswordRecoveryActivity.this.etPassword.getText().toString().equals(PasswordRecoveryActivity.this.etPasswordConfirm.getText().toString())) {

                            boolean isUpdated = PasswordRecoveryActivity.this.myDb.updateUser(
                                    PasswordRecoveryActivity.this.etEmail.getText().toString(),
                                    PasswordRecoveryActivity.this.etName.getText().toString(),
                                    PasswordRecoveryActivity.this.etPassword.getText().toString()
                            );

                            if (isUpdated == true) {
                                Intent registerIntent = new Intent(PasswordRecoveryActivity.this, LoginScreenActivity.class);
                                PasswordRecoveryActivity.this.startActivity(registerIntent);
                                PasswordRecoveryActivity.this.finish();
                                Toast.makeText(PasswordRecoveryActivity.this, "Password Updated", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PasswordRecoveryActivity.this, "UserActivity does not Exist", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(PasswordRecoveryActivity.this, "Passwords Failed to Match", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}