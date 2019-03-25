package com.rebersincar.kampusetkinlik.Models;

public class JoinPojo{
	private boolean tf;
	private String sonuc;

	public boolean isTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"JoinPojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
