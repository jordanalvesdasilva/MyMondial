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
    private ListView MatchListview;
    private ArrayList<String> HomeTeam = new ArrayList<String>();
    private ArrayList<String> AwayTeam = new ArrayList<String>() ;
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();
    private ArrayList<Integer> HomeTeamFlag = new ArrayList<Integer>();
    private ArrayList<Integer> AwayTeamFlag = new ArrayList<Integer>();
    private int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa };

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
        Match_adapter match_adapter = new Match_adapter(getApplicationContext(),HomeTeam, AwayTeam, Score, Time, HomeTeamFlag, AwayTeamFlag );
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
                                    Parse(0,16,data);
                                    break;
                                case "Journée 2":
                                    Parse(16,32,data);
                                    break;
                                case "Journée 3":
                                    Parse(32,48,data);
                                    break;
                                case "Huitièmes de finale":
                                    Parse(48,57,data);
                                    break;
                                case "Quarts de finale":
                                    Parse(57,61,data);
                                    break;
                                case "Demi-finales":
                                    Parse(61,63,data);
                                    break;
                                case "Finale":
                                    Parse(63,64,data);
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

    private void Parse(int min, int max, JSONArray data){

        try {
            HomeTeam.clear();
            AwayTeam.clear();
            Score.clear();
            Time.clear();
            HomeTeamFlag.clear();
            AwayTeamFlag.clear();
            for (int i = min; i <max; i++) {
                if (data.getJSONObject(i).getJSONObject("home_team").has("name")) {
                    HomeTeam.add(data.getJSONObject(i).getJSONObject("home_team").getString("name"));
                    AwayTeam.add(data.getJSONObject(i).getJSONObject("away_team").getString("name"));
                    HomeTeamFlag.add(ChooseFlag(data.getJSONObject(i).getJSONObject("home_team").getString("name")));
                    AwayTeamFlag.add(ChooseFlag(data.getJSONObject(i).getJSONObject("away_team").getString("name")));
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
                    Time.add(data.getJSONObject(i).getString("match_start"));
                } else if (data.getJSONObject(i).getString("status").equals("finished")) {
                    Time.add("TER");
                } else {
                    Time.add(data.getJSONObject(i).getString("minute"));
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private int ChooseFlag(String name_team){
        switch (name_team){
            case "Senegal":
                return Flag[26];
            case "Netherlands":
                return Flag[20];
            case "England":
                return Flag[1];
            case "Iran":
                return Flag[16];
            case "Qatar":
                return Flag[24];
            case "Ecuador":
                return Flag[12];
            case "USA":
                return Flag[31];
            case "Wales":
                return Flag[21];
            case "Argentina":
                return Flag[3];
            case "Saudi Arabia":
                return Flag[2];
            case "Denmark":
                return Flag[11];
            case "Tunisia":
                return Flag[29];
            case "Mexico":
                return Flag[19];
            case "Poland":
                return Flag[22];
            case "France":
                return Flag[14];
            case "Australia":
                return Flag[4];
            case "Morocco":
                return Flag[18];
            case "Croatia":
                return Flag[9];
            case "Germany":
                return Flag[0];
            case "Japan":
                return Flag[17];
            case "Spain":
                return Flag[13];
            case "Costa Rica":
                return Flag[10];
            case "Belgium":
                return Flag[5];
            case "Canada":
                return Flag[8];
            case "Switzerland":
                return Flag[28];
            case "Cameroon":
                return Flag[7];
            case "Uruguay":
                return Flag[30];
            case "Republic of Korea":
                return Flag[25];
            case "Portugal":
                return Flag[23];
            case "Ghana":
                return Flag[15];
            case "Brazil":
                return Flag[6];
            case "Serbia":
                return Flag[27];
        }
        return 0;
    }
}