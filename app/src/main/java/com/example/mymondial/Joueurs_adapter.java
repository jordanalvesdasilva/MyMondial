package com.example.mymondial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Joueurs_adapter extends BaseAdapter {

    Context context;

    ArrayList<String> Player_Rang = new ArrayList<String>();
    ArrayList<String> Player_Name = new ArrayList<String>();
    ArrayList<String> Player_Stats = new ArrayList<String>();
    ArrayList<String> Player_Min = new ArrayList<String>();
    ArrayList<Integer> Player_Flag = new ArrayList<Integer>();

    LayoutInflater inflater ;

    public Joueurs_adapter(Context context, ArrayList<String> Player_Rang, ArrayList<String> Player_Name, ArrayList<String> Player_Stats, ArrayList<String> Player_Min, ArrayList<Integer> Player_Flag){
        this.context=context;
        this.Player_Rang=Player_Rang;
        this.Player_Name=Player_Name;
        this.Player_Stats=Player_Stats;
        this.Player_Min=Player_Min;
        this.Player_Flag= Player_Flag;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return Player_Rang.size();
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
        convertView = inflater.inflate(R.layout.activity_joueurs_listview, null);
        TextView PlayerRangView = (TextView) convertView.findViewById(R.id.Player_Rang);
        TextView PlayerNameView = (TextView) convertView.findViewById(R.id.Player_Name);
        TextView PlayerStatsView = (TextView) convertView.findViewById(R.id.Player_Stats);
        TextView PlayerMinView = (TextView) convertView.findViewById(R.id.Player_Min);
        ImageView PlayerFlagView = (ImageView) convertView.findViewById(R.id.Player_Flag);

        PlayerRangView.setText(Player_Rang.get(position));
        PlayerNameView.setText(Player_Name.get(position));
        PlayerStatsView.setText(Player_Stats.get(position));
        PlayerMinView.setText(Player_Min.get(position));
        PlayerFlagView.setImageResource(Player_Flag.get(position));

        return convertView;
    }
}
