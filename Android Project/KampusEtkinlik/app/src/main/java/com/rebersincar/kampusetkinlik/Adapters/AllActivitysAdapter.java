package com.rebersincar.kampusetkinlik.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.kampusetkinlik.Models.ActivityPojo;
import com.rebersincar.kampusetkinlik.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllActivitysAdapter extends BaseAdapter {
    List<ActivityPojo> list;
    Context context;

    public AllActivitysAdapter(List<ActivityPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.postlayout, parent , false);
        ImageView resim = convertView.findViewById(R.id.layoutImage);
        TextView head = convertView.findViewById(R.id.layoutHead);
        TextView dateTime = convertView.findViewById(R.id.layoutDate);
        TextView puan = convertView.findViewById(R.id.begenText);
        TextView katilim = convertView.findViewById(R.id.katilimText);
        String url ="http://firatkampus.atwebpages.com/"+list.get(position).getImage();
        head.setText(list.get(position).getHead());
        dateTime.setText(list.get(position).getDate()+"     "+list.get(position).getTime());
        Picasso.with(context).load(url).centerCrop().resize(400,150).into(resim);
        katilim.setText(""+list.get(position).getKatilim());
        puan.setText(""+list.get(position).getBegenme());
        return convertView;
    }
}
