package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class home_page extends AppCompatActivity implements View.OnClickListener {
    //Initialise variables
    EditText etSource, etDestination;
    Button btnTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // add click listeners for the journey button, local bus stop button, timetable button and saved locations button
        ((Button) findViewById(R.id.journeyButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.localBusButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.busTimeTableButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.savedLocationsButton)).setOnClickListener(this);

        //Assign variable
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btnTrack = findViewById(R.id.start_end_button);

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting the value from edit text
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check the condition
                if (sSource.equals("") && sDestination.equals("")) {
                    // When both of the values are black
                    Toast.makeText(getApplicationContext()
                            ,"Please enter start and end location", Toast.LENGTH_SHORT).show();
                }else {
                    //When both of the values are filled, display the map track
                    DisplayTrack(sSource, sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        // If the device doesn't have a map application installed, then it will redirect to the play store.
        try {
            // When google maps is being installed, it will initialise the url
            // initialise the uri
            Uri uri = Uri.parse("https://www.google.co.uk/maps/dir/" + sSource + "/" + sDestination);
            // Initialise intent with an action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            // Setting the package
            intent.setPackage("com.google.android.apps.maps");
            // Setting the flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Starting the activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            // When google maps is not installed do the following
            //Initialise uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            // Initialise intent with the action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // Setting the flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Starting the activity
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        // this method for onClick is for each button on the homepage to navigate to each page
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


