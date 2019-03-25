package com.rebersincar.kampusetkinlik.Models;

public class ActivityPojo{
	private String head;
	private String date;
	private String image;
	private boolean tf;
	private int userid;
	private int id;
	private String sonuc;
	private String main;
	private String time;
	private int katilim;
	private float puan;
	private int begenme;

	public String getHead() {
		return head;
	}

	public String getDate() {
		return date;
	}

	public String getImage() {
		return image;
	}

	public boolean isTf() {
		return tf;
	}

	public int getUserid() {
		return userid;
	}

	public int getId() {
		return id;
	}

	public String getSonuc() {
		return sonuc;
	}

	public String getMain() {
		return main;
	}

	public String getTime() {
		return time;
	}

	public int getKatilim() {
		return katilim;
	}

	public float getPuan() {
		return puan;
	}

	public int getBegenme() {
		return begenme;
	}

	@Override
	public String toString() {
		return "ActivityPojo{" +
				"head='" + head + '\'' +
				", date='" + date + '\'' +
				", image='" + image + '\'' +
				", tf=" + tf +
				", userid=" + userid +
				", id=" + id +
				", sonuc='" + sonuc + '\'' +
				", main='" + main + '\'' +
				", time='" + time + '\'' +
				", katilim=" + katilim +
				'}';
	}
}
