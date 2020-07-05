package tafadzwa.nutritionist;

/**
 * Created by mqondisi on 5/20/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.utils.DatabaseHelper;


public class HeightEditActivity extends AppCompatActivity {

    Button bChangeHeight;
    TextView tvOldHeight;
    EditText etNewHeight, etEmailConfirm;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_height_edit);

        this.myDb = new DatabaseHelper(this);

        this.bChangeHeight = this.findViewById(id.bChangeHeight);
        this.tvOldHeight = this.findViewById(id.tvCurrentHeight);
        this.etEmailConfirm = this.findViewById(id.etEmailConfirm);
        this.etNewHeight = this.findViewById(id.etNewHeight);
        this.tvOldHeight.setText(Double.toString(this.myDb.getHeight(this.myDb.retrieveUser())));
        this.UpdateHeight();
    }

    public void UpdateHeight() {
        this.bChangeHeight.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(HeightEditActivity.this.etEmailConfirm.getText().toString().equals("") || HeightEditActivity.this.etNewHeight.getText().toString().equals("") ) {
                            Toast.makeText(HeightEditActivity.this, "All Fields Must be Entered", Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean isUpdated = HeightEditActivity.this.myDb.updateHeight(HeightEditActivity.this.myDb.retrieveUser(),
                                    Double.parseDouble(HeightEditActivity.this.etNewHeight.getText().toString()),
                                    HeightEditActivity.this.etEmailConfirm.getText().toString()
                            );

                            if (isUpdated == true) {
                                Intent registerIntent = new Intent(HeightEditActivity.this, UserDetailsActivity.class);
                                HeightEditActivity.this.startActivity(registerIntent);
                                HeightEditActivity.this.finish();
                                Toast.makeText(HeightEditActivity.this, "Height Updated to: " + HeightEditActivity.this.etNewHeight.getText() + "cm", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(HeightEditActivity.this, "UserActivity does not Exist", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }
}