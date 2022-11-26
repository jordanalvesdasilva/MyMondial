package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhaseFinale_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhaseFinale_Fragment extends Fragment {

    public int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa,R.drawable.colombie, R.drawable.panama, R.drawable.suede,R.drawable.nigeria,R.drawable.perou,R.drawable.iceland,R.drawable.egypte,R.drawable.russie };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhaseFinale_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhaseFinale_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhaseFinale_Fragment newInstance(String param1, String param2) {
        PhaseFinale_Fragment fragment = new PhaseFinale_Fragment();
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
        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_phase_finale_, null);
        RequetAPI(inflate);
        return inflate;
    }

    private void RequetAPI(View inflate){
        // Huitièmes de finale
        ImageView HuitHome1 = (ImageView) inflate.findViewById(R.id.HuitHome1);
        ImageView HuitAway1 = (ImageView) inflate.findViewById(R.id.HuitAway1);
        TextView HuitHomeScore1 = (TextView) inflate.findViewById(R.id.HuitHomeScore1);
        TextView HuitAwayScore1 = (TextView) inflate.findViewById(R.id.HuitAwayScore1);
        ImageView HuitHome2 = (ImageView) inflate.findViewById(R.id.HuitHome2);
        ImageView HuitAway2 = (ImageView) inflate.findViewById(R.id.HuitAway2);
        TextView HuitHomeScore2 = (TextView) inflate.findViewById(R.id.HuitHomeScore2);
        TextView HuitAwayScore2 = (TextView) inflate.findViewById(R.id.HuitAwayScore2);
        ImageView HuitHome3 = (ImageView) inflate.findViewById(R.id.HuitHome3);
        ImageView HuitAway3 = (ImageView) inflate.findViewById(R.id.HuitAway3);
        TextView HuitHomeScore3 = (TextView) inflate.findViewById(R.id.HuitHomeScore3);
        TextView HuitAwayScore3 = (TextView) inflate.findViewById(R.id.HuitAwayScore3);
        ImageView HuitHome4 = (ImageView) inflate.findViewById(R.id.HuitHome4);
        ImageView HuitAway4 = (ImageView) inflate.findViewById(R.id.HuitAway4);
        TextView HuitHomeScore4 = (TextView) inflate.findViewById(R.id.HuitHomeScore4);
        TextView HuitAwayScore4 = (TextView) inflate.findViewById(R.id.HuitAwayScore4);
        ImageView HuitHome5 = (ImageView) inflate.findViewById(R.id.HuitHome5);
        ImageView HuitAway5 = (ImageView) inflate.findViewById(R.id.HuitAway5);
        TextView HuitHomeScore5 = (TextView) inflate.findViewById(R.id.HuitHomeScore5);
        TextView HuitAwayScore5 = (TextView) inflate.findViewById(R.id.HuitAwayScore5);
        ImageView HuitHome6 = (ImageView) inflate.findViewById(R.id.HuitHome6);
        ImageView HuitAway6 = (ImageView) inflate.findViewById(R.id.HuitAway6);
        TextView HuitHomeScore6 = (TextView) inflate.findViewById(R.id.HuitHomeScore6);
        TextView HuitAwayScore6 = (TextView) inflate.findViewById(R.id.HuitAwayScore6);
        ImageView HuitHome7 = (ImageView) inflate.findViewById(R.id.HuitHome7);
        ImageView HuitAway7 = (ImageView) inflate.findViewById(R.id.HuitAway7);
        TextView HuitHomeScore7 = (TextView) inflate.findViewById(R.id.HuitHomeScore7);
        TextView HuitAwayScore7 = (TextView) inflate.findViewById(R.id.HuitAwayScore7);
        ImageView HuitHome8 = (ImageView) inflate.findViewById(R.id.HuitHome8);
        ImageView HuitAway8 = (ImageView) inflate.findViewById(R.id.HuitAway8);
        TextView HuitHomeScore8 = (TextView) inflate.findViewById(R.id.HuitHomeScore8);
        TextView HuitAwayScore8 = (TextView) inflate.findViewById(R.id.HuitAwayScore8);
        // Quarts de finale
        ImageView QuatreHome1 = (ImageView) inflate.findViewById(R.id.QuatreHome1);
        ImageView QuatreAway1 = (ImageView) inflate.findViewById(R.id.QuatreAway1);
        TextView QuatreHomeScore1 = (TextView) inflate.findViewById(R.id.QuatreHomeScore1);
        TextView QuatreAwayScore1 = (TextView) inflate.findViewById(R.id.QuatreAwayScore1);
        ImageView QuatreHome2 = (ImageView) inflate.findViewById(R.id.QuatreHome2);
        ImageView QuatreAway2 = (ImageView) inflate.findViewById(R.id.QuatreAway2);
        TextView QuatreHomeScore2 = (TextView) inflate.findViewById(R.id.QuatreHomeScore2);
        TextView QuatreAwayScore2 = (TextView) inflate.findViewById(R.id.QuatreAwayScore2);
        ImageView QuatreHome3 = (ImageView) inflate.findViewById(R.id.QuatreHome3);
        ImageView QuatreAway3 = (ImageView) inflate.findViewById(R.id.QuatreAway3);
        TextView QuatreHomeScore3 = (TextView) inflate.findViewById(R.id.QuatreHomeScore3);
        TextView QuatreAwayScore3 = (TextView) inflate.findViewById(R.id.QuatreAwayScore3);
        ImageView QuatreHome4 = (ImageView) inflate.findViewById(R.id.QuatreHome4);
        ImageView QuatreAway4 = (ImageView) inflate.findViewById(R.id.QuatreAway4);
        TextView QuatreHomeScore4 = (TextView) inflate.findViewById(R.id.QuatreHomeScore4);
        TextView QuatreAwayScore4 = (TextView) inflate.findViewById(R.id.QuatreAwayScore4);
        // Demi-finales
        ImageView DeuxHome1 = (ImageView) inflate.findViewById(R.id.DeuxHome1);
        ImageView DeuxAway1 = (ImageView) inflate.findViewById(R.id.DeuxAway1);
        TextView DeuxHomeScore1 = (TextView) inflate.findViewById(R.id.DeuxHomeScore1);
        TextView DeuxAwayScore1 = (TextView) inflate.findViewById(R.id.DeuxAwayScore1);
        ImageView DeuxHome2 = (ImageView) inflate.findViewById(R.id.DeuxHome2);
        ImageView DeuxAway2 = (ImageView) inflate.findViewById(R.id.DeuxAway2);
        TextView DeuxHomeScore2 = (TextView) inflate.findViewById(R.id.DeuxHomeScore2);
        TextView DeuxAwayScore2 = (TextView) inflate.findViewById(R.id.DeuxAwayScore2);
        // finale
        ImageView FinaleHome1 = (ImageView) inflate.findViewById(R.id.FinaleHome1);
        ImageView FinaleAway1 = (ImageView) inflate.findViewById(R.id.FinaleAway1);
        TextView FinaleHomeScore1 = (TextView) inflate.findViewById(R.id.FinaleHomeScore1);
        TextView FinaleAwayScore1 = (TextView) inflate.findViewById(R.id.FinaleAwayScore1);
        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url;
        String  years = ((Global_variable) getActivity().getApplication()).getyears();
        if (years.equals("2018")){
            url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=1193";   //CDM 2018
        }
        else {
            url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=3072";   //CDM 2022
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray data = jObject.getJSONArray("data");
                            
                            HuitHome1.setImageResource(ChooseFlag(data.getJSONObject(48).getJSONObject("home_team").getString("name")));
                            HuitAway1.setImageResource(ChooseFlag(data.getJSONObject(48).getJSONObject("away_team").getString("name")));
                            HuitHomeScore1.setText(data.getJSONObject(48).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore1.setText(data.getJSONObject(48).getJSONObject("stats").getString("away_score"));
                            HuitHome2.setImageResource(ChooseFlag(data.getJSONObject(49).getJSONObject("home_team").getString("name")));
                            HuitAway2.setImageResource(ChooseFlag(data.getJSONObject(49).getJSONObject("away_team").getString("name")));
                            HuitHomeScore2.setText(data.getJSONObject(49).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore2.setText(data.getJSONObject(49).getJSONObject("stats").getString("away_score"));
                            HuitHome3.setImageResource(ChooseFlag(data.getJSONObject(50).getJSONObject("home_team").getString("name")));
                            HuitAway3.setImageResource(ChooseFlag(data.getJSONObject(50).getJSONObject("away_team").getString("name")));
                            HuitHomeScore3.setText(data.getJSONObject(50).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore3.setText(data.getJSONObject(50).getJSONObject("stats").getString("away_score"));
                            HuitHome4.setImageResource(ChooseFlag(data.getJSONObject(51).getJSONObject("home_team").getString("name")));
                            HuitAway4.setImageResource(ChooseFlag(data.getJSONObject(51).getJSONObject("away_team").getString("name")));
                            HuitHomeScore4.setText(data.getJSONObject(51).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore4.setText(data.getJSONObject(51).getJSONObject("stats").getString("away_score"));
                            HuitHome5.setImageResource(ChooseFlag(data.getJSONObject(52).getJSONObject("home_team").getString("name")));
                            HuitAway5.setImageResource(ChooseFlag(data.getJSONObject(52).getJSONObject("away_team").getString("name")));
                            HuitHomeScore5.setText(data.getJSONObject(52).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore5.setText(data.getJSONObject(52).getJSONObject("stats").getString("away_score"));
                            HuitHome6.setImageResource(ChooseFlag(data.getJSONObject(53).getJSONObject("home_team").getString("name")));
                            HuitAway6.setImageResource(ChooseFlag(data.getJSONObject(53).getJSONObject("away_team").getString("name")));
                            HuitHomeScore6.setText(data.getJSONObject(53).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore6.setText(data.getJSONObject(53).getJSONObject("stats").getString("away_score"));
                            HuitHome7.setImageResource(ChooseFlag(data.getJSONObject(54).getJSONObject("home_team").getString("name")));
                            HuitAway7.setImageResource(ChooseFlag(data.getJSONObject(54).getJSONObject("away_team").getString("name")));
                            HuitHomeScore7.setText(data.getJSONObject(54).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore7.setText(data.getJSONObject(54).getJSONObject("stats").getString("away_score"));
                            HuitHome8.setImageResource(ChooseFlag(data.getJSONObject(55).getJSONObject("home_team").getString("name")));
                            HuitAway8.setImageResource(ChooseFlag(data.getJSONObject(55).getJSONObject("away_team").getString("name")));
                            HuitHomeScore8.setText(data.getJSONObject(55).getJSONObject("stats").getString("home_score"));
                            HuitAwayScore8.setText(data.getJSONObject(55).getJSONObject("stats").getString("away_score"));
                            
                            QuatreHome1.setImageResource(ChooseFlag(data.getJSONObject(56).getJSONObject("home_team").getString("name")));
                            QuatreAway1.setImageResource(ChooseFlag(data.getJSONObject(56).getJSONObject("away_team").getString("name")));
                            QuatreHomeScore1.setText(data.getJSONObject(56).getJSONObject("stats").getString("home_score"));
                            QuatreAwayScore1.setText(data.getJSONObject(56).getJSONObject("stats").getString("away_score"));
                            QuatreHome2.setImageResource(ChooseFlag(data.getJSONObject(57).getJSONObject("home_team").getString("name")));
                            QuatreAway2.setImageResource(ChooseFlag(data.getJSONObject(57).getJSONObject("away_team").getString("name")));
                            QuatreHomeScore2.setText(data.getJSONObject(57).getJSONObject("stats").getString("home_score"));
                            QuatreAwayScore2.setText(data.getJSONObject(57).getJSONObject("stats").getString("away_score"));
                            QuatreHome3.setImageResource(ChooseFlag(data.getJSONObject(58).getJSONObject("home_team").getString("name")));
                            QuatreAway3.setImageResource(ChooseFlag(data.getJSONObject(58).getJSONObject("away_team").getString("name")));
                            QuatreHomeScore3.setText(data.getJSONObject(58).getJSONObject("stats").getString("home_score"));
                            QuatreAwayScore3.setText(data.getJSONObject(58).getJSONObject("stats").getString("away_score"));
                            QuatreHome4.setImageResource(ChooseFlag(data.getJSONObject(59).getJSONObject("home_team").getString("name")));
                            QuatreAway4.setImageResource(ChooseFlag(data.getJSONObject(59).getJSONObject("away_team").getString("name")));
                            QuatreHomeScore4.setText(data.getJSONObject(59).getJSONObject("stats").getString("home_score"));
                            QuatreAwayScore4.setText(data.getJSONObject(59).getJSONObject("stats").getString("away_score"));

                            DeuxHome1.setImageResource(ChooseFlag(data.getJSONObject(60).getJSONObject("home_team").getString("name")));
                            DeuxAway1.setImageResource(ChooseFlag(data.getJSONObject(60).getJSONObject("away_team").getString("name")));
                            DeuxHomeScore1.setText(data.getJSONObject(60).getJSONObject("stats").getString("home_score"));
                            DeuxAwayScore1.setText(data.getJSONObject(60).getJSONObject("stats").getString("away_score"));
                            DeuxHome2.setImageResource(ChooseFlag(data.getJSONObject(61).getJSONObject("home_team").getString("name")));
                            DeuxAway2.setImageResource(ChooseFlag(data.getJSONObject(61).getJSONObject("away_team").getString("name")));
                            DeuxHomeScore2.setText(data.getJSONObject(61).getJSONObject("stats").getString("home_score"));
                            DeuxAwayScore2.setText(data.getJSONObject(61).getJSONObject("stats").getString("away_score"));

                            FinaleHome1.setImageResource(ChooseFlag(data.getJSONObject(63).getJSONObject("home_team").getString("name")));
                            FinaleAway1.setImageResource(ChooseFlag(data.getJSONObject(63).getJSONObject("away_team").getString("name")));
                            FinaleHomeScore1.setText(data.getJSONObject(63).getJSONObject("stats").getString("home_score"));
                            FinaleAwayScore1.setText(data.getJSONObject(63).getJSONObject("stats").getString("away_score"));
                            

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }

        });
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
            case "Colombia":
                return Flag[32];
            case "Panama":
                return Flag[33];
            case "Sweden":
                return Flag[34];
            case "Nigeria":
                return Flag[35];
            case "Peru":
                return Flag[36];
            case "Iceland":
                return Flag[37];
            case "Egypt":
                return Flag[38];
            case "Russia":
                return Flag[39];
            default:
                return R.drawable.ic_baseline_flag_24;
        }
    }
    
}