package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class journey extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        // add click listeners for the home page button
        ((Button) findViewById(R.id.journeyHomePageButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.journeyHomePageButton) {
            Intent intent = new Intent(getApplicationContext(), home_page.class);
            startActivity(intent);
        }
    }


}
