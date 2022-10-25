package com.example.mymondial;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Match_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> HomeTeam = new ArrayList<String>();
    ArrayList<String> AwayTeam = new ArrayList<String>();
    ArrayList<String> Score = new ArrayList<String>();
    ArrayList<String> Time = new ArrayList<String>();
    LayoutInflater inflater ;

    public Match_adapter(Context context, ArrayList<String> HomeTeam, ArrayList<String> AwayTeam, ArrayList<String> Score, ArrayList<String> Time){
        this.context=context;
        this.AwayTeam=AwayTeam;
        this.HomeTeam=HomeTeam;
        this.Time=Time;
        this.Score=Score;
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
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_match_listview, null);
        TextView HomeTeamView = (TextView) convertView.findViewById(R.id.HomeTeam);
        TextView AwayTeamView = (TextView) convertView.findViewById(R.id.AwayTeam);
        TextView ScoreView = (TextView) convertView.findViewById(R.id.Result);
        TextView TimeView = (TextView) convertView.findViewById(R.id.Time);

        HomeTeamView.setText(HomeTeam.get(position));
        AwayTeamView.setText(AwayTeam.get(position));
        ScoreView.setText(Score.get(position));
        TimeView.setText(Time.get(position));

        return convertView;
    }
}
