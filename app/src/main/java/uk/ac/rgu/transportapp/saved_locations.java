package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class saved_locations extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_locations);

        // add click listeners for the home page button
        ((Button) findViewById(R.id.savedLocationsHomePageButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.savedLocationsHomePageButton) {
            Intent intent = new Intent(getApplicationContext(), home_page.class);
            startActivity(intent);
        }
    }


}