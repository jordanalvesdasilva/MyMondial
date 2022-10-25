package com.example.mymondial;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ListView MatchListview;
    private ArrayList<String> HomeTeam = new ArrayList<String>();
    private ArrayList<String> AwayTeam = new ArrayList<String>() ;
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.journée, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
    String current_day = parent.getItemAtPosition(position).toString();
    Toast.makeText(parent.getContext(), current_day, Toast.LENGTH_SHORT).show();

        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(this);
        MatchListview = (ListView) findViewById(R.id.match_live);
        Match_adapter match_adapter = new Match_adapter(getApplicationContext(),HomeTeam, AwayTeam, Score, Time );
        //String url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=1243";  //LDC
        String url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=3072";   //CDM
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray data = jObject.getJSONArray("data");
                            System.out.println("Response is: " + response.substring(0, 500));
                            switch (current_day) {
                                case "Journée 1":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 0; i < 16; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Journée 2":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 16; i < 32; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Journée 3":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 32; i < 48; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Huitièmes de finale":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 48; i < 57; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Quarts de finale":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 57; i < 61; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Demi-finales":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    for (int i = 61; i < 63; i++) {
                                        if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                                            HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                                            AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                                        } else {
                                            HomeTeam.add("not found");
                                            AwayTeam.add("not found");
                                        }
                                        if (data.getJSONObject(i).getJSONObject("stats").getString("ft_score") == "null") {
                                            Score.add("  -  ");
                                        } else {
                                            Score.add(data.getJSONObject(i).getJSONObject("stats").getString("ft_score"));
                                        }
                                        if ((data.getJSONObject(i).getString("status").equals("notstarted"))) {
                                            Time.add("");
                                        } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                                            Time.add("TER");
                                        } else {
                                            Time.add(data.getJSONObject(i).getString("minute"));
                                        }
                                    }
                                    break;
                                case "Finale":
                                    HomeTeam.clear();
                                    AwayTeam.clear();
                                    Score.clear();
                                    Time.clear();
                                    if (data.getJSONObject(63).getJSONObject("home_team").has("name")) {
                                        HomeTeam.add(data.getJSONObject(63).getJSONObject("home_team").getString("name"));
                                        AwayTeam.add(data.getJSONObject(63).getJSONObject("away_team").getString("name"));
                                    } else {
                                        HomeTeam.add("not found");
                                        AwayTeam.add("not found");
                                    }
                                    if (data.getJSONObject(63).getJSONObject("stats").getString("ft_score") == "null") {
                                        Score.add("  -  ");
                                    } else {
                                        Score.add(data.getJSONObject(63).getJSONObject("stats").getString("ft_score"));
                                    }
                                    if ((data.getJSONObject(63).getString("status").equals("notstarted"))) {
                                        Time.add("");
                                    } else if (data.getJSONObject(63).getString("status").equals("finished")) {
                                        Time.add("TER");
                                    } else {
                                        Time.add(data.getJSONObject(63).getString("minute"));
                                    }

                            break;
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MatchListview.setAdapter(match_adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}