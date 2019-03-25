package com.rebersincar.kampusetkinlik.Models;

public class LikePojo{
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
			"LikePojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
