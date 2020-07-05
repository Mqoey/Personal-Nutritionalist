package tafadzwa.nutritionist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import tafadzwa.nutritionist.R.layout;

/**
 * Created by mqondisi on 6/4/20.
 */

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_notification);
    }
}
