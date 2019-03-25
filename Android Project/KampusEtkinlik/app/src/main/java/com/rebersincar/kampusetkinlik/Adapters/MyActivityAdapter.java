package com.rebersincar.kampusetkinlik.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.kampusetkinlik.Activity.AlertDialogClass;
import com.rebersincar.kampusetkinlik.Models.MyActivitysPojo;
import com.rebersincar.kampusetkinlik.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyActivityAdapter extends BaseAdapter {
    static List<MyActivitysPojo> list;
    Context context;
    Activity activity;
    int etkinlik;
    AlertDialogClass alertDialogClass;

    public MyActivityAdapter(List<MyActivitysPojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.mypostslayout, parent , false);
        ImageView resim = convertView.findViewById(R.id.imageMyPosts);
        TextView head = convertView.findViewById(R.id.headMyPosts);
        TextView dateTime = convertView.findViewById(R.id.dateMyPosts);
        if (list.get(position).isTf())
        {
            String url ="http://firatkampus.atwebpages.com/"+list.get(position).getImage();
            etkinlik = list.get(position).getId();
            head.setText(list.get(position).getHead());
            dateTime.setText(list.get(position).getDate()+"     "+list.get(position).getTime());
            Picasso.with(context).load(url).centerCrop().resize(120,90).into(resim);
        }
        else
        {
            String url ="http://firatkampus.atwebpages.com/Images/no-item.jpg";
            Picasso.with(context).load(url).centerCrop().resize(120,90).into(resim);
            head.setText("Etkinlik Bulunamadı");
        }
        return convertView;
    }
}
