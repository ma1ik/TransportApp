package uk.ac.rgu.transportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;


public class local_bus_stops extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_bus_stops);

        // add click listeners for the home page button
        ((Button) findViewById(R.id.localBusStopHomeButton)).setOnClickListener(this);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    // This was the second attempt of trying to implement the API to fetch and parse the data of each bus stops name
    // So when user clicks on the parse button in the local_bus_stops page, it grabs each bus stops name and displays it
    private void jsonParse() {
        String url = "http://transportapi.com/v3/uk/places.json?lat=57.1533753&lon=-2.1093067&type=bus_stop&app_id=a3fc9e50&app_key=5252ce9c184fc8ed83b5bc5f28db5b02";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("member");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject member = jsonArray.getJSONObject(i);

                                String busStop = member.getString("busStop");
                                String busName = member.getString("name");

                                mTextViewResult.append(busStop + ", " + String.valueOf(busName) + "\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
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


