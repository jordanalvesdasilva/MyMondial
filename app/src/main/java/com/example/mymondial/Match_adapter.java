package com.example.mymondial;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.strictmode.FragmentStrictMode;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Match_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> HomeTeam = new ArrayList<String>();
    ArrayList<String> AwayTeam = new ArrayList<String>();
    ArrayList<String> Score = new ArrayList<String>();
    ArrayList<String> Time = new ArrayList<String>();
    ArrayList<Integer> HomeTeamFlag = new ArrayList<Integer>();
    ArrayList<Integer> AwayTeamFlag = new ArrayList<Integer>();
    LayoutInflater inflater ;

    public Match_adapter(Context context, ArrayList<String> HomeTeam, ArrayList<String> AwayTeam, ArrayList<String> Score, ArrayList<String> Time, ArrayList<Integer> HomeTeamFlag, ArrayList<Integer> AwayTeamFlag){
        this.context=context;
        this.AwayTeam=AwayTeam;
        this.HomeTeam=HomeTeam;
        this.Time=Time;
        this.Score=Score;
        this.HomeTeamFlag= HomeTeamFlag;
        this.AwayTeamFlag=AwayTeamFlag;
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
