package com.rebersincar.kampusetkinlik.Adapters;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebersincar.kampusetkinlik.Activity.SelectKategoriActivity;
import com.rebersincar.kampusetkinlik.Models.KatildigimPojo;
import com.rebersincar.kampusetkinlik.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class KatildigimAdapter extends BaseAdapter {

    List<KatildigimPojo> list;
    Context context;

    public KatildigimAdapter(List<KatildigimPojo> list,Context context) {

        this.context=context;
        this.list=list;

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
        convertView = LayoutInflater.from(context).inflate(R.layout.postlayout, parent , false);
        ImageView resim = convertView.findViewById(R.id.layoutImage);
        TextView head = convertView.findViewById(R.id.layoutHead);
        TextView kategori = convertView.findViewById(R.id.layoutKategori);
        TextView main = convertView.findViewById(R.id.layoutMain);
        TextView dateTime = convertView.findViewById(R.id.layoutDate);
        TextView puan = convertView.findViewById(R.id.begenText);
        TextView katilim = convertView.findViewById(R.id.katilimText);
        if (list.get(position).isTf())
        {
            String url ="http://firatkampus.atwebpages.com/"+list.get(position).getImage();
            head.setText(list.get(position).getHead().toUpperCase());
            dateTime.setText(list.get(position).getDate()+"     "+list.get(position).getTime());
            main.setText(list.get(position).getMain());
            kategori.setText(SelectKategoriActivity.Categories[list.get(position).getKategori()-1]);
            Picasso.with(context).load(url).centerCrop().resize(400,150).into(resim);
            katilim.setText(""+list.get(position).getKatilim());
            puan.setText(""+list.get(position).getBegenme());

            String today = Bugun();
            String etkinlik = list.get(position).getDate();
            boolean kontrol = KontrolTarih(etkinlik,today);
            if (kontrol == false)
            {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);

                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                resim.setColorFilter(filter);
            }
        }
        else
        {
            String url ="http://firatkampus.atwebpages.com/Images/no-item.jpg";
            Picasso.with(context).load(url).centerCrop().resize(400,150).into(resim);
            head.setText("Etkinlik Bulunamadı");
        }

        return convertView;
    }

    public String Bugun()
    {
        Date simdikiZaman = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return df.format(simdikiZaman);

    }

    public boolean KontrolTarih(String etkinlik, String today)
    {
        StringTokenizer tarihEtkinlik = new StringTokenizer(etkinlik, "/");
        StringTokenizer tarihToday = new StringTokenizer(today,"/");


        int dayEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());
        int monthEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());
        int yearEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());

        int dayToday = Integer.valueOf(tarihToday.nextToken());
        int monthToday = Integer.valueOf(tarihToday.nextToken());
        int yearToday = Integer.valueOf(tarihToday.nextToken());

        if (yearEtkinlik>yearToday)
        {
            return true;
        }
        else if (yearEtkinlik == yearToday)
        {
            if (monthEtkinlik>monthToday)
            {
                return true;
            }
            else if (monthEtkinlik == monthToday)
            {
                if (dayEtkinlik>dayToday)
                {
                    return true;
                }
                else if (dayEtkinlik == dayToday)
                {
                    return true;
                }
            }
        }

        return false;
    }

}
