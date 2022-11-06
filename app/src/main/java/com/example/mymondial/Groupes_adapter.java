package com.example.mymondial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Groupes_adapter extends BaseAdapter {

    Context context;

    // EQUIPE 1
    ArrayList<Integer> E1_flag = new ArrayList<Integer>();
    ArrayList<String> E1_name = new ArrayList<String>();
    ArrayList<String> E1_match_joue = new ArrayList<String>();
    ArrayList<String> E1_victoire = new ArrayList<String>();
    ArrayList<String> E1_defaite = new ArrayList<String>();
    ArrayList<String> E1_nul = new ArrayList<String>();
    ArrayList<String> E1_point = new ArrayList<String>();
    // EQUIPE 2
    ArrayList<Integer> E2_flag = new ArrayList<Integer>();
    ArrayList<String> E2_name = new ArrayList<String>();
    ArrayList<String> E2_match_joue = new ArrayList<String>();
    ArrayList<String> E2_victoire = new ArrayList<String>();
    ArrayList<String> E2_defaite = new ArrayList<String>();
    ArrayList<String> E2_nul = new ArrayList<String>();
    ArrayList<String> E2_point = new ArrayList<String>();
    // EQUIPE 3
    ArrayList<Integer> E3_flag = new ArrayList<Integer>();
    ArrayList<String> E3_name = new ArrayList<String>();
    ArrayList<String> E3_match_joue = new ArrayList<String>();
    ArrayList<String> E3_victoire = new ArrayList<String>();
    ArrayList<String> E3_defaite = new ArrayList<String>();
    ArrayList<String> E3_nul = new ArrayList<String>();
    ArrayList<String> E3_point = new ArrayList<String>();
    // EQUIPE 4
    ArrayList<Integer> E4_flag = new ArrayList<Integer>();
    ArrayList<String> E4_name = new ArrayList<String>();
    ArrayList<String> E4_match_joue = new ArrayList<String>();
    ArrayList<String> E4_victoire = new ArrayList<String>();
    ArrayList<String> E4_defaite = new ArrayList<String>();
    ArrayList<String> E4_nul = new ArrayList<String>();
    ArrayList<String> E4_point = new ArrayList<String>();

    LayoutInflater inflater ;

    public Groupes_adapter(Context context, ArrayList<Integer> E1_flag, ArrayList<String> E1_name, ArrayList<String> E1_match_joue, ArrayList<String> E1_victoire, ArrayList<String> E1_defaite, ArrayList<String> E1_nul, ArrayList<String> E1_point,
                                            ArrayList<Integer> E2_flag, ArrayList<String> E2_name, ArrayList<String> E2_match_joue, ArrayList<String> E2_victoire, ArrayList<String> E2_defaite, ArrayList<String> E2_nul, ArrayList<String> E2_point,
                                            ArrayList<Integer> E3_flag, ArrayList<String> E3_name, ArrayList<String> E3_match_joue, ArrayList<String> E3_victoire, ArrayList<String> E3_defaite, ArrayList<String> E3_nul, ArrayList<String> E3_point,
                                            ArrayList<Integer> E4_flag, ArrayList<String> E4_name, ArrayList<String> E4_match_joue, ArrayList<String> E4_victoire, ArrayList<String> E4_defaite, ArrayList<String> E4_nul, ArrayList<String> E4_point){
        this.context=context;

        // EQUIPE 1
        this.E1_flag=E1_flag;
        this.E1_name=E1_name;
        this.E1_match_joue=E1_match_joue;
        this.E1_victoire=E1_victoire;
        this.E1_defaite= E1_defaite;
        this.E1_nul=E1_nul;
        this.E1_point=E1_point;
        // EQUIPE 2
        this.E2_flag=E2_flag;
        this.E2_name=E2_name;
        this.E2_match_joue=E2_match_joue;
        this.E2_victoire=E2_victoire;
        this.E2_defaite= E2_defaite;
        this.E2_nul=E2_nul;
        this.E2_point=E2_point;
        // EQUIPE 3
        this.E3_flag=E3_flag;
        this.E3_name=E3_name;
        this.E3_match_joue=E3_match_joue;
        this.E3_victoire=E3_victoire;
        this.E3_defaite= E3_defaite;
        this.E3_nul=E3_nul;
        this.E3_point=E3_point;
        // EQUIPE 4
        this.E4_flag=E4_flag;
        this.E4_name=E4_name;
        this.E4_match_joue=E4_match_joue;
        this.E4_victoire=E4_victoire;
        this.E4_defaite= E4_defaite;
        this.E4_nul=E4_nul;
        this.E4_point=E4_point;

        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return E1_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_groupes_listview, null);

        // APPLICATION DES VARIABLES

        // DRAPEAUX DES PAYS
        ImageView E1_FlagView = (ImageView) convertView.findViewById(R.id.E1_flag);
        ImageView E2_FlagView = (ImageView) convertView.findViewById(R.id.E2_flag);
        ImageView E3_FlagView = (ImageView) convertView.findViewById(R.id.E3_flag);
        ImageView E4_FlagView = (ImageView) convertView.findViewById(R.id.E4_flag);
        // NOMS DES PAYS
        TextView E1_NameView = (TextView) convertView.findViewById(R.id.E1_name);
        TextView E2_NameView = (TextView) convertView.findViewById(R.id.E2_name);
        TextView E3_NameView = (TextView) convertView.findViewById(R.id.E3_name);
        TextView E4_NameView = (TextView) convertView.findViewById(R.id.E4_name);
        // NOMBRE DE MATCHS JOUES
        TextView E1_MatchView = (TextView) convertView.findViewById(R.id.E1_match_joue);
        TextView E2_MatchView = (TextView) convertView.findViewById(R.id.E2_match_joue);
        TextView E3_MatchView = (TextView) convertView.findViewById(R.id.E3_match_joue);
        TextView E4_MatchView = (TextView) convertView.findViewById(R.id.E4_match_joue);
        // NOMBRE DE VICTOIRES
        TextView E1_VictoireView = (TextView) convertView.findViewById(R.id.E1_victoire);
        TextView E2_VictoireView = (TextView) convertView.findViewById(R.id.E2_victoire);
        TextView E3_VictoireView = (TextView) convertView.findViewById(R.id.E3_victoire);
        TextView E4_VictoireView = (TextView) convertView.findViewById(R.id.E4_victoire);
        // NOMBRE DE DEFAITES
        TextView E1_DefaiteView = (TextView) convertView.findViewById(R.id.E1_defaite);
        TextView E2_DefaiteView = (TextView) convertView.findViewById(R.id.E2_defaite);
        TextView E3_DefaiteView = (TextView) convertView.findViewById(R.id.E3_defaite);
        TextView E4_DefaiteView = (TextView) convertView.findViewById(R.id.E4_defaite);
        // NOMBRE DE MATCHS NULS
        TextView E1_NulView = (TextView) convertView.findViewById(R.id.E1_nul);
        TextView E2_NulView = (TextView) convertView.findViewById(R.id.E2_nul);
        TextView E3_NulView = (TextView) convertView.findViewById(R.id.E3_nul);
        TextView E4_NulView = (TextView) convertView.findViewById(R.id.E4_nul);
        // NOMBRE DE POINTS
        TextView E1_PointView = (TextView) convertView.findViewById(R.id.E1_point);
        TextView E2_PointView = (TextView) convertView.findViewById(R.id.E2_point);
        TextView E3_PointView = (TextView) convertView.findViewById(R.id.E3_point);
        TextView E4_PointView = (TextView) convertView.findViewById(R.id.E4_point);


        // AFFICHAGES

        // DRAPEAUX
        E1_FlagView.setImageResource(E1_flag.get(position));
        E2_FlagView.setImageResource(E2_flag.get(position));
        E3_FlagView.setImageResource(E3_flag.get(position));
        E4_FlagView.setImageResource(E4_flag.get(position));
        // NOMS
        E1_NameView.setText(E1_name.get(position));
        E2_NameView.setText(E2_name.get(position));
        E3_NameView.setText(E3_name.get(position));
        E4_NameView.setText(E4_name.get(position));
        // MATCHS JOUES
        E1_MatchView.setText(E1_match_joue.get(position));
        E2_MatchView.setText(E2_match_joue.get(position));
        E3_MatchView.setText(E3_match_joue.get(position));
        E4_MatchView.setText(E4_match_joue.get(position));
        // VICTOIRES
        E1_VictoireView.setText(E1_victoire.get(position));
        E2_VictoireView.setText(E2_victoire.get(position));
        E3_VictoireView.setText(E3_victoire.get(position));
        E4_VictoireView.setText(E4_victoire.get(position));
        // DEFAITES
        E1_DefaiteView.setText(E1_defaite.get(position));
        E2_DefaiteView.setText(E2_defaite.get(position));
        E3_DefaiteView.setText(E3_defaite.get(position));
        E4_DefaiteView.setText(E4_defaite.get(position));
        // NULS
        E1_NulView.setText(E1_nul.get(position));
        E2_NulView.setText(E2_nul.get(position));
        E3_NulView.setText(E3_nul.get(position));
        E4_NulView.setText(E4_nul.get(position));
        // POINTS
        E1_PointView.setText(E1_point.get(position));
        E2_PointView.setText(E2_point.get(position));
        E3_PointView.setText(E3_point.get(position));
        E4_PointView.setText(E4_point.get(position));

        return convertView;
    }
}
