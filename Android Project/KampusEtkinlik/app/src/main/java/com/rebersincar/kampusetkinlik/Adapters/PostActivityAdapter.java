package com.rebersincar.kampusetkinlik.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.kampusetkinlik.Models.ActivityPojo;
import com.rebersincar.kampusetkinlik.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostActivityAdapter {

    List<ActivityPojo> list;
    Context context;

    public PostActivityAdapter(List<ActivityPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_post, parent , false);
        ImageView resim = convertView.findViewById(R.id.postImage);
        String url ="http://firatkampus.atwebpages.com/"+list.get(position).getImage();
        Picasso.with(context).load(url).centerCrop().resize(400,150).into(resim);

        convertView = LayoutInflater.from(context).inflate(R.layout.content_post, parent , false);

        TextView content = convertView.findViewById(R.id.contentpost);

        content.setText(list.get(position).getMain());

        return convertView;
    }
}
