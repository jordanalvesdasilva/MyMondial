package com.example.mymondial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Information_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> Home_Time = new ArrayList<String>();
    ArrayList<String> Home_Who = new ArrayList<String>();
    ArrayList<String> Home_Who_Help = new ArrayList<String>();
    ArrayList<String> Score = new ArrayList<String>();
    ArrayList<Integer> Logo = new ArrayList<Integer>();
    ArrayList<String> Away_Time= new ArrayList<String>();
    ArrayList<String> Away_Who= new ArrayList<String>();
    ArrayList<String> Away_Who_Help= new ArrayList<String>();
    LayoutInflater inflater ;

    public Information_adapter(Context context, ArrayList<String> Home_Time, ArrayList<String> Home_Who, ArrayList<String> Home_Who_Help, ArrayList<String> Score, ArrayList<Integer> Logo, ArrayList<String> Away_Time, ArrayList<String> Away_Who, ArrayList<String> Away_Who_Help ){
        this.context=context;
        this.Home_Time=Home_Time;
        this.Home_Who=Home_Who;
        this.Home_Who_Help=Home_Who_Help;
        this.Score=Score;
        this.Logo=Logo;
        this.Away_Time=Away_Time;
        this.Away_Who=Away_Who;
        this.Away_Who_Help=Away_Who_Help;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return Score.size();
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
        convertView = inflater.inflate(R.layout.info_listview, null);
        TextView Home_Time_View = (TextView) convertView.findViewById(R.id.time_left);
        TextView Home_Who_View = (TextView) convertView.findViewById(R.id.who_left);
        TextView Home_Who_Help_View = (TextView) convertView.findViewById(R.id.who_help_left);
        ImageView Logo_View = (ImageView) convertView.findViewById(R.id.image);
        TextView Score_View = (TextView) convertView.findViewById(R.id.score);
        TextView Away_Time_View = (TextView) convertView.findViewById(R.id.time_right);
        TextView Away_Who_View = (TextView) convertView.findViewById(R.id.who_right);
        TextView Away_Who_Help_View = (TextView) convertView.findViewById(R.id.who_help_right);

        Home_Time_View.setText(Home_Time.get(position));
        Home_Who_View.setText(Home_Who.get(position));
        Home_Who_Help_View.setText(Home_Who_Help.get(position));
        Score_View.setText(Score.get(position));
        Logo_View.setImageResource(Logo.get(position));
        Away_Time_View.setText(Away_Time.get(position));
        Away_Who_View.setText(Away_Who.get(position));
        Away_Who_Help_View.setText(Away_Who_Help.get(position));

        return convertView;
    }
}
