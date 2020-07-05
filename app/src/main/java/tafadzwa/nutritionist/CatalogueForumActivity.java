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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;
import tafadzwa.nutritionist.utils.DatabaseHelper;

public class CatalogueForumActivity extends AppCompatActivity {

    Button bAdd;
    EditText etFoodName, etCalories, etIngrediant;
    CheckBox vegetarian;
    String type = "";
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_catalogue_forum);

        this.myDB = new DatabaseHelper(this);
        this.bAdd = this.findViewById(id.bAddDiet);
        this.vegetarian = this.findViewById(id.cbType);
        this.etFoodName = this.findViewById(id.etFoodName);
        this.etCalories = this.findViewById(id.etCalories);
        this.etIngrediant = this.findViewById(id.etIngrediants);

        this.AddData();
    }
    public void AddData(){
        this.bAdd.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        if(CatalogueForumActivity.this.etFoodName.getText().toString().equals("") || CatalogueForumActivity.this.etIngrediant.getText().toString().equals("") || CatalogueForumActivity.this.etCalories.getText().toString().equals("")){
                            Toast.makeText(CatalogueForumActivity.this, "All Fields Required", Toast.LENGTH_LONG).show();
                        }

                        else{
                            boolean pass = CatalogueForumActivity.this.myDB.uniqueFood(CatalogueForumActivity.this.etFoodName.getText().toString());

                            if(pass == true){

                                if(CatalogueForumActivity.this.vegetarian.isChecked()){
                                    CatalogueForumActivity.this.type = "Veg";
                                }

                                else{
                                    CatalogueForumActivity.this.type = "Meat";
                                }

                                boolean isInserted = CatalogueForumActivity.this.myDB.insertFood(
                                        CatalogueForumActivity.this.etFoodName.getText().toString(),
                                        CatalogueForumActivity.this.etCalories.getText().toString(),
                                        CatalogueForumActivity.this.type,
                                        CatalogueForumActivity.this.etIngrediant.getText().toString()
                                );

                                if (isInserted == true) {
                                    Intent registerIntent = new Intent(CatalogueForumActivity.this, CatalogueActivity.class);
                                    startActivity(registerIntent);
                                    Toast.makeText(CatalogueForumActivity.this, "Item Inserted", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(CatalogueForumActivity.this, "Data Failed to Insert", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(CatalogueForumActivity.this, "Name Matches Item in Catalogue", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }

}