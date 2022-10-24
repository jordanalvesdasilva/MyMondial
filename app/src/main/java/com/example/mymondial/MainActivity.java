package com.example.mymondial;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.mymondial.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList<String> live = new ArrayList<String>();
        ListView text = (ListView) findViewById(R.id.match_live);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, live );
        String url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=1243";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray data = jObject.getJSONArray("data");
                            System.out.println("Response is: " + response.substring(0,500));
                            for (int i = 0; i < data.length(); i++) {
                                String home_team_name = data.getJSONObject(i).getJSONObject("home_team").getString("name");
                                String away_team_name = data.getJSONObject(i).getJSONObject("away_team").getString("name");
                                String score = data.getJSONObject(i).getJSONObject("stats").getString("ft_score");
                                live.add(home_team_name + "  " + score + "  " + away_team_name );
                            }
                            text.setAdapter(arrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                System.out.println(error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        System.out.println(live);

    }

}