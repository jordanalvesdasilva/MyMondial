package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ListView MatchListview;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> HomeTeam = new ArrayList<String>();
    private ArrayList<String> AwayTeam = new ArrayList<String>() ;
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();
    private ArrayList<Integer> HomeTeamFlag = new ArrayList<Integer>();
    private ArrayList<Integer> AwayTeamFlag = new ArrayList<Integer>();
    private ArrayList<String> ID = new ArrayList<String>();
    public int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
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

        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_home_, null);
        Spinner spinner = inflate.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.journée, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        // Inflate the layout for this fragment
        return inflate;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String current_day = parent.getItemAtPosition(position).toString();
        RequetAPI(current_day);

        MatchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Layout, new Match_Fragment(), "Match_Fragment");
                fragmentTransaction.commit();

                System.out.println(id);
                Bundle match_id = new Bundle();
                match_id.putString("MID", Long.toString(id));
                getParentFragmentManager().setFragmentResult("match_id",match_id );
            }
        });

        swipeRefreshLayout = getView().findViewById(R.id.RefreshHome);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RequetAPI(current_day);
                Toast.makeText(parent.getContext(), "Reloaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
            ID.clear();
            for (int i = min; i <max; i++) {
                ID.add(data.getJSONObject(i).getString("id"));
                if (data.getJSONObject(i).has("home_team_en")) {
                    HomeTeam.add(data.getJSONObject(i).getString("home_team_en"));
                    AwayTeam.add(data.getJSONObject(i).getString("away_team_en"));
                    HomeTeamFlag.add(ChooseFlag(data.getJSONObject(i).getString("home_team_en")));
                    AwayTeamFlag.add(ChooseFlag(data.getJSONObject(i).getString("away_team_en")));
                } else {
                    HomeTeam.add("not found");
                    AwayTeam.add("not found");
                }
                if ((data.getJSONObject(i).getString("time_elapsed").equals("notstarted"))) {
                    Time.add(data.getJSONObject(i).getString("local_date"));
                    Score.add("  -  ");
                } else if (data.getJSONObject(i).getString("time_elapsed").equals("finished")) {
                    Time.add("TER");
                    String ScoreHome = data.getJSONObject(i).getString("home_score");
                    String ScoreAway = data.getJSONObject(i).getString("away_score");
                    Score.add(ScoreHome + "-" +ScoreAway);
                } else {
                    Time.add("LIVE");
                    String ScoreHome = data.getJSONObject(i).getString("home_score");
                    String ScoreAway = data.getJSONObject(i).getString("away_score");
                    Score.add(ScoreHome + "-" +ScoreAway);
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void RequetAPI(String current_day){
        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        MatchListview = (ListView) getView().findViewById(R.id.match_live);
        Match_adapter match_adapter = new Match_adapter(getActivity(), HomeTeam, AwayTeam, Score, Time, HomeTeamFlag, AwayTeamFlag, ID);
        //String url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=3072";   //CDM API1
        String url ="http://api.cup2022.ir/api/v1/match";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            System.out.println(response);
                            JSONArray data = jObject.getJSONArray("data");
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
                        match_adapter.notifyDataSetChanged();
                        MatchListview.setAdapter(match_adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                System.out.println(error);
            }

        })
        {@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzdjZWY5NDQ4NzA5MjMzZmRhMzNhODIiLCJpYXQiOjE2NjkxMzIxODEsImV4cCI6MTY2OTIxODU4MX0.CZ_Y-fKKZ3VHr1sUJaxvPvEiyXe2IvmSYA-V5rCEHKU");
            headerMap.put("Content-Type", "application/json");
            return headerMap;}
        };
        queue.add(stringRequest);
    }

    public int ChooseFlag(String name_team){
        switch (name_team){
            case "Senegal":
                return Flag[26];
            case "Nederlands":
                return Flag[20];
            case "England":
                return Flag[1];
            case "Iran":
                return Flag[16];
            case "Qatar":
                return Flag[24];
            case "Ecuador":
                return Flag[12];
            case "United States":
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
            case "South Korea":
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