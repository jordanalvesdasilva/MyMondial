package com.example.mymondial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Groupes_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Groupes_Fragment extends Fragment {

    private ListView GroupesListview;
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<String> Groupe_name = new ArrayList<String>();
    // EQUIPE 1
    private ArrayList<Integer> E1_flag = new ArrayList<Integer>();
    private ArrayList<String> E1_name = new ArrayList<String>();
    private ArrayList<String> E1_match_joue = new ArrayList<String>();
    private ArrayList<String> E1_victoire = new ArrayList<String>();
    private ArrayList<String> E1_defaite = new ArrayList<String>();
    private ArrayList<String> E1_nul = new ArrayList<String>();
    private ArrayList<String> E1_point = new ArrayList<String>();
    // EQUIPE 2
    private ArrayList<Integer> E2_flag = new ArrayList<Integer>();
    private ArrayList<String> E2_name = new ArrayList<String>();
    private ArrayList<String> E2_match_joue = new ArrayList<String>();
    private ArrayList<String> E2_victoire = new ArrayList<String>();
    private ArrayList<String> E2_defaite = new ArrayList<String>();
    private ArrayList<String> E2_nul = new ArrayList<String>();
    private ArrayList<String> E2_point = new ArrayList<String>();
    // EQUIPE 3
    private ArrayList<Integer> E3_flag = new ArrayList<Integer>();
    private ArrayList<String> E3_name = new ArrayList<String>();
    private ArrayList<String> E3_match_joue = new ArrayList<String>();
    private ArrayList<String> E3_victoire = new ArrayList<String>();
    private ArrayList<String> E3_defaite = new ArrayList<String>();
    private ArrayList<String> E3_nul = new ArrayList<String>();
    private ArrayList<String> E3_point = new ArrayList<String>();
    // EQUIPE 4
    private ArrayList<Integer> E4_flag = new ArrayList<Integer>();
    private ArrayList<String> E4_name = new ArrayList<String>();
    private ArrayList<String> E4_match_joue = new ArrayList<String>();
    private ArrayList<String> E4_victoire = new ArrayList<String>();
    private ArrayList<String> E4_defaite = new ArrayList<String>();
    private ArrayList<String> E4_nul = new ArrayList<String>();
    private ArrayList<String> E4_point = new ArrayList<String>();

    // DRAPEAUX PAYS
    public int Flag []= {R.drawable.allemagne,R.drawable.angleterre,R.drawable.arabie_saoudite,R.drawable.argentine,R.drawable.australie,R.drawable.belgique,R.drawable.bresil,R.drawable.cameroun,R.drawable.canada,R.drawable.croatie,R.drawable.costa_rica,R.drawable.danemark,R.drawable.equateur,R.drawable.espagne,R.drawable.france,R.drawable.ghana,R.drawable.iran,R.drawable.japon,R.drawable.maroc,R.drawable.mexique,R.drawable.pays_bas,R.drawable.pays_de_galles,R.drawable.pologne,R.drawable.portugal,R.drawable.quatar,R.drawable.republique_de_coree,R.drawable.senegal,R.drawable.serbie,R.drawable.suisse,R.drawable.tunisie,R.drawable.uruguay,R.drawable.usa,R.drawable.colombie, R.drawable.panama, R.drawable.suede,R.drawable.nigeria,R.drawable.perou,R.drawable.iceland,R.drawable.egypte,R.drawable.russie };


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


        // REQUEST API FOR MATCH
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        GroupesListview = (ListView) inflate.findViewById(R.id.groupes_live);
        Groupes_adapter groupes_adapter = new Groupes_adapter(getActivity(), E1_flag, E1_name, E1_match_joue, E1_victoire, E1_defaite, E1_nul, E1_point,
                E2_flag, E2_name, E2_match_joue, E2_victoire, E2_defaite, E2_nul, E2_point,
                E3_flag, E3_name, E3_match_joue, E3_victoire, E3_defaite, E3_nul, E3_point,
                E4_flag, E4_name, E4_match_joue, E4_victoire, E4_defaite, E4_nul, E4_point,
                Groupe_name);
        String url;
        String  years = ((Global_variable) getActivity().getApplication()).getyears();
        if (years.equals("2018")){
            url = "https://app.sportdataapi.com/api/v1/soccer/standings?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=1193";   //CDM 2018
        }
        else {
            url = "https://app.sportdataapi.com/api/v1/soccer/standings?apikey=54137fd0-5dd7-11ed-8fe2-912a4280ead9&season_id=3072";   //CDM 2022
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            JSONObject data = jObject.getJSONObject("data");
                            JSONObject Groupes = data.getJSONObject("standings");

                            // PROTOTYPE RECHERCHE API
                            ResetVariables();

                            for (int i = 0; i < Groupes.length(); i++) {
                                // AJOUTER RECHERCHE NOM DU GROUPE
                                String numero_groupe = Integer.toString(i);
                                // RECUPERATION NOM GROUPE
                                Groupe_name.add(IdGroupe(numero_groupe));
                                /*--------------------------------------- EQUIPE 1 ------------------------------------------*/
                                // NOM EQUIPE 1 + DRAPEAU
                                E1_name.add(IdPays(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getString("team_id")));
                                E1_flag.add(ChooseFlag(E1_name.get(i)));
                                // NOMBRE DE MATCHS JOUES
                                E1_match_joue.add(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getJSONObject("overall").getString("games_played"));
                                // NOMBRE DE VICTOIRES
                                E1_victoire.add(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getJSONObject("overall").getString("won"));
                                // NOMBRE DE DEFAITES
                                E1_defaite.add(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getJSONObject("overall").getString("lost"));
                                // NOMBRE DE MATCHS NULS
                                E1_nul.add(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getJSONObject("overall").getString("draw"));
                                // NOMBRE DE POINTS
                                E1_point.add(Groupes.getJSONArray(numero_groupe).getJSONObject(0).getString("points"));
                                /*--------------------------------------- EQUIPE 2 ------------------------------------------*/
                                // NOM EQUIPE 1 + DRAPEAU
                                E2_name.add(IdPays(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getString("team_id")));
                                E2_flag.add(ChooseFlag(E2_name.get(i)));
                                // NOMBRE DE MATCHS JOUES
                                E2_match_joue.add(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getJSONObject("overall").getString("games_played"));
                                // NOMBRE DE VICTOIRES
                                E2_victoire.add(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getJSONObject("overall").getString("won"));
                                // NOMBRE DE DEFAITES
                                E2_defaite.add(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getJSONObject("overall").getString("lost"));
                                // NOMBRE DE MATCHS NULS
                                E2_nul.add(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getJSONObject("overall").getString("draw"));
                                // NOMBRE DE POINTS
                                E2_point.add(Groupes.getJSONArray(numero_groupe).getJSONObject(1).getString("points"));
                                /*--------------------------------------- EQUIPE 3 ------------------------------------------*/
                                // NOM EQUIPE 1 + DRAPEAU
                                E3_name.add(IdPays(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getString("team_id")));
                                E3_flag.add(ChooseFlag(E3_name.get(i)));
                                // NOMBRE DE MATCHS JOUES
                                E3_match_joue.add(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getJSONObject("overall").getString("games_played"));
                                // NOMBRE DE VICTOIRES
                                E3_victoire.add(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getJSONObject("overall").getString("won"));
                                // NOMBRE DE DEFAITES
                                E3_defaite.add(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getJSONObject("overall").getString("lost"));
                                // NOMBRE DE MATCHS NULS
                                E3_nul.add(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getJSONObject("overall").getString("draw"));
                                // NOMBRE DE POINTS
                                E3_point.add(Groupes.getJSONArray(numero_groupe).getJSONObject(2).getString("points"));
                                /*--------------------------------------- EQUIPE 4 ------------------------------------------*/
                                // NOM EQUIPE 1 + DRAPEAU
                                E4_name.add(IdPays(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getString("team_id")));
                                E4_flag.add(ChooseFlag(E4_name.get(i)));
                                // NOMBRE DE MATCHS JOUES
                                E4_match_joue.add(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getJSONObject("overall").getString("games_played"));
                                // NOMBRE DE VICTOIRES
                                E4_victoire.add(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getJSONObject("overall").getString("won"));
                                // NOMBRE DE DEFAITES
                                E4_defaite.add(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getJSONObject("overall").getString("lost"));
                                // NOMBRE DE MATCHS NULS
                                E4_nul.add(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getJSONObject("overall").getString("draw"));
                                // NOMBRE DE POINTS
                                E4_point.add(Groupes.getJSONArray(numero_groupe).getJSONObject(3).getString("points"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println(E4_point);
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

        return inflate;


    }

    private void ResetVariables(){
        Groupe_name.clear();
        // EQUIPE 1 DU GROUPE
        E1_flag.clear();
        E1_name.clear();
        E1_match_joue.clear();
        E1_victoire.clear();
        E1_defaite.clear();
        E1_nul.clear();
        E1_point.clear();
        // EQUIPE 2 DU GROUPE
        E2_flag.clear();
        E2_name.clear();
        E2_match_joue.clear();
        E2_victoire.clear();
        E2_defaite.clear();
        E2_nul.clear();
        E2_point.clear();
        // EQUIPE 3 DU GROUPE
        E3_flag.clear();
        E3_name.clear();
        E3_match_joue.clear();
        E3_victoire.clear();
        E3_defaite.clear();
        E3_nul.clear();
        E3_point.clear();
        // EQUIPE 4 DU GROUPE
        E4_flag.clear();
        E4_name.clear();
        E4_match_joue.clear();
        E4_victoire.clear();
        E4_defaite.clear();
        E4_nul.clear();
        E4_point.clear();
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
    private String IdPays (String Team_id){
        switch (Team_id){
            case "14220" :
                return "Costa Rica";
            case "7841":
                return "Costa Rica";
            case "3017":
                return "Germany";
            case "12397":
                return "Japan";
            case "3024":
                return "Spain";
            case "12550":
                return "Ecuador";
            case "3080":
                return "Netherlands";
            case "12279":
                return "Qatar";
            case "56":
                return "Senegal";
            case "12302":
                return "England";
            case "12396":
                return "Iran";
            case "7850":
                return "USA";
            case "14218":
                return "Wales";
            case "12502":
                return "Argentina";
            case "12473":
                return "Mexico";
            case "3011":
                return "Poland";
            case "767":
                return "Saudi Arabia";
            case "3008":
                return "Denmark";
            case "12300":
                return "France";
            case "73":
                return "Tunisia";
            case "14219":
                return "Australia";
            case "747":
                return "Australia";
            case "3054":
                return "Belgium";
            case "7835":
                return "Canada";
            case "3026":
                return "Croatia";
            case "52":
                return "Morocco";
            case "12504":
                return "Brazil";
            case "85":
                return "Cameroon";
            case "3036":
                return "Serbia";
            case "3064":
                return "Switzerland";
            case "95":
                return "Ghana";
            case "755":
                return "Republic of Korea";
            case "12299":
                return "Portugal";
            case "12501":
                return "Uruguay";
            case "12431":
                return "Russia";
            case "24":
                return "Egypt";
            case "3029":
                return "Iceland";
            case "12503":
                return "Peru";
            case "37":
                return "Nigeria";
            case "3078":
                return "Sweden";
            case "12340":
                return "Panama";
            case "12365":
                return "Colombia";
            default:
                return " ";
        }
    }

    private String IdGroupe(String numero){
        switch (numero){
            case "0":
                return "A";
            case "1":
                return "B";
            case "2":
                return "C";
            case "3":
                return "D";
            case "4":
                return "E";
            case "5":
                return "F";
            case "6":
                return "G";
            case "7":
                return "H";
            default:
                return " ";
        }
    }

}