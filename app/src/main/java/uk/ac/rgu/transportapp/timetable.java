package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class timetable extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        // add click listeners for the home page button
        ((Button) findViewById(R.id.timetableHomeButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.timetableHomeButton) {
            Intent intent = new Intent(getApplicationContext(), home_page.class);
            startActivity(intent);
        }
    }


}