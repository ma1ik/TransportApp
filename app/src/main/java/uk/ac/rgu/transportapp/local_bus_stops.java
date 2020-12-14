package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class local_bus_stops extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_bus_stops);

        // add click listeners for the home page button
        ((Button) findViewById(R.id.localBusStopHomeButton)).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.localBusStopHomeButton) {
            Intent intent = new Intent(getApplicationContext(), home_page.class);
            startActivity(intent);
        }
    }

    public void setTaskDescription(String description) {
    }

    public void setName(String name) {
    }
}


