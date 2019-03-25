package com.rebersincar.kampusetkinlik.Models;

public class WeekPojo{
	private String date;
	private String image;
	private String puan;
	private String sonuc;
	private String main;
	private int kategori;
	private String userid;
	private String head;
	private String katilim;
	private String begenme;
	private boolean tf;
	private String id;
	private String time;

	public String getDate(){
		return date;
	}

	public String getImage(){
		return image;
	}

	public String getPuan(){
		return puan;
	}

	public String getSonuc(){
		return sonuc;
	}

	public String getMain(){
		return main;
	}

	public int getKategori(){
		return kategori;
	}

	public String getUserid(){
		return userid;
	}

	public String getHead(){
		return head;
	}

	public String getKatilim(){
		return katilim;
	}

	public String getBegenme(){
		return begenme;
	}

	public boolean isTf(){
		return tf;
	}

	public String getId(){
		return id;
	}

	public String getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"WeekPojo{" + 
			"date = '" + date + '\'' + 
			",image = '" + image + '\'' + 
			",puan = '" + puan + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			",main = '" + main + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",userid = '" + userid + '\'' + 
			",head = '" + head + '\'' + 
			",katilim = '" + katilim + '\'' + 
			",begenme = '" + begenme + '\'' + 
			",tf = '" + tf + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}
