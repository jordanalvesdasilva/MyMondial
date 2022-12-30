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
    private ArrayList<String> Player_Match = new ArrayList<String>();


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
        // Inflate le layout pour ce fragment

        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_joueurs_, null);
        // Requete de l'API et remplissage des tableaux
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JoueursListview = (ListView) inflate.findViewById(R.id.joueurs_live);
        Joueurs_adapter joueurs_adapter = new Joueurs_adapter(getActivity(), Player_Rang, Player_Name, Player_Stats, Player_Match);
        String url;
        String  years = ((Global_variable) getActivity().getApplication()).getyears();
        if (years.equals("2018")){
            url = "https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=1193";   //CDM 2018
        }
        else {
            url = "https://app.sportdataapi.com/api/v1/soccer/topscorers?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=3072";   //CDM 2022
        }
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
            Player_Match.clear();
            for (int i = min; i <max; i++) {

                // ------------------------------------------- TEST AVEC CDM 2018 --------------------------------------------------------------
                Player_Rang.add(data.getJSONObject(i).getString("pos"));
                Player_Name.add(data.getJSONObject(i).getJSONObject("player").getString("player_name"));
                Player_Stats.add(data.getJSONObject(i).getJSONObject("goals").getString("overall"));
                Player_Match.add(data.getJSONObject(i).getString("matches_played"));

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

}