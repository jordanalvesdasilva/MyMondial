package com.example.mymondial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Adapter de la Listview de la page home
// Permet d'adapter les différentes informations dans un item de la listview
public class Match_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> HomeTeam = new ArrayList<String>();
    ArrayList<String> AwayTeam = new ArrayList<String>();
    ArrayList<String> Score = new ArrayList<String>();
    ArrayList<String> Time = new ArrayList<String>();
    ArrayList<Integer> HomeTeamFlag = new ArrayList<Integer>();
    ArrayList<Integer> AwayTeamFlag = new ArrayList<Integer>();
    ArrayList<String> ID = new ArrayList<String>();
    LayoutInflater inflater ;

    public Match_adapter(Context context, ArrayList<String> HomeTeam, ArrayList<String> AwayTeam, ArrayList<String> Score, ArrayList<String> Time, ArrayList<Integer> HomeTeamFlag, ArrayList<Integer> AwayTeamFlag, ArrayList<String> ID){
        this.context=context;
        this.AwayTeam=AwayTeam;
        this.HomeTeam=HomeTeam;
        this.Time=Time;
        this.Score=Score;
        this.HomeTeamFlag= HomeTeamFlag;
        this.AwayTeamFlag=AwayTeamFlag;
        this.ID=ID;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return HomeTeam.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        Long ID_long =Long.parseLong(ID.get(position));
        return ID_long;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_match_listview, null);
        TextView HomeTeamView = (TextView) convertView.findViewById(R.id.HomeTeam);
        TextView AwayTeamView = (TextView) convertView.findViewById(R.id.AwayTeam);
        TextView ScoreView = (TextView) convertView.findViewById(R.id.Result);
        TextView TimeView = (TextView) convertView.findViewById(R.id.Time);
        ImageView HomeTeamFlagView = (ImageView) convertView.findViewById(R.id.HomeTeamFlag);
        ImageView AwayTeamFlagView = (ImageView) convertView.findViewById(R.id.AwayTeamFlag);

        HomeTeamView.setText(HomeTeam.get(position));
        AwayTeamView.setText(AwayTeam.get(position));
        ScoreView.setText(Score.get(position));
        TimeView.setText(Time.get(position));
        HomeTeamFlagView.setImageResource(HomeTeamFlag.get(position));
        AwayTeamFlagView.setImageResource(AwayTeamFlag.get(position));

        return convertView;
    }
}
