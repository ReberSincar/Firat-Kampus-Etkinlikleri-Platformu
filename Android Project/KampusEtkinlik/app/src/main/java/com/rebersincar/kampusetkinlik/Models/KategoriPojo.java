package com.rebersincar.kampusetkinlik.Models;

public class KategoriPojo{
	private String date;
	private String image;
	private float puan;
	private String sonuc;
	private String main;
	private int kategori;
	private int userid;
	private String head;
	private int katilim;
	private int begenme;
	private boolean tf;
	private int id;
	private String time;


	public String getDate(){
		return date;
	}


	public String getImage(){
		return image;
	}

	public float getPuan(){
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

	public int getUserid(){
		return userid;
	}

	public String getHead(){
		return head;
	}

	public int getKatilim(){
		return katilim;
	}

	public int getBegenme(){
		return begenme;
	}

	public boolean isTf(){
		return tf;
	}

	public int getId(){
		return id;
	}

	public String getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"KategoriPojo{" + 
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
