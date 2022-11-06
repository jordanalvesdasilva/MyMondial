package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
 * Use the {@link Groupes_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Groupes_Fragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ListView GroupesListview;
    // EQUIPE 1
    private ArrayList<Integer> E1_flag = new ArrayList<Integer>();
    private ArrayList<String> E1_name = new ArrayList<String>() ;
    private ArrayList<String> E1_match_joue = new ArrayList<String>();
    private ArrayList<String> E1_victoire = new ArrayList<String>();
    private ArrayList<String> E1_defaite = new ArrayList<String>();
    private ArrayList<String> E1_nul = new ArrayList<String>();
    private ArrayList<String> E1_point = new ArrayList<String>();
    // EQUIPE 2
    private ArrayList<Integer> E2_flag = new ArrayList<Integer>();
    private ArrayList<String> E2_name = new ArrayList<String>() ;
    private ArrayList<String> E2_match_joue = new ArrayList<String>();
    private ArrayList<String> E2_victoire = new ArrayList<String>();
    private ArrayList<String> E2_defaite = new ArrayList<String>();
    private ArrayList<String> E2_nul = new ArrayList<String>();
    private ArrayList<String> E2_point = new ArrayList<String>();
    // EQUIPE 3
    private ArrayList<Integer> E3_flag = new ArrayList<Integer>();
    private ArrayList<String> E3_name = new ArrayList<String>() ;
    private ArrayList<String> E3_match_joue = new ArrayList<String>();
    private ArrayList<String> E3_victoire = new ArrayList<String>();
    private ArrayList<String> E3_defaite = new ArrayList<String>();
    private ArrayList<String> E3_nul = new ArrayList<String>();
    private ArrayList<String> E3_point = new ArrayList<String>();
    // EQUIPE 4
    private ArrayList<Integer> E4_flag = new ArrayList<Integer>();
    private ArrayList<String> E4_name = new ArrayList<String>() ;
    private ArrayList<String> E4_match_joue = new ArrayList<String>();
    private ArrayList<String> E4_victoire = new ArrayList<String>();
    private ArrayList<String> E4_defaite = new ArrayList<String>();
    private ArrayList<String> E4_nul = new ArrayList<String>();
    private ArrayList<String> E4_point = new ArrayList<String>();

    // DRAPEAUX PAYS
    private int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa };



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Groupes_Fragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Groupes_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Groupes_Fragment newInstance(String param1, String param2) {
        Groupes_Fragment fragment = new Groupes_Fragment();
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

        View inflate = (ViewGroup) inflater.inflate(R.layout.fragment_groupes_, null);
        //Spinner spinner = inflate.findViewById(R.id.spinner);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.journée, android.R.layout.simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        // Inflate the layout for this fragment
        return inflate;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String current_day = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), current_day, Toast.LENGTH_SHORT).show();

        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        GroupesListview = (ListView) getView().findViewById(R.id.match_live);
        Groupes_adapter groupes_adapter = new Groupes_adapter(getActivity(), E1_flag, E1_name, E1_match_joue, E1_victoire, E1_defaite, E1_nul, E1_point,
                                                                             E2_flag, E2_name, E2_match_joue, E2_victoire, E2_defaite, E2_nul, E2_point,
                                                                             E3_flag, E3_name, E3_match_joue, E3_victoire, E3_defaite, E3_nul, E3_point,
                                                                             E4_flag, E4_name, E4_match_joue, E4_victoire, E4_defaite, E4_nul, E4_point);
        String url = "https://app.sportdataapi.com/api/v1/soccer/matches?apikey=193beda0-5093-11ed-aa03-b339e6eb1617&season_id=3072";   //CDM
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONArray data = jObject.getJSONArray("data");
                            System.out.println("Response is: " + response.substring(0, 500));
                            /*switch (current_day) {
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
                            }*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GroupesListview.setAdapter(groupes_adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        GroupesListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager() ;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Layout, new Groupes_Fragment());
                fragmentTransaction.commit();

                Bundle match_id = new Bundle();
                match_id.putString("MID", Long.toString(id));
                getParentFragmentManager().setFragmentResult("match_id",match_id );
            }
        });
    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /*
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

    }*/

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