package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Match_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Match_Fragment extends Fragment {

    private ListView InfoListview;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> Home_Time = new ArrayList<String>();
    private ArrayList<String> Home_Who = new ArrayList<String>();
    private ArrayList<String> Home_Who_Help = new ArrayList<String>();
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<Integer> Logo = new ArrayList<Integer>();
    private ArrayList<String> Away_Time= new ArrayList<String>();
    private ArrayList<String> Away_Who= new ArrayList<String>();
    private ArrayList<String> Away_Who_Help= new ArrayList<String>();
    public int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Match_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Match_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Match_Fragment newInstance(String param1, String param2) {
        Match_Fragment fragment = new Match_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_match_, null);

        getParentFragmentManager().setFragmentResultListener("match_id", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String match_id = result.getString("MID");
                RequetAPI(match_id);

                swipeRefreshLayout = getView().findViewById(R.id.RefreshMatch);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(false);
                        RequetAPI(match_id);
                    }
                });
            }
        });
        return inflate;
    }


    private void RequetAPI(String match_id){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        InfoListview = (ListView) getView().findViewById(R.id.info_listview);
        Information_adapter information_adapter = new Information_adapter(getActivity(), Home_Time, Home_Who, Home_Who_Help, Score, Logo, Away_Time, Away_Who, Away_Who_Help );
        String url = "https://app.sportdataapi.com/api/v1/soccer/matches/"+match_id+"?apikey=193beda0-5093-11ed-aa03-b339e6eb1617";   //CDM
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Home_Time.clear();
                            Home_Who.clear();
                            Home_Who_Help.clear();
                            Score.clear();
                            Logo.clear();
                            Away_Time.clear();
                            Away_Who.clear();
                            Away_Who_Help.clear();
                            TextView HomeTeam = (TextView) getView().findViewById(R.id.HomeTeamMatch);
                            ImageView HomeTeamFlag = (ImageView) getView().findViewById(R.id.HomeTeamFlagMatch);
                            TextView AwayTeam = (TextView) getView().findViewById(R.id.AwayTeamMatch);
                            ImageView AwayTeamFlag = (ImageView) getView().findViewById(R.id.AwayTeamFlagMatch);
                            TextView Time = (TextView) getView().findViewById(R.id.TimeMatch);
                            TextView Result = (TextView) getView().findViewById(R.id.ResultMatch);
                            JSONObject jObject = new JSONObject(response);
                            JSONObject data = jObject.getJSONObject("data");
                            String HometeamID = data.getJSONObject("home_team").getString("team_id");
                            String AwayteamID = data.getJSONObject("away_team").getString("team_id");
                            int i=0;
                            if (data.has("match_events") && !data.isNull("match_events")){
                                JSONArray match_events = data.getJSONArray("match_events");
                                for (i = 0; i < match_events.length(); i++){
                                    if (match_events.getJSONObject(i).getString("type").equals("goal")) {
                                        if (match_events.getJSONObject(i).getString("team_id").equals(HometeamID)) {
                                            Home_Time.add(match_events.getJSONObject(i).getString("minute"));
                                            Home_Who.add(match_events.getJSONObject(i).getString("player_name"));
                                            Home_Who_Help.add(match_events.getJSONObject(i).getString("related_player_name"));
                                            Score.add(match_events.getJSONObject(i).getString("result"));
                                            Logo.add(R.drawable.ic_baseline_sports_soccer_24);
                                            Away_Time.add(" ");
                                            Away_Who.add(" ");
                                            Away_Who_Help.add(" ");
                                        } else {
                                            Away_Time.add(match_events.getJSONObject(i).getString("minute"));
                                            Away_Who.add(match_events.getJSONObject(i).getString("player_name"));
                                            Away_Who_Help.add(match_events.getJSONObject(i).getString("related_player_name"));
                                            Score.add(match_events.getJSONObject(i).getString("result"));
                                            Logo.add(R.drawable.ic_baseline_sports_soccer_24);
                                            Home_Time.add(" ");
                                            Home_Who.add(" ");
                                            Home_Who_Help.add(" ");
                                        }
                                    }
                                }
                            }
                            HomeTeam.setText(data.getJSONObject("home_team").getString("name"));
                            AwayTeam.setText(data.getJSONObject("away_team").getString("name"));
                            HomeTeamFlag.setImageResource(ChooseFlag(data.getJSONObject("home_team").getString("name")));
                            AwayTeamFlag.setImageResource(ChooseFlag(data.getJSONObject("away_team").getString("name")));
                            if ((data.getString("status").equals("notstarted"))) {
                                Time.setText(data.getString("match_start"));
                                Result.setText(" - ");
                            } else if (data.getString("status").equals("finished")) {
                                Time.setText("TER");
                                Result.setText(data.getJSONObject("stats").getString("ft_score"));
                            } else {
                                Time.setText(data.getString("minute"));
                                Result.setText(Score.get(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        InfoListview.setAdapter(information_adapter);
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
    public int ChooseFlag(String name_team){
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
            default:
                return R.drawable.ic_baseline_flag_24;
        }
    }
}