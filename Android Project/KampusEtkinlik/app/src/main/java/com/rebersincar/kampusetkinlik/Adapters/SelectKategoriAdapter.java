package com.rebersincar.kampusetkinlik.Adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.kampusetkinlik.Models.Kategories;
import com.rebersincar.kampusetkinlik.R;

import java.util.List;

public class SelectKategoriAdapter extends BaseAdapter {

    private List<Kategories> list;
    Context context;

    public SelectKategoriAdapter(Context context, List<Kategories> kategori) {
        this.context=context;
        list = kategori;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.kategorilayout, parent , false);
        TextView baslik = convertView.findViewById(R.id.kategoriBaslik);
        ImageView icon = convertView.findViewById(R.id.kategoriImage);

        Kategories data = list.get(position);

        baslik.setText(data.getKategori_ismi());

        if(data.getKategori_ismi().equals("Eğitim"))
        {
            icon.setImageResource(R.drawable.egitim);
        }
        else if(data.getKategori_ismi().equals("Etkinlik"))
        {
            icon.setImageResource(R.drawable.etkinlik);
        }
        else if(data.getKategori_ismi().equals("Festival"))
        {
            icon.setImageResource(R.drawable.festival);
        }
        else if(data.getKategori_ismi().equals("Gezi"))
        {
            icon.setImageResource(R.drawable.gezi);
        }
        else if(data.getKategori_ismi().equals("Kamp"))
        {
            icon.setImageResource(R.drawable.kamp);
        }
        else if(data.getKategori_ismi().equals("Kariyer Günleri"))
        {
            icon.setImageResource(R.drawable.kariyer);
        }
        else if(data.getKategori_ismi().equals("Konferans"))
        {
            icon.setImageResource(R.drawable.konferans);
        }
        else if(data.getKategori_ismi().equals("Konser"))
        {
            icon.setImageResource(R.drawable.konser);
        }
        else if(data.getKategori_ismi().equals("Networking"))
        {
            icon.setImageResource(R.drawable.ntworking);
        }
        else if(data.getKategori_ismi().equals("Ödül Töreni"))
        {
            icon.setImageResource(R.drawable.odul);
        }
        else if(data.getKategori_ismi().equals("Panel"))
        {
            icon.setImageResource(R.drawable.panel);
        }
        else if(data.getKategori_ismi().equals("Sempozyum"))
        {
            icon.setImageResource(R.drawable.sempozyum);
        }
        else if(data.getKategori_ismi().equals("Yarışma"))
        {
            icon.setImageResource(R.drawable.yarisma);
        }
        else
        {
            icon.setImageResource(R.drawable.zirve);
        }


        return convertView;
    }
}
