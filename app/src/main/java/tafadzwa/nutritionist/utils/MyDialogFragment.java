package tafadzwa.nutritionist.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import tafadzwa.nutritionist.MainActivity;
import tafadzwa.nutritionist.ReminderActivity;


public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState){

        Builder builder = new Builder(this.getActivity());
        builder.setTitle("Personal Nutritionist");
        builder.setMessage("Do you want to be reminded of the meal?");
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(MyDialogFragment.this.getActivity(), ReminderActivity.class);
                MyDialogFragment.this.startActivity(intent);

                if(MyDialogFragment.this.getActivity()!=null){
                    MyDialogFragment.this.getActivity().finish();
                }
            }
        });
        builder.setNegativeButton("NO", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent registerIntent = new Intent(MyDialogFragment.this.getActivity(), MainActivity.class);
                MyDialogFragment.this.startActivity(registerIntent);
                Toast.makeText(MyDialogFragment.this.getActivity(), "Meal added to plan", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

}