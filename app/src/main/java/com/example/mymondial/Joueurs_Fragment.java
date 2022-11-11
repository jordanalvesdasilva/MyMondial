package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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
 * Use the {@link Joueurs_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Joueurs_Fragment extends Fragment {

    private ListView JoueursListview;

    private ArrayList<String> Player_Rang = new ArrayList<String>();
    private ArrayList<String> Player_Name = new ArrayList<String>();
    private ArrayList<String> Player_Stats = new ArrayList<String>();
    private ArrayList<String> Player_Min = new ArrayList<String>();
    private ArrayList<Integer> Player_Flag = new ArrayList<Integer>();

    public int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Joueurs_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Joueurs_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Joueurs_Fragment newInstance(String param1, String param2) {
        Joueurs_Fragment fragment = new Joueurs_Fragment();
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

        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_joueurs_, null);
        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JoueursListview = (ListView) inflate.findViewById(R.id.joueurs_live);
        Joueurs_adapter joueurs_adapter = new Joueurs_adapter(getActivity(), Player_Rang, Player_Name, Player_Stats, Player_Min, Player_Flag);
        String url = "https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=1193";   // CDM 2018
        //String url = "https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=3072";   // CDM 2022
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray data = jObject.getJSONArray("data");

                            Parse(0,20,data);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JoueursListview.setAdapter(joueurs_adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return inflate;
    }

    private void Parse(int min, int max, JSONArray data){

        try {
            // RAZ DES VARIABLES
            Player_Rang.clear();
            Player_Name.clear();
            Player_Stats.clear();
            Player_Min.clear();
            Player_Flag.clear();
            for (int i = min; i <max; i++) {

                // ------------------------------------------- TEST AVEC CDM 2018 --------------------------------------------------------------
                Player_Rang.add(data.getJSONObject(i).getString("pos"));
                Player_Name.add(data.getJSONObject(i).getJSONObject("player").getString("player_name"));
                Player_Stats.add(data.getJSONObject(i).getJSONObject("goals").getString("overall"));
                Player_Min.add(data.getJSONObject(i).getString("minutes_played"));

                Player_Flag.add(ChooseFlag(data.getJSONObject(i).getJSONObject("team").getString("team_name")));

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

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