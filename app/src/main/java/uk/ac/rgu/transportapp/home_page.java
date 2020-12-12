package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_page extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // add click listeners for the journey button, local bus stop button, timetable button and saved locations button
        ((Button) findViewById(R.id.journeyButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.localBusButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.busTimeTableButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.savedLocationsButton)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.journeyButton) {
            Intent intent = new Intent(getApplicationContext(), journey.class);
            startActivity(intent);
        } else if (view.getId() == R.id.localBusButton){
            Intent intent = new Intent(getApplicationContext(), local_bus_stops.class);
            startActivity(intent);
        } else if (view.getId() == R.id.busTimeTableButton){
            Intent intent = new Intent(getApplicationContext(), timetable.class);
            startActivity(intent);
        } else if(view.getId() == R.id.savedLocationsButton){
            Intent intent = new Intent(getApplicationContext(), saved_locations.class);
            startActivity(intent);
        }
    }

}


