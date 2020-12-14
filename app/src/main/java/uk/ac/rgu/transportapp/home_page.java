package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class home_page extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = home_page.class.getCanonicalName();

    //Initialise variables
    EditText etSource, etDestination;
    Button btnTrack;
    private List<StringRequest> requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // add click listeners for the local bus stop button, timetable button and saved locations button
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

    public void getTasksFromCloud(){
        String url = "http://transportapi.com/v3/uk/places.json?lat=57.1533753&lon=-2.1093067&type=bus_stop&app_id=a3fc9e50&app_key=5252ce9c184fc8ed83b5bc5f28db5b02.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success" + response);
                TaskJsonConverter converter = new TaskJsonConverter();
                List<local_bus_stops> bus_stops = converter.convertJsonStringToTask(response);
                RecyclerView.Adapter adapter = new RecyclerViewAdapter(getApplicationContext(), local_bus_stops);
                // Set the recycler views adapter
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error" + error.getLocalizedMessage());

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public void onClick(View view) {
        // this method for onClick is for each button on the homepage to navigate to each page
        if (view.getId() == R.id.localBusButton){
            Intent intent = new Intent(getApplicationContext(), local_bus_stops.class);
            startActivity(intent);
            getTasksFromCloud();
        } else if (view.getId() == R.id.busTimeTableButton){
            Intent intent = new Intent(getApplicationContext(), timetable.class);
            startActivity(intent);
        } else if(view.getId() == R.id.savedLocationsButton){
            Intent intent = new Intent(getApplicationContext(), saved_locations.class);
            startActivity(intent);
        }
    }

    public class TaskJsonConverter {
        List<local_bus_stops> local_bus_stops = new ArrayList<local_bus_stops>();

        public List<local_bus_stops> convertJsonStringToTask(String JsonString){
            try {
                JSONObject jsonObject = new JSONObject(JsonString);
                JSONObject membersObject = jsonObject.getJSONObject("members");
                Iterator<String> membersKeysIter = membersObject.keys();
                while (membersKeysIter.hasNext()){
                    String membersKey = membersKeysIter.next();
                    JSONObject memberObject = membersObject.getJSONObject(membersKey);
                    local_bus_stops bus_stop = new local_bus_stops();
                    bus_stop.setName(membersObject.getString("name"));
                    bus_stop.setTaskDescription(membersObject.getString("description"));
                    local_bus_stops.add(bus_stop);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return local_bus_stops;
        }
    }






}


