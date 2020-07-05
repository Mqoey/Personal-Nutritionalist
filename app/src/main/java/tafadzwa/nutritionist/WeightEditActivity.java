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

public class WeightEditActivity extends AppCompatActivity {

    Button bChangeWeight;
    TextView tvOldWeight;
    EditText etNewWeight, etEmailConfirm;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_weight_edit);

        this.myDb = new DatabaseHelper(this);

        this.bChangeWeight = this.findViewById(id.bChangeWeight);
        this.tvOldWeight = this.findViewById(id.tvCurrentWeight);
        this.etEmailConfirm = this.findViewById(id.etEmailConfirm);
        this.etNewWeight = this.findViewById(id.etNewWeight);

        this.tvOldWeight.setText(Double.toString(this.myDb.getWeight(this.myDb.retrieveUser())));
        this.UpdateWeight();
    }

    public void UpdateWeight() {
        this.bChangeWeight.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(WeightEditActivity.this.etEmailConfirm.getText().toString().equals("") || WeightEditActivity.this.etNewWeight.getText().toString().equals("") ) {
                            Toast.makeText(WeightEditActivity.this, "All Fields Must be Entered", Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean isUpdated = WeightEditActivity.this.myDb.updateWeight(WeightEditActivity.this.myDb.retrieveUser(),
                                    Double.parseDouble(WeightEditActivity.this.etNewWeight.getText().toString()),
                                    WeightEditActivity.this.etEmailConfirm.getText().toString()
                            );

                            if (isUpdated == true) {
                                Intent registerIntent = new Intent(WeightEditActivity.this, UserDetailsActivity.class);
                                WeightEditActivity.this.startActivity(registerIntent);
                                WeightEditActivity.this.finish();
                                Toast.makeText(WeightEditActivity.this, "Weight Updated to: " + WeightEditActivity.this.etNewWeight.getText() + "kg", Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(WeightEditActivity.this, "UserActivity does not Exist", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }
}